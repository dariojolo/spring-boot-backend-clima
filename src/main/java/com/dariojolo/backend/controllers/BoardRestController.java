package com.dariojolo.backend.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dariojolo.backend.model.services.IBoardService;
import com.dariojolo.backend.model.services.ICiudadService;
import com.dariojolo.backend.models.entities.Board;
import com.dariojolo.backend.models.entities.Ciudad;

import ch.qos.logback.core.net.server.Client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class BoardRestController {

	@Autowired
	private IBoardService boardService;

	@Autowired
	private ICiudadService ciudadService;

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
				System.out.println("En ciudad: " + ciudad.getNombre());
				ciudades.put(ciudad.getId() + "", ciudad);
				// board.getCiudades().add(ciudad);
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
	public Board showByNombre(@PathVariable String usuario) {

		return boardService.findByUsuario(usuario);
	}

	// Obtener una ciudad por ID
	@GetMapping("/boards/Eciudades/{id}")
	public Ciudad showC(@PathVariable Long id) {
		System.out.println("ID Recibido: " + id);
		return ciudadService.findById(id);
	}
	/*
	 * @GetMapping("/boards/Eciudades/{id}") public String showC(@PathVariable Long
	 * id, Map<String, Object> model) { System.out.println("ID Recibido: " + id);
	 * model.put("cliente",ciudadService.findById(id)); model.put("titulo",
	 * "Editar"); return "/board/formCiudad";
	 * 
	 * }
	 */

	@PostMapping("/boards")
	@ResponseStatus(HttpStatus.CREATED)
	public Board create(@RequestBody Board board) {
		return boardService.save(board);
	}

	// Guardar una ciudad
	@PostMapping("/boards/ciudades")
	@ResponseStatus(HttpStatus.CREATED)
	public Ciudad createC(@RequestBody Ciudad ciudad) {
		ciudad.setTemperatura("33");
		return ciudadService.save(ciudad);
	}

	// Actualizar un board
	@PutMapping("/boards/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Board update(@RequestBody Board board, @PathVariable Long id) {
		System.out.println("Editar board: " + board.getNombre());
		Board boardActual = boardService.findById(id);
		System.out.println("Board actual: " + boardActual.getNombre());
		boardActual.setNombre(board.getNombre());

		System.out.println("Board editado: " + board.getNombre());

		return boardService.save(boardActual);
	}

	// Editar una ciudad
	@PutMapping("/boards/ciudades/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ciudad updateC(@RequestBody Ciudad ciudad, @PathVariable Long id) {
		Ciudad ciudadActual = ciudadService.findById(id);

		ciudadActual.setNombre(ciudad.getNombre());

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
		System.out.println("Ciudad a eliminar " + id);
		ciudadService.deleteById(id);
		System.out.println("Ciudad eliminada");
	}

	

	//@GetMapping("/boards/yahoo", produces = MediaType.APPLICATION_JSON_VALUE)
	//@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//public @ResponseBody String yahoo() {
	@GetMapping("/boards/yahoo")
	public ResponseEntity<String> yahoo() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		//headers.set("Content-Type", "application/json;charset=utf-8");
		//headers.set("Access-Control-Allow-Origin", "*");
		//headers.set("Age", "0");
		//headers.set("Connection", "keep-alive");
		String resourceURL = "https://query.yahooapis.com/v1/public/yql?q=select item.condition from weather.forecast where woeid in (select woeid from geo.places(1) where text='buenos aires') and u='C'&format=json";
		HttpEntity<String>entity = new HttpEntity<String>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(resourceURL, HttpMethod.GET,entity,String.class);
		
		if (responseEntity.getStatusCode() ==HttpStatus.OK ) {
			System.out.println(responseEntity.getBody());
			String body = responseEntity.getBody();
			JSONObject jObject  = new JSONObject(); // json
			JSONObject data = (JSONObject) jObject.get("query"); // get data object
			//String projectname = data.getString("name");*/
			System.out.println("Data: " + data);
		}
		return responseEntity;
		
		
		
		
		
		
	}

}
