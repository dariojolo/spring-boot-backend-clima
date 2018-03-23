package com.dariojolo.backend.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.dariojolo.backend.models.entities.Ciudad;

public interface ICiudadDao extends CrudRepository<Ciudad, Long>{

}
