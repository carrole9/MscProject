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

@Entity
@Table(name = "Users")

@NamedQueries({
	@NamedQuery(name = "User.getAll", query = "SELECT us FROM User us"),
	@NamedQuery(name = "User.findById", query = "select us from User us where us.id=:id"), 
	@NamedQuery(name = "User.getAllUsername", query = "select us.username from User us"),
})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	// add an attribute specifying a column for the id property
	// add attributes to ensure that the id column is automatically generated
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	// add attributes for all the remaining properties
	@Column(name = "username")
	String username;
	@Column(name = "password")
	String password;
	@Column(name = "usertype")
	String usertype;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}

	public User(String username, String password, String usertype, int id) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

}
