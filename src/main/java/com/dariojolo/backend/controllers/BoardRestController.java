package com.dariojolo.backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.dariojolo.backend.model.services.IBoardService;
import com.dariojolo.backend.model.services.ICiudadService;
import com.dariojolo.backend.models.entities.Board;
import com.dariojolo.backend.models.entities.Ciudad;

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
	//Lista de ciudades por nombre de board
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
	
	//Obtener un board por ID
	@GetMapping("/boards/{id}")
	public Board show(@PathVariable Long id) {
		return boardService.findById(id);
	}
	//Obtener un board por ID
		@GetMapping("/boards/nombre/{usuario}")
		public Board showByNombre(@PathVariable String usuario) {
			
			return boardService.findByUsuario(usuario);
		}

	//Obtener una ciudad por ID
	//	@GetMapping("/boards/ciudades/{id}")
	//	public Ciudad showC(@PathVariable Long id) {
	//		return ciudadService.findById(id);
	//	}
	

	@PostMapping("/boards")
	@ResponseStatus(HttpStatus.CREATED)
	public Board create(@RequestBody Board board) {
		return boardService.save(board);
	}
	@PostMapping("/boards/ciudades")
	@ResponseStatus(HttpStatus.CREATED)
	public Ciudad createC(@RequestBody Ciudad ciudad) {
		return ciudadService.save(ciudad);
	}

	@PutMapping("/boards/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Board update(@RequestBody Board board, @PathVariable Long id) {
		Board boardActual = boardService.findById(id);

		boardActual.setCiudades(board.getCiudades());

		return boardService.save(boardActual);
	}
	@PutMapping("/boards/ciudades{id}")
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
		ciudadService.delete(id);
	}

}
