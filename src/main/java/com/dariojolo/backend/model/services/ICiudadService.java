package com.dariojolo.backend.model.services;

import java.util.List;

import com.dariojolo.backend.models.entities.Ciudad;


public interface ICiudadService {
	
	public List<Ciudad>findAll();
	public Ciudad save(Ciudad ciudad);
	public void deleteById(Long id);
	public Ciudad findById(Long id);

}
