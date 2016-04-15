package com.ILoveLAMP.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//add an annotations specifying the table that this will map to
@Entity @Table(name="errors")

//ensure that the class implements Serializable
@NamedQueries({
	@NamedQuery(name = "Error_Data.getAll", query = "SELECT ed FROM Error_Data ed"),
	@NamedQuery(name = "Error_Data.findById", query = "select ed from Error_Data ed where ed.dataId=:id"), 

})


public class Error_Data implements Serializable{

	private static final long serialVersionUID = 1L;

	// add an attribute specifying a column for the id property
	// add attributes to ensure that the id column is automantically generated

	@Id //signifies the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "error_id")
	private Integer dataId;

	// add attributes for all the remaining properties
	//  The type used in mapping java.util.String or java.util.Calendar. and Map as java.sql.Date
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "error_time")
	private String dateTime;

	@Column(name = "error_event_id")
	private String eventId;

	@Column(name = "error_failure_id")
	private String failureId;

	@Column(name = "error_ue_type")
	private String ueType;

	@Column(name = "error_market")
	private String market;

	@Column(name = "error_operator")
	private String operator;

	@Column(name = "error_cellId")
	private String cellId;

	@Column(name = "error_duration")
	private String duration;

	@Column(name = "error_cause_Code")
	private String cause_Code;

	@Column(name = "error_ne_version")
	private String neVersion;

	@Column(name = "error_imsi")
	private String imsi;

	@Column(name = "error_hier3_id")
	private String hier3_Id;

	@Column(name = "error_hier32_id")
	private String hier32_Id;

	@Column(name = "error_hier321_id")
	private String hier321_Id;

	public Error_Data() {}

	public Error_Data(Integer dataId, String dateTime, String eventId,
			String failureId, String ueType, String market, String operator,
			String cellId, String duration, String cause_Code,
			String neVersion, String imsi, String hier3_Id, String hier32_Id,
			String hier321_Id) {
		this.dataId = dataId;
		this.dateTime = dateTime;
		this.eventId = eventId;
		this.failureId = failureId;
		this.ueType = ueType;
		this.market = market;
		this.operator = operator;
		this.cellId = cellId;
		this.duration = duration;
		this.cause_Code = cause_Code;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3_Id = hier3_Id;
		this.hier32_Id = hier32_Id;
		this.hier321_Id = hier321_Id;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getFailureId() {
		return failureId;
	}

	public void setFailureId(String failureId) {
		this.failureId = failureId;
	}

	public String getUeType() {
		return ueType;
	}

	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCellId() {
		return cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCause_Code() {
		return cause_Code;
	}

	public void setCause_Code(String cause_Code) {
		this.cause_Code = cause_Code;
	}

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getHier3_Id() {
		return hier3_Id;
	}

	public void setHier3_Id(String hier3_Id) {
		this.hier3_Id = hier3_Id;
	}

	public String getHier32_Id() {
		return hier32_Id;
	}

	public void setHier32_Id(String hier32_Id) {
		this.hier32_Id = hier32_Id;
	}

	public String getHier321_Id() {
		return hier321_Id;
	}

	public void setHier321_Id(String hier321_Id) {
		this.hier321_Id = hier321_Id;
	}
		
}
