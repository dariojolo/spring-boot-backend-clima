package com.dariojolo.backend.controllers;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dariojolo.backend.model.services.BoardServiceImpl;
import com.dariojolo.backend.model.services.IBoardService;
import com.dariojolo.backend.model.services.ICiudadService;
import com.dariojolo.backend.models.dao.IBoardDao;
import com.dariojolo.backend.models.entities.Board;
import com.dariojolo.backend.models.entities.Ciudad;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class BoardRestController {
	
	@Autowired
	private IBoardService boardService;
	
	
	
	@GetMapping("/boards")
	public List<Board>index(){
		List<Board> lista = new ArrayList<>();
		for (Board board : (List<Board>) boardService.findAll()) {
			System.out.println("En board: " + board);
			Board board2 = new Board();
			board2.setId(board.getId());
			board2.setNombre(board.getNombre());
			board2.setUsuario(board.getUsuario());
			List<Ciudad> listaCiudades = new ArrayList<>();
			for (Ciudad ciudad : board.getCiudades()) {
				System.out.println("En ciudad: " + ciudad);
				listaCiudades.add(ciudad);
				board2.getCiudades().add(ciudad);
			}
			for (Ciudad ciudad : board.getCiudades()) {
			board2.getCiudades().add(ciudad);
			}
			lista.add(board2);
		}
		return lista;
	};	
	
	@GetMapping("/boards2")
	public HashMap<String, Object> get() {
		HashMap<String, Object> map = new HashMap<>();
		for (Board board : (List<Board>) boardService.findAll()) {
	    map.put("id", board.getId());
		map.put("nombre", board.getNombre());
	    map.put("usuario", board.getUsuario());
	    HashMap<String, Ciudad> ciudades = new HashMap<>();
	    int i = 1;
	    for (Ciudad ciudad : board.getCiudades()) {
			System.out.println("En ciudad: " + ciudad.getNombre());
			ciudades.put(ciudad.getId()+"", ciudad);
			//board.getCiudades().add(ciudad);
			}
	    map.put("ciudades", ciudades);
		}
	    return map;
	}
	/*public ObjectNode sayHello() {
		for (Board board : (List<Board>) boardService.findAll()) {
	    ObjectNode objectNode = mapper.createObjectNode();
	    objectNode.put("key", "value");
	    objectNode.put("foo", "bar");
	    objectNode.put("number", 42);
	    return objectNode;
		}
	} */

}
