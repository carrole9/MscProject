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
@Entity @Table(name="user_equipments")
@XmlRootElement
//ensure that the class implements Serializable
@NamedQueries({
	@NamedQuery(name = "User_Equipment.getAll", query = "SELECT ue FROM User_Equipment ue"),
	@NamedQuery(name = "User_Equipment.findById", query = "select ue from User_Equipment ue where ue.userEquipmentId=:id"), 

})
public class User_Equipment implements Serializable{

	private static final long serialVersionUID = 1L;

	// add an attribute specifying a column for the id property
		// add attributes to ensure that the id column is automantically generated

		@Id //signifies the primary key
		@Column(name = "ue_id", nullable = false, unique=true)
		private Integer userEquipmentId;
		
		@Column(name = "marketing_name")
		private String marketingName;
		
		@Column(name = "manufacturer")
		private String manufacturer;
		
		@Column(name = "access_capability")
		private String accessCapability;
		
		@Column(name = "model")
		private String model;
		
		@Column(name = "vendor_name")
		private String vendorName;
		
		@Column(name = "ue_type")
		private String ueType;
		
		@Column(name = "os")
		private String os;
		
		@Column(name = "input_mode")
		private String inputMode;
		
		// adding bi-directional mapping
		@OneToMany(mappedBy = "userEquipment")
		private List<Base_Data> baseData;

		public User_Equipment() {}

		public User_Equipment(Integer userEquipmentId, String marketingName,
				String manufacturer, String accessCapability, String model,
				String vendorName, String ueType, String os, String inputMode) {
			this.userEquipmentId = userEquipmentId;
			this.marketingName = marketingName;
			this.manufacturer = manufacturer;
			this.accessCapability = accessCapability;
			this.model = model;
			this.vendorName = vendorName;
			this.ueType = ueType;
			this.os = os;
			this.inputMode = inputMode;
		}

		public Integer getUserEquipmentId() {
			return userEquipmentId;
		}

		public void setUserEquipmentId(Integer userEquipmentId) {
			this.userEquipmentId = userEquipmentId;
		}

		public String getMarketingName() {
			return marketingName;
		}

		public void setMarketingName(String marketingName) {
			this.marketingName = marketingName;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public String getAccessCapability() {
			return accessCapability;
		}

		public void setAccessCapability(String accessCapability) {
			this.accessCapability = accessCapability;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getVendorName() {
			return vendorName;
		}

		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}

		public String getUeType() {
			return ueType;
		}

		public void setUeType(String ueType) {
			this.ueType = ueType;
		}

		public String getOs() {
			return os;
		}

		public void setOs(String os) {
			this.os = os;
		}

		public String getInputMode() {
			return inputMode;
		}

		public void setInputMode(String inputMode) {
			this.inputMode = inputMode;
		}

}
