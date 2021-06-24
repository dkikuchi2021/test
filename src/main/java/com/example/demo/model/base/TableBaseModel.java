package com.example.demo.model.base;

public class TableBaseModel {

	private String tableName;

	public void setTableName(String name) {
		this.tableName = name;
	}

	public String getTableName() {
		return this.tableName;
	}

	private int rownum;

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
}