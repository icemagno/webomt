package br.com.cmabreu.webomt.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="federations") 
public class Federation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_federation")
	private int idFederation;

	@Column(length=150, unique=true)
	private String name;

	@Column(length=250)
	private String description;
	
	@Column(length=250)
	private String definitionFile;
	
	public int getIdFederation() {
		return idFederation;
	}

	public void setIdFederation(int idFederation) {
		this.idFederation = idFederation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefinitionFile() {
		return definitionFile;
	}

	public void setDefinitionFile(String definitionFile) {
		this.definitionFile = definitionFile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
