package com.dariojolo.backend;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dariojolo.backend.model.services.IBoardService;
import com.dariojolo.backend.model.services.ICiudadService;
import com.dariojolo.backend.models.dao.IBoardDao;
import com.dariojolo.backend.models.dao.ICiudadDao;
import com.dariojolo.backend.models.entities.Board;
import com.dariojolo.backend.models.entities.Ciudad;

@SpringBootApplication
public class SpringBootBackendClimaApplication implements CommandLineRunner{

	@Autowired
	private IBoardService boardService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendClimaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		Board board2 = new Board();
		board2.setId(3L);
		board2.setNombre("Las ciudades de Dario");
		board2.setUsuario("Dario");
		
		Board board3 = new Board();
		board3.setId(4L);
		board3.setNombre("Nuevo board");
		board3.setUsuario("Pepe");
		
		Ciudad ciudad3 = new Ciudad();
		ciudad3.setNombre("Buenos Aires");
		ciudad3.setFecha(new Date());
		ciudad3.setTemperatura("22");
		ciudad3.setBoard(board2);
		
		Ciudad ciudad4 = new Ciudad();
		ciudad4.setNombre("New York");
		ciudad4.setFecha(new Date());
		ciudad4.setTemperatura("12");
		ciudad4.setBoard(board2);
		
		
		Ciudad ciudad5 = new Ciudad();
		ciudad5.setNombre("Santiago");
		ciudad5.setFecha(new Date());
		ciudad5.setTemperatura("12");
		ciudad5.setBoard(board2);
		
		Ciudad ciudad6 = new Ciudad();
		ciudad6.setNombre("New York");
		ciudad6.setFecha(new Date());
		ciudad6.setTemperatura("12");
		ciudad6.setBoard(board3);
		
		
		Ciudad ciudad7 = new Ciudad();
		ciudad7.setNombre("Santiago");
		ciudad7.setFecha(new Date());
		ciudad7.setTemperatura("12");
		ciudad7.setBoard(board3);
		
		
		board2.getCiudades().add(ciudad3);
		board2.getCiudades().add(ciudad4);
		board2.getCiudades().add(ciudad5);
		
		board3.getCiudades().add(ciudad6);
		board3.getCiudades().add(ciudad7);
		
		
		boardService.save(board2);
		
		boardService.save(board3);*/
        
	}
}
