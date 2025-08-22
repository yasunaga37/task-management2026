package model.entity;

import java.sql.Date;

public class User {
	private String userId;
	private String password;
	private Date updateDatatime;

	public User() {}
	public User(String userId, String password, Date updateDatatime) {
		this.userId = userId;
		this.password = password;
		this.updateDatatime = updateDatatime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUpdateDatatime() {
		return updateDatatime;
	}

	public void setUpdateDatatime(Date updateDatatime) {
		this.updateDatatime = updateDatatime;
	}
}
