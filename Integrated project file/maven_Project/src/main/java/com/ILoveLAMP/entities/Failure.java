package com.ILoveLAMP.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

//add an annotations specifying the table that this will map to
@Entity @Table(name="failures")
@XmlRootElement
//ensure that the class implements Serializable
@NamedQueries({
	@NamedQuery(name = "Failure.getAll", query = "SELECT f FROM Failure f"),
	@NamedQuery(name = "Failure.findById", query = "select f from Failure f where f.failureId=:id"), 

})
public class Failure implements Serializable{

	private static final long serialVersionUID = 1L;

	// add an attribute specifying a column for the id property
	// add attributes to ensure that the id column is automantically generated

	@Id //signifies the primary key
	@Column(name = "failure_id", nullable = false, unique=true)
	private Integer failureId;
	
	@Column(name = "description")
	private String description;
	
	// adding bi-directional mapping
	@OneToMany(mappedBy = "failure")
	private List<Base_Data> baseData;

	public Failure() {}

	public Failure(Integer failureId, String description) {
		this.failureId = failureId;
		this.description = description;
	}

	public Integer getFailureId() {
		return failureId;
	}

	public void setFailureId(Integer failureId) {
		this.failureId = failureId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
