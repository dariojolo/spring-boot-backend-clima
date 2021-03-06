package com.dariojolo.backend.model.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariojolo.backend.models.dao.ICiudadDao;
import com.dariojolo.backend.models.entities.Board;
import com.dariojolo.backend.models.entities.Ciudad;

@Service
public class CiudadServiceImpl implements ICiudadService{

    protected Logger logger = LoggerFactory.getLogger(CiudadServiceImpl.class);

    
	@Autowired
	private ICiudadDao ciudadDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Ciudad> findAll() {
		return (List<Ciudad>) ciudadDao.findAll();
	}
	@Override
	public Ciudad save(Ciudad ciudad) {
		return ciudadDao.save(ciudad);
		
	}
	@Override
	@Transactional
	public void deleteById(Long id) {
		ciudadDao.deleteById(id);
	}
	@Override
	@Transactional(readOnly=true)
	public Ciudad findById(Long id) {
		return ciudadDao.findById(id).orElse(null);
	}

}
