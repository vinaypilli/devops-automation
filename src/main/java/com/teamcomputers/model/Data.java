package com.teamcomputers.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="data")
public class Data {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Data(String id) {
		super();
		this.id = id;
	}

}
