package com.dariojolo.backend.model.services;

import java.util.List;


import com.dariojolo.backend.models.entities.Board;

public interface IBoardService {

	public List<Board>findAll();
	public void save(Board board);
}
