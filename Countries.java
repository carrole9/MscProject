package com.ILoveLAMP.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Countries implements Serializable {
	
	@Id
	@Column(name="mcc",nullable = false)  	private Integer mcc;
	@Column(name="country") private String country;
	
	
	public Countries(){}
	
	public Countries(Integer mcc,String country) {
		
		this.mcc = mcc;
		this.country = country;
		
	}

	public Integer getMcc() {
		return mcc;
	}

	public void setMcc(Integer mcc) {
		this.mcc = mcc;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	

}
