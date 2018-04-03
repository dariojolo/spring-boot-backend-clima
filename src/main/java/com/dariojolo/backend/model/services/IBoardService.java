package com.dariojolo.backend.model.services;

import java.util.HashSet;
import java.util.List;

import javax.websocket.ClientEndpoint;

import com.dariojolo.backend.models.entities.Board;
import com.dariojolo.backend.models.entities.Ciudad;

public interface IBoardService {

	public List<Board>findAll();
	public Board findByNombre(String nombre);
	public List<Board> findByUsuario(String usuario);
	public Board save(Board board);
	public void delete(Long id);
	public Board findById(Long id);
	
}
