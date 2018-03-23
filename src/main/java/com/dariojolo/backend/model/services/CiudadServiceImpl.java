package com.dariojolo.backend.model.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariojolo.backend.models.dao.ICiudadDao;
import com.dariojolo.backend.models.entities.Ciudad;

@Service
public class CiudadServiceImpl implements ICiudadService{

	@Autowired
	private ICiudadDao ciudadDao;
	@Override
	@Transactional(readOnly=true)
	public List<Ciudad> findAll() {
		// TODO Auto-generated method stub
		return (List<Ciudad>) ciudadDao.findAll();
	}
	@Override
	public void save(Ciudad ciudad) {
		// TODO Auto-generated method stub
		ciudadDao.save(ciudad);
		
	}

}
