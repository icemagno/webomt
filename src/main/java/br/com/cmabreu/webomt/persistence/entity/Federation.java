package br.com.cmabreu.webomt.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="federations") 
public class Federation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_federation")
	private int idFederation;

	@ManyToOne
	@JoinColumn(name="id_user", foreignKey = @ForeignKey(name = "fk_fed_user"))
	@Fetch(FetchMode.JOIN)
	private User owner;
	
	@Column
	private Boolean visible = false;
	
	@Column(length=150)
	private String name;

	@Column(length=15, unique=true)
	private String serial;

	@Column(columnDefinition = "TEXT")
	private String glyph;

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
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public User getOwner() {
		return owner;
	}

	public Boolean isVisible() {
		return visible;
	}
	
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getGlyph() {
		return glyph;
	}

	public void setGlyph(String glyph) {
		this.glyph = glyph;
	}

	public Boolean getVisible() {
		return visible;
	}
	
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public String getSerial() {
		return serial;
	}
	
}
