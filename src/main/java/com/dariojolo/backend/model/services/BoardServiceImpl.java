package com.dariojolo.backend.model.services;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariojolo.backend.models.dao.IBoardDao;
import com.dariojolo.backend.models.entities.Board;
import com.dariojolo.backend.models.entities.Ciudad;

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
	@Transactional
	public Board save(Board board) {
		return boardDao.save(board);
	}

	@Override
	@Transactional(readOnly=true)
	public Board findByNombre(String nombre) {
		return  (Board) boardDao.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Board> findByUsuario(String usuario) {
		return  (List<Board>) boardDao.findByUsuario(usuario);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		boardDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Board findById(Long id) {
		return boardDao.findById(id).orElse(null);
	}


}
