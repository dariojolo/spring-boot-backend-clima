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
	
	@GetMapping("/boards3")
	public List<Board> get3() {
		
		List<Board> listaBoards = new ArrayList<Board>();
		

		for (Board board : (List<Board>) boardService.findAll()) {
			Board board2 = new Board();
			board2.setId(board.getId());
			board2.setNombre(board.getNombre());
			board2.setUsuario(board.getUsuario());
			
			List<Ciudad> ciudades = new ArrayList<>();
			int i = 1;
			for (Ciudad ciudad : board.getCiudades()) {
				System.out.println("En ciudad: " + ciudad.getNombre());
				ciudades.add(ciudad);
				// board.getCiudades().add(ciudad);
			}
			board2.setCiudades(ciudades);
			listaBoards.add(board2);
		}
		return listaBoards;
	}
	
	@GetMapping("/boards4")
	public List<HashMap<String, Object>> get4() {
		List<HashMap<String, Object>> listado = new ArrayList<HashMap<String, Object>>();
		
		for (Board board : (List<Board>) boardService.findAll()) {
		HashMap<String, Object> map = new HashMap<>();
	    map.put("id", board.getId());
		map.put("nombre", board.getNombre());
	    map.put("usuario", board.getUsuario());
	    HashMap<String, Ciudad> ciudades = new HashMap<>();
	    
	    for (Ciudad ciudad : board.getCiudades()) {
			System.out.println("En ciudad: " + ciudad.getNombre());
			ciudades.put(ciudad.getId()+"", ciudad);
			//board.getCiudades().add(ciudad);
			}
	    map.put("ciudades", ciudades);
		listado.add(map);
		}
	    return listado;
	}
	
	/* 
	 @GetMapping("/boards3")
     public HashMap<String, Board> get3() {
     
         List<HashMap<String, Board>> listaBoards = new ArrayList<HashMap<String, Board>>();
         
         for (Board board : (List<Board>) boardService.findAll()) {
             HashMap<String, Object> mapBoard = new HashMap<String, Object>();
             
             mapBoard.put("id", board.getId());
             mapBoard.put("nombre", board.getNombre());
             mapBoard.put("usuario", board.getUsuario());
         
             int i = 1;
             List<HashMap> listaCiudades = new ArrayList<>();
             for (Ciudad ciudad : board.getCiudades()) {
                  HashMap<String, Object> mapCiudad = new HashMap<>();
                 mapCiudad.put("id", ciudad.getId());
                 mapCiudad.put("nombre", ciudad.getNombre());
                 mapCiudad.put("temperatura", ciudad.getTemperatura());
                 mapCiudad.put("fecha", ciudad.getFecha());
                 listaCiudades.add(mapCiudad);
             }
             
             mapBoard.put("ciudades", listaCiudades);
             listaBoards.addAll("",mapBoard);
         }
         
         return  listaBoards;
     }
	 

     @GetMapping("/boards4")
     public HashMap<String, Object> get4() {
     
         List<HashMap> listaBoards = new ArrayList<>();
         
         for (Board board : (List<Board>) boardService.findAll()) {
         
             Board b = new Board();
             b.setId(board.getId());
             b.setNombre(board.getNombre());
             b.setUsuario(board.getUsuario());
         
             List<HashMap> listaCiudades = new ArrayList<>();
             
             for (Ciudad ciudad : board.getCiudades()) {
                 Ciudad c = new Ciudad();
                 c.setId(ciudad.getId());
                 c.setNombre(ciudad.getNombre());
                 c.setTemperatura(ciudad.getTemperatura());
                 c.setFecha(ciudad.getFecha());
                 listaCiudades.add(1,c);
             }
             
             b.setCiudades(listaCiudades);
             
             listaBoards.add(b);
         }
         
         return listaBoards;
     }*/
	 
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
