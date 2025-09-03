package model.entity;

import java.sql.Timestamp;

public class User {
	private String id;
	private String password;
	private String name;
	private Timestamp updateDatatime;

	public User() {}
	public User(String id, String password, String name, Timestamp updateDatatime) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.updateDatatime = updateDatatime;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getUpdateDatatime() {
		return updateDatatime;
	}

	public void setUpdateDatatime(Timestamp updateDatatime) {
		this.updateDatatime = updateDatatime;
	}
}
