package com.s4you.flybeau.webapi.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.s4you.flybeau.dto.SpeciesDTO;
import com.s4you.flybeau.webapi.service.SpeciesMngmApiService;

@Path("/species")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON  + "; charset=UTF8")
public class SpeciesMngmApiController {
	
	SpeciesMngmApiService speciesApiService = new SpeciesMngmApiService();
	
	@Path("/list")
	@GET
	public List<SpeciesDTO> list(@Context HttpHeaders headers){
		List<SpeciesDTO> all = speciesApiService.getAll();
		return all;
	}

}
