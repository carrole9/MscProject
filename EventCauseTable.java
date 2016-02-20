package com.ILoveLAMP.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_causes")
public class EventCauseTable implements Serializable {

	@Id
	@Column(name="event_cause_id", nullable = false) 
	private Integer eventCauseId;
	
	@Column(name="mcc") 	 private Integer mcc;
	@Column(name="mnc") 	 private Integer mnc;
	@Column(name="operator") private Integer operator;
	
	public EventCauseTable(){}
	
	public EventCauseTable(Integer mcc,Integer mnc,Integer operator) {
		
		this.eventCauseId = eventCauseId;
		this.mcc = mcc;
		this.mnc = mnc;
		this.operator = operator;
	}

	public Integer getEventCauseId() {
		return eventCauseId;
	}

	public void setEventCauseId(Integer eventCauseId) {
		this.eventCauseId = eventCauseId;
	}

	public Integer getMcc() {
		return mcc;
	}

	public void setMcc(Integer mcc) {
		this.mcc = mcc;
	}

	public Integer getMnc() {
		return mnc;
	}

	public void setMnc(Integer mnc) {
		this.mnc = mnc;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	
	

}
