package model.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Task {
	private int id;
	private String name;
	private int catagoryId;
	private String categoryName;
	private Date limitDate;
	private String userId;
	private String userName;
	private String statusCode;
	private String statusName;
	private String memo;
	private String deleteFlag;
	private Timestamp createDatetime;
	private Timestamp updateDatetime;

	public Task() {}
	public Task(int id, String name, String categoryName, Date limitDate, String userName, String statusName,
			String memo, String deleteFlag, Timestamp createDatetime, Timestamp updateDatetime) {
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
		this.limitDate = limitDate;
		this.userName = userName;
		this.statusName = statusName;
		this.memo = memo;
		this.deleteFlag = deleteFlag;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}
	
	public Task(int id, String name, int catagoryId, String userId, String statusCode, 
			Date limitDate, String memo, String deleteFlag) {
		this.id = id;
		this.name = name;
		this.catagoryId = catagoryId;
		this.limitDate = limitDate;
		this.userId = userId;
		this.statusCode = statusCode;
		this.memo = memo;
		this.deleteFlag = deleteFlag;
	}

	public Task(int id, String name, int catagoryId, String categoryName, Date limitDate, String userId,
			String userName, String statusCode, String statusName, String memo, String deleteFlag,
			Timestamp createDatetime, Timestamp updateDatetime) {
		this.id = id;
		this.name = name;
		this.catagoryId = catagoryId;
		this.categoryName = categoryName;
		this.limitDate = limitDate;
		this.userId = userId;
		this.userName = userName;
		this.statusCode = statusCode;
		this.statusName = statusName;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
	public int getCatagoryId() {
		return catagoryId;
	}
	public void setCatagoryId(int catagoryId) {
		this.catagoryId = catagoryId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
