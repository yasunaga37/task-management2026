package model.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Task {
	private int id;
	private String name;
	private String categoryName;
	private Date limitDate;
	private String userName;
	private String statuName;
	private String memo;
	private String deleteFlag;
	private Timestamp createDatetime;
	private Timestamp updateDatetime;

	public Task() {
	}

	public Task(int id, String name, String categoryName, Date limitDate, String userName, String statuName,
			String memo, String deleteFlag, Timestamp createDatetime, Timestamp updateDatetime) {
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
		this.limitDate = limitDate;
		this.userName = userName;
		this.statuName = statuName;
		this.memo = memo;
		this.deleteFlag = deleteFlag;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
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

	public Timestamp getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(java.sql.Timestamp create_datetime) {
		this.createDatetime = create_datetime;
	}

	public Timestamp getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Timestamp updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
