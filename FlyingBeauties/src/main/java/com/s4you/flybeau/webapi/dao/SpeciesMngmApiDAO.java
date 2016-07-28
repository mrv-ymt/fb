package com.s4you.flybeau.webapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.s4you.flybeau.dbconnection.DatabaseConnection;
import com.s4you.flybeau.dto.SpeciesDTO;

@Repository
@Transactional
public class SpeciesMngmApiDAO {

	public List<SpeciesDTO> getAll() {
		String sql =  "SELECT * FROM fb_specieslist";
		List<SpeciesDTO> listSpecies = new ArrayList<SpeciesDTO>();		

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				SpeciesDTO speciesDTO = new SpeciesDTO();
				speciesDTO.setCompetitionId(resultSet.getInt("CompetitionId"));
				speciesDTO.setPtnameId(resultSet.getInt("ptname_id"));
				speciesDTO.setLangiso3(resultSet.getString("langiso3"));
				speciesDTO.setTaxonId(resultSet.getInt("taxonId"));
				speciesDTO.setNameId(resultSet.getInt("nameId"));
				speciesDTO.setSelected(BooleanUtils.toBoolean(resultSet.getInt("selected")));
				speciesDTO.setTaxonName(StringUtils.trimToEmpty(resultSet.getString("taxonName")));
				speciesDTO.setSeq(resultSet.getInt("seq"));
				speciesDTO.setGroupId(resultSet.getInt("groupId"));
				speciesDTO.setGroup(BooleanUtils.toBoolean(resultSet.getInt("isGroup")));
				
				listSpecies.add(speciesDTO);
				
			}
			return listSpecies;
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}

}
