package com.dariojolo.backend.model.services;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariojolo.backend.models.dao.IBoardDao;
import com.dariojolo.backend.models.entities.Board;

@Service
public class BoardServiceImpl implements IBoardService{

	@Autowired
	private IBoardDao boardDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Board> findAll() {
		return (List<Board>) boardDao.findAll();
	}

	@Override
	public void save(Board board) {
		boardDao.save(board);
	}

}
