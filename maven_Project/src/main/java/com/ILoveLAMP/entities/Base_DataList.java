package com.ILoveLAMP.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // this is required to enable JSON serialization
public class Base_DataList implements Serializable{

	private static final long serialVersionUID = 1L;

	public Collection<Base_Data> getBaseDataCollection(){
		return baseDataCollection;
	}
	
	public void setBaseDataCollection(Collection<Base_Data> baseDataCollection){
		this.baseDataCollection = baseDataCollection;
	}
	
	private Collection<Base_Data> baseDataCollection;
}
