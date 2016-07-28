package com.s4you.flybeau.dto;

public class SpeciesDTO {
//	"CompetitionId" integer NOT NULL, -- Relation to Competition
//	  ptname_id integer, -- backward compatibility with S4PTNAME-table in science4you
//	  langiso3 character(3), -- for ISO 639-2 3-letter language code
//	  "taxonId" integer NOT NULL, -- accepted taxon
//	  "nameId" integer NOT NULL, -- Id of the name
//	  selected smallint, -- default 0, 1 for selected species
//	  "taxonName" character(100), -- common names and scientific names
//	  seq integer, -- ordering of the species
//	  "groupId" integer, -- taxonId fo the group
//	  "isGroup" smallint,
	private int competitionId;
	private int ptnameId;
	private String langiso3;
	private int taxonId;
	private int nameId;
	private boolean selected;
	private String taxonName;
	private int seq;
	private int groupId;
	private boolean isGroup;
	
	public int getCompetitionId() {
		return competitionId;
	}
	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
	}
	public int getPtnameId() {
		return ptnameId;
	}
	public void setPtnameId(int ptnameId) {
		this.ptnameId = ptnameId;
	}
	public String getLangiso3() {
		return langiso3;
	}
	public void setLangiso3(String langiso3) {
		this.langiso3 = langiso3;
	}
	public int getTaxonId() {
		return taxonId;
	}
	public void setTaxonId(int taxonId) {
		this.taxonId = taxonId;
	}
	public int getNameId() {
		return nameId;
	}
	public void setNameId(int nameId) {
		this.nameId = nameId;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getTaxonName() {
		return taxonName;
	}
	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public boolean isGroup() {
		return isGroup;
	}
	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}
}
