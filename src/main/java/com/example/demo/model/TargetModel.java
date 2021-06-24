package com.example.demo.model;
import java.sql.Timestamp;

import com.example.demo.model.Entity.TestTableEntity;
import com.example.demo.model.base.TableBaseModel;

public class TargetModel extends TableBaseModel{
		
    private TestTableEntity entityData = null;
	
    public TargetModel() {
    	setTableName("test_table");
    }
    
    public TargetModel(TestTableEntity entity) {
    	setTableName("test_table");
    	entityData = entity;
    }
    
    public String getId() {
    	return entityData.getId();
    }
    
    public void setId(String id) {
    	entityData.setId(id);
    }
        
	public Integer getNumber() {
		return entityData.getNumber();
	}
	
	public void setNumber(Integer number) {
		entityData.setNumber(number);
	}
	
	public Timestamp getDate() {
		return entityData.getDate();
	}
	
	public void setDate(Timestamp date) {
		entityData.setDate(date);
	}
	
 }