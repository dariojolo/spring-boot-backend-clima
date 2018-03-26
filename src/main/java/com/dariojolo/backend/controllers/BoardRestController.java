package com.dariojolo.backend.controllers;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class BoardRestController {
	
	@Autowired
	private IBoardService boardService;
	
	private List<Board> lista = new ArrayList<>();
	
	@GetMapping("/boards")
	public List<Board>index(){
		for (Board board : (List<Board>) boardService.findAll()) {
			System.out.println("En board: " + board);
			Board board2 = new Board();
			board2.setId(board.getId());
			board2.setNombre(board.getNombre());
			board2.setUsuario(board.getUsuario());
			for (Ciudad ciudad : board.getCiudades()) {
				System.out.println("En ciudad: " + ciudad);
				board2.getCiudades().add(ciudad);
			}
			lista.add(board2);
		}
		return lista;
	};	

}
