package com.ILoveLAMP.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

//add an annotations specifying the table that this will map to
@Entity
@Table(name = "base_datas")
// ensure that the class implements Serializable

@NamedQueries({
	@NamedQuery(name = "Base_Data.getAll", query = "SELECT b FROM Base_Data b"),
	@NamedQuery(name = "Base_Data.getAllIMSI", query = "SELECT distinct b.imsi FROM Base_Data b"),
	@NamedQuery(name = "Base_Data.getAllFailureID", query = "SELECT distinct b.failure.description FROM Base_Data b"),
	@NamedQuery(name = "Base_Data.getAllManufacturers", query = "SELECT distinct b.userEquipment.manufacturer FROM Base_Data b"),
	@NamedQuery(name = "Base_Data.getAllModels", query = "SELECT distinct b.userEquipment.model FROM Base_Data b where b.userEquipment.manufacturer = :model"),
	@NamedQuery(name = "Base_Data.findById", query = "select bd from Base_Data bd where bd.dataId=:id"),
	@NamedQuery(name = "Base_Data.findByTime", query = "select bd from Base_Data bd where bd.dateTime=:time"),
	@NamedQuery(name = "Base_Data.IMSIandCallFailuresfindByTime", query = "select distinct bd.imsi from Base_Data bd where bd.dateTime>=:firsttime and bd.dateTime<=:secondtime"),
	@NamedQuery(name = "Base_Data.EventandCausecodeByIMSI", query = "select distinct bd.eventCause from Base_Data bd where bd.imsi = :imsi"),
	@NamedQuery(name = "Base_Data.FailurebyTimeandIMSI", query = "select count(bd.failure) from Base_Data bd where bd.dateTime>=:firsttime and bd.dateTime<=:secondtime and bd.imsi = :imsi"),
	
	@NamedQuery(name = "Base_Data.getIMSIbyIdfailure", query = "select distinct bd.imsi from Base_Data bd where bd.failure.description = :failId"),			
	
	@NamedQuery(name = "Base_Data.findByPeriod", query = "select count(bd) from Base_Data bd where (bd.dateTime between :startTime and :endTime)"),				
	@NamedQuery(name = "Base_Data.findByPeriodAndModel", query = "select count(bd) from Base_Data bd where (bd.userEquipment=:userEquipment) and (bd.dateTime between :startTime and :endTime)"),			
	@NamedQuery(name = "Base_Data.findBaseDatasByPeriod", query ="select bd from Base_Data bd where (bd.dateTime between :startTime and :endTime)"),
	@NamedQuery(name = "Base_Data.findImsiByPeriod", query ="select bd.imsi from Base_Data bd where (bd.dateTime between :startTime and :endTime) group by bd.imsi"),
	@NamedQuery(name = "Base_Data.countNoOfFailuresByPeriodAndImsi", query ="select count(bd) from Base_Data bd where (bd.imsi =:imsi) and (bd.dateTime between :startTime and :endTime)"),
	@NamedQuery(name = "Base_Data.getTotalDurationByPeriodAndImsi", query ="select sum(bd.duration) from Base_Data bd where (bd.imsi =:imsi) and (bd.dateTime between :startTime and :endTime)"),
	@NamedQuery(name = "Base_Data.findIMSIbyFailureID", query = "SELECT bd.imsi FROM Base_Data bd where bd.failure.failureId=:failId GROUP BY bd.imsi"),
	@NamedQuery(name = "Base_Data.findTop10IMSIinDateRange", query = "SELECT bd.imsi, COUNT(bd.imsi) FROM Base_Data bd WHERE bd.dateTime BETWEEN :startDate AND :endDate GROUP BY bd.imsi"),
	@NamedQuery(name = "Base_Data.getNoOfFailuresTotalDurationsForEachImsiByPeriod", query = "select bd.imsi, count(bd), sum(bd.duration) from Base_Data bd where (bd.dateTime BETWEEN :startTime AND :endTime) group by bd.imsi"),
	@NamedQuery(name = "Base_Data.FailureCauseCodeAndOcurrences", query = "select distinct bd.failure.failureId,bd.failure.description,bd.eventCause.causeCode,bd.eventCause.eventId,bd.eventCause.description,count(bd)"
			+ "from Base_Data bd "
			+ "where bd.userEquipment.model=:model "
			+ "group by bd.failure.failureId,bd.eventCause.ecId"),
	@NamedQuery(name = "Base_Data.UniqueCauseCodesForIMSI", query = "select distinct bd.failure.failureId,bd.failure.description, bd.eventCause.causeCode, bd.eventCause.description from Base_Data bd where bd.imsi = :imsi"),
	
	@NamedQuery(name = "Base_Data.Top10MarketOperatorCellIdCombo", query = "select bd.operator.mcc, bd.operator.operatorName,  bd.cellId from Base_Data bd where bd.dateTime BETWEEN :firsttime AND :secondtime GROUP BY bd.operator, bd.cellId ORDER BY bd.cellId")
})
@XmlRootElement
public class Base_Data implements Serializable {

	private static final long serialVersionUID = 1L;

	// add an attribute specifying a column for the id property
	// add attributes to ensure that the id column is automantically generated

	@Id
	// signifies the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "data_id")
	private Integer dataId;

	// add attributes for all the remaining properties
	// The type used in mapping java.util.Date or java.util.Calendar. and Map as
	// java.sql.Date
	// @Temporal(TemporalType.TIMESTAMP)

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time")
	private Date dateTime;

	@Column(name = "cell_id")
	private Integer cellId;

	@Column(name = "duration")
	private Integer duration;

	@Column(name = "ne_version")
	private String neVersion;

	@Column(name = "imsi")
	private String imsi;

	@Column(name = "hier3_id")
	private String hier3Id;

	@Column(name = "hier32_id")
	private String hier32Id;

	@Column(name = "hier321_id")
	private String hier321Id;

	// adding relationships

	@ManyToOne
	@JoinColumn(name = "failure_id", referencedColumnName = "failure_id")
	private Failure failure;

	@ManyToOne
	@JoinColumn(name = "ec_PK", referencedColumnName = "ec_PK")
	private Event_Cause eventCause;

	@ManyToOne
	@JoinColumn(name = "op_PK", referencedColumnName = "op_PK")
	private Operator operator;

	@ManyToOne
	@JoinColumn(name = "ue_id", referencedColumnName = "ue_id")
	private User_Equipment userEquipment;

	public Base_Data() {
	}
	
	public Base_Data(String imsi,Failure failure) {
		this.imsi=imsi;
		this.failure = failure;
	}

	public Base_Data(Integer dataId, Date dateTime, Integer cellId,
			Integer duration, String neVersion, String imsi, String hier3Id,
			String hier32Id, String hier321Id, Failure failure,
			Event_Cause eventCause, Operator operator,
			User_Equipment userEquipment) {
		this.dataId = dataId;
		this.dateTime = dateTime;
		this.cellId = cellId;
		this.duration = duration;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3Id = hier3Id;
		this.hier32Id = hier32Id;
		this.hier321Id = hier321Id;
		this.failure = failure;
		this.eventCause = eventCause;
		this.operator = operator;
		this.userEquipment = userEquipment;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public String getDateTime() {
		return dateTime.toString();
	}

	public void setDateTime(Date dateTime2) {
		this.dateTime = dateTime2;
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

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getHier3Id() {
		return hier3Id;
	}

	public void setHier3Id(String hier3Id) {
		this.hier3Id = hier3Id;
	}

	public String getHier32Id() {
		return hier32Id;
	}

	public void setHier32Id(String hier32Id) {
		this.hier32Id = hier32Id;
	}

	public String getHier321Id() {
		return hier321Id;
	}

	public void setHier321Id(String hier321Id) {
		this.hier321Id = hier321Id;
	}

	public Failure getFailure() {
		return failure;
	}

	public void setFailure(Failure failure) {
		this.failure = failure;
	}

	public Event_Cause getEventCause() {
		return eventCause;
	}

	public void setEventCause(Event_Cause eventCause) {
		this.eventCause = eventCause;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public User_Equipment getUserEquipment() {
		return userEquipment;
	}

	public void setUserEquipment(User_Equipment userEquipment) {
		this.userEquipment = userEquipment;
	}

}