package com.example.demo.model.Entity;

import java.sql.Timestamp;


public class TestTableEntity {

	private String id;

	private Integer number;

	private Timestamp date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	
}