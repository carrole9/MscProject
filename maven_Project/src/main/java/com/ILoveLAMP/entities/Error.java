package com.ILoveLAMP.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//add an annotations specifying the table that this will map to
@Entity @Table(name="errors")

//ensure that the class implements Serializable
//@NamedQueries({
//	@NamedQuery(name = "Base_Data.getAll", query = "SELECT b FROM Base_Data b"),
//	@NamedQuery(name = "Base_Data.findById", query = "select bd from Base_Data bd where bd.dataId=:id"), 
//
//})


public class Error implements Serializable{

	private static final long serialVersionUID = 1L;

	// add an attribute specifying a column for the id property
	// add attributes to ensure that the id column is automantically generated

	@Id //signifies the primary key
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "error_id", nullable = false)
	private Integer dataId;

	// add attributes for all the remaining properties
	//  The type used in mapping java.util.Date or java.util.Calendar. and Map as java.sql.Date
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "error_date_time")
	private Date dateTime;

	@Column(name = "error_cell_id")
	private Integer cellId;

	@Column(name = "error_duration")
	private Integer duration;

	@Column(name = "error_ne_version")
	private String neVersion;

	@Column(name = "error_imsi")
	private BigInteger imsi;

	@Column(name = "error_hier3_id")
	private BigInteger hier3Id;

	@Column(name = "error_hier32_id")
	private BigInteger hier32Id;

	@Column(name = "error_failure_id")
	private Integer failureId;

	@Column(name = "error_ue_id")
	private Integer ueId;
	
	@Column(name = "error_op_PK")
	private Integer operator;
	
	@Column(name = "error_ec_PK")
	private Integer eventCause;

	public Error() {}

	public Error(Integer dataId, Date dateTime, Integer cellId,
			Integer duration, String neVersion, BigInteger imsi,
			BigInteger hier3Id, BigInteger hier32Id, Integer failureId,
			Integer ueId, Integer operator, Integer eventCause) {
		this.dataId = dataId;
		this.dateTime = dateTime;
		this.cellId = cellId;
		this.duration = duration;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3Id = hier3Id;
		this.hier32Id = hier32Id;
		this.failureId = failureId;
		this.ueId = ueId;
		this.operator = operator;
		this.eventCause = eventCause;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getCellId() {
		return cellId;
	}

	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public BigInteger getImsi() {
		return imsi;
	}

	public void setImsi(BigInteger imsi) {
		this.imsi = imsi;
	}

	public BigInteger getHier3Id() {
		return hier3Id;
	}

	public void setHier3Id(BigInteger hier3Id) {
		this.hier3Id = hier3Id;
	}

	public BigInteger getHier32Id() {
		return hier32Id;
	}

	public void setHier32Id(BigInteger hier32Id) {
		this.hier32Id = hier32Id;
	}

	public Integer getFailureId() {
		return failureId;
	}

	public void setFailureId(Integer failureId) {
		this.failureId = failureId;
	}

	public Integer getUeId() {
		return ueId;
	}

	public void setUeId(Integer ueId) {
		this.ueId = ueId;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Integer getEventCause() {
		return eventCause;
	}

	public void setEventCause(Integer eventCause) {
		this.eventCause = eventCause;
	}
	
	
	
}
