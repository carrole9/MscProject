package com.ILoveLAMP.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

//add an annotations specifying the table that this will map to
@Entity @Table(name="event_causes")
@XmlRootElement
public class Event_Cause implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id //signifies the primary key
	@Column(name = "ec_PK", nullable = false,unique=true)
	private Integer ecId;

	@Column(name = "cause_code")
	private Integer causeCode;

	@Column(name = "event_id")
	private Integer eventId;

	@Column(name = "description")
	private String description;

	// adding bi-directional mapping
	@OneToMany(mappedBy = "eventCause")
	private List<Base_Data> baseData;

	public Event_Cause() {}

	public Event_Cause(Integer ecId, Integer causeCode, Integer eventId,
			String description) {
		this.ecId = ecId;
		this.causeCode = causeCode;
		this.eventId = eventId;
		this.description = description;
	}

	public Integer getEcId() {
		return ecId;
	}

	public void setEcId(Integer ecId) {
		this.ecId = ecId;
	}

	public Integer getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(Integer causeCode) {
		this.causeCode = causeCode;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
