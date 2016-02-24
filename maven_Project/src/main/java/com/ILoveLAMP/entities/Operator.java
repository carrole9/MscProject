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
@Entity @Table(name="operators")
@XmlRootElement
public class Operator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id //signifies the primary key
	@Column(name = "op_PK", nullable = false, unique=true)
	private Integer opId;
	
	@Column(name = "mcc")
	private Integer mcc;
	
	@Column(name = "mnc")
	private Integer mnc;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "operator_name")
	private String operatorName;
	
	// adding bi-directional relationship
	@OneToMany(mappedBy = "operator")
	private List<Base_Data> baseData;

	public Operator() {}

	public Operator(Integer opId, Integer mcc, Integer mnc, String country,
			String operatorName) {
		this.opId = opId;
		this.mcc = mcc;
		this.mnc = mnc;
		this.country = country;
		this.operatorName = operatorName;
	}

	public Integer getOpId() {
		return opId;
	}

	public void setOpId(Integer opId) {
		this.opId = opId;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public List<Base_Data> getBaseData() {
		return baseData;
	}

	public void setBaseData(List<Base_Data> baseData) {
		this.baseData = baseData;
	}

	
}
