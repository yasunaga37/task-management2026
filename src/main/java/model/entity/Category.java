package model.entity;

import java.sql.Timestamp;

public class Category {
	private int id;
	private String name;
	private Timestamp updateDatatime;

	public Category() {}
	public Category(int id, String name, Timestamp updateDatatime) {
		this.id = id;
		this.name = name;
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

	public Timestamp getUpdateDatatime() {
		return updateDatatime;
	}

	public void setUpdateDatatime(Timestamp updateDatatime) {
		this.updateDatatime = updateDatatime;
	}
}
