package com.dariojolo.backend.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dariojolo.backend.models.entities.Board;

public interface IBoardDao extends CrudRepository<Board, Long>{
	public Board findByNombre(String nombre);
}
