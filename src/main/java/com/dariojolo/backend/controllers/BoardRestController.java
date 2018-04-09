package com.dariojolo.backend.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.dariojolo.backend.model.services.IBoardService;
import com.dariojolo.backend.model.services.ICiudadService;
import com.dariojolo.backend.models.entities.Board;
import com.dariojolo.backend.models.entities.Ciudad;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class BoardRestController {

	private final SimpMessagingTemplate template;

	@Autowired
	private IBoardService boardService;

	@Autowired
	private ICiudadService ciudadService;

	@Autowired
	BoardRestController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/send/message")
	public void onReceiveMessage(Ciudad ciudad) {

		this.template.convertAndSend("/topic", ciudad);
	}

	// Lista de Boards
	@GetMapping("/boards")
	public List<HashMap<String, Object>> get() {
		List<HashMap<String, Object>> listado = new ArrayList<HashMap<String, Object>>();

		for (Board board : (List<Board>) boardService.findAll()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", board.getId());
			map.put("nombre", board.getNombre());
			map.put("usuario", board.getUsuario());
			HashMap<String, Ciudad> ciudades = new HashMap<>();

			for (Ciudad ciudad : board.getCiudades()) {
				ciudades.put(ciudad.getId() + "", ciudad);
			}
			map.put("ciudades", ciudades);
			listado.add(map);
		}
		return listado;
	}

	// Lista de ciudades por nombre de board
	@GetMapping("/boards/ciudades/{nombre}")
	public List<Ciudad> getCiudades(@PathVariable("nombre") String nombre) {
		System.out.println("Nombre: " + nombre);
		List<Ciudad> listado = new ArrayList<>();
		Board board = (Board) boardService.findByNombre(nombre);
		for (Ciudad ciudad : board.getCiudades()) {
			// this.onReceiveMessage("Ciudad agregada " + ciudad.getNombre());
			System.out.println("Ciudad agregada: " + ciudad.getNombre());
			listado.add(ciudad);
		}
		return listado;
	}

	// Obtener un board por ID
	@GetMapping("/boards/{id}")
	public Board show(@PathVariable Long id) {
		return boardService.findById(id);
	}

	// Obtener un board por nombre
	@GetMapping("/boards/nombre/{usuario}")
	public List<Board> showByNombre(@PathVariable String usuario) {

		return (List<Board>) boardService.findByUsuario(usuario);
	}

	// Obtener una ciudad por ID
	@GetMapping("/boards/Eciudades/{id}")
	public Ciudad showC(@PathVariable Long id) {
		System.out.println("ID Recibido: " + id);
		return ciudadService.findById(id);
	}

	@PostMapping("/boards")
	@ResponseStatus(HttpStatus.CREATED)
	public Board create(@RequestBody Board board) {
		return boardService.save(board);
	}

	// Guardar una ciudad
	@PostMapping("/boards/ciudades")
	@ResponseStatus(HttpStatus.CREATED)
	public Ciudad createC(@RequestBody Ciudad ciudad) throws IOException, ParseException {
		obtenerTemperatura(ciudad);

		return ciudadService.save(ciudad);
	}

	// Actualizar un board
	@PutMapping("/boards/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Board update(@RequestBody Board board, @PathVariable Long id) {

		Board boardActual = boardService.findById(id);
		boardActual.setNombre(board.getNombre());
		return boardService.save(boardActual);
	}

	// Editar una ciudad
	@PutMapping("/boards/ciudades/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ciudad updateC(@RequestBody Ciudad ciudad, @PathVariable Long id) throws IOException, ParseException {
		Ciudad ciudadActual = ciudadService.findById(id);
		obtenerTemperatura(ciudadActual);
		ciudadActual.setNombre(ciudad.getNombre());
		onReceiveMessage(ciudad);
		return ciudadService.save(ciudadActual);
	}

	@DeleteMapping("/boards/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		boardService.delete(id);
	}

	@DeleteMapping("/boards/ciudades/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteC(@PathVariable Long id) {
		ciudadService.deleteById(id);
	}

	@GetMapping("/boards/yahoo")
	public Ciudad obtenerTemperatura(Ciudad ciudadR) throws IOException, ParseException {
		String resultado = "";
		Ciudad ciudad = ciudadR;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json;charset=utf-8");
		headers.set("Access-Control-Allow-Origin", "*");
		headers.set("Age", "0");
		headers.set("Connection", "keep-alive");
		String resourceURL = "https://query.yahooapis.com/v1/public/yql?q=select item.condition from weather.forecast where woeid in (select woeid from geo.places(1) where text='"
				+ ciudad.getNombre() + "') and u='C'&format=json";

		String yahoo2 = new RestTemplate().getForObject(resourceURL, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(yahoo2);

		Iterator<JsonNode> iterator = actualObj.elements();

		if (iterator.hasNext()) {
			JsonNode object = iterator.next();
			JsonNode results = object.get("results");

			JsonNode channel = results.get("channel");
			JsonNode item = channel.get("item");
			JsonNode condition = item.get("condition");
			System.out.println("Condition: " + condition.get("temp"));
			JsonNode temp = condition.get("temp");
			JsonNode date = condition.get("date");
			ciudad.setTemperatura(temp.asText());

		}
		return ciudad;
	}
}
