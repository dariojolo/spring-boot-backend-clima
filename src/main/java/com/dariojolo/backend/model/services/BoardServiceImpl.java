package com.dariojolo.backend.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dariojolo.backend.models.dao.IBoardDao;
import com.dariojolo.backend.models.dao.ICiudadDao;
import com.dariojolo.backend.models.entities.Board;

public class BoardServiceImpl implements IBoardService{

	@Autowired
	private IBoardDao boardDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Board> findAll() {
		// TODO Auto-generated method stub
		return (List<Board>) boardDao.findAll();
	}

	@Override
	public void save(Board board) {
		// TODO Auto-generated method stub
		boardDao.save(board);
	}

}
