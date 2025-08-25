package model.entity;

import java.sql.Timestamp;

public class Status {
	private String code;
	private String name;
	private Timestamp updateDatatime;

	public Status() {}
	public Status(String code, String name, Timestamp updateDatatime) {
		this.code = code;
		this.name = name;
		this.updateDatatime = updateDatatime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
