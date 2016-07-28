package com.s4you.flybeau.webapi.service;

import java.util.List;

import com.s4you.flybeau.dto.SpeciesDTO;
import com.s4you.flybeau.webapi.dao.SpeciesMngmApiDAO;

public class SpeciesMngmApiService {
	SpeciesMngmApiDAO speciesDao = new SpeciesMngmApiDAO();

	public List<SpeciesDTO> getAll() {
		return speciesDao.getAll();
	}

}
