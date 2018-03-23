package com.dariojolo.backend.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.dariojolo.backend.models.entities.Board;

public interface IBoardDao extends CrudRepository<Board, Long>{

}
