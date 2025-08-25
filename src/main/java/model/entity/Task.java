package model.entity;

import java.security.Timestamp;
import java.sql.Date;

public class Task {
	private int id;
	private String name;
	private String categoryName;
	private Date limitDate;
	private String userName;
	private String statuName;
	private String memo;
	private String deleteFlag;
	private Timestamp createDatatime;
	private Timestamp updateDatatime;

	public Task() {
	}

	public Task(int id, String name, String categoryName, Date limitDate, String userName, String statuName,
			String memo, String deleteFlag, Timestamp createDatatime, Timestamp updateDatatime) {
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
		this.limitDate = limitDate;
		this.userName = userName;
		this.statuName = statuName;
		this.memo = memo;
		this.deleteFlag = deleteFlag;
		this.createDatatime = createDatatime;
		this.updateDatatime = updateDatatime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatuName() {
		return statuName;
	}

	public void setStatuName(String statuName) {
		this.statuName = statuName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Timestamp getCreateDatatime() {
		return createDatatime;
	}

	public void setCreateDatatime(Timestamp createDatatime) {
		this.createDatatime = createDatatime;
	}

	public Timestamp getUpdateDatatime() {
		return updateDatatime;
	}

	public void setUpdateDatatime(Timestamp updateDatatime) {
		this.updateDatatime = updateDatatime;
	}

}
