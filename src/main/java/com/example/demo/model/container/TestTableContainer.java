package com.example.demo.model.container;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.TestTableModel;
import com.example.demo.model.Entity.TestTableEntity;

@Service
public class TestTableContainer{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// データの一覧を返す
	@Transactional
	public void findAll() {
		String query = "SELECT * from test_table";
		List<TestTableEntity> list = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(TestTableEntity.class));
		setModel(list);
		/*
		try {
			// 処理
			query = "insert into test_table (id, number, date) values('YYY', null, null)";
			jdbcTemplate.update(query);
			query = "insert into test_table (id, number, date) values('XXX', null, null)";
			jdbcTemplate.update(query);
		} catch (Exception e) {
			AppLog.getInstance().Log(e.getMessage(), "E", "TestId");
		}
		*/
		
	}
	
	private void setModel(List<TestTableEntity> list) {
		reSetContainer();
		int index = 1;
		for (TestTableEntity testTableEntity : list) {
			TestTableModel model = new TestTableModel(testTableEntity);
			model.setRownum(index);
			index += 1;
			data.add(model);
		}
	}

	// データを追加する
	public TestTableEntity save(TestTableEntity person) {
		return person;
	}

	private List<TestTableModel> data;
	
	/**
	 * @return data
	 */
	public List<TestTableModel> getData() {
		return data;
	}

	/**
	 * @param data セットする data
	 */
	public void setData(List<TestTableModel> data) {
		this.data = data;
	}
	
	private void reSetContainer() {
		
		if(this.data != null) {
			this.data.clear();
		}
		
		this.data = new ArrayList<TestTableModel>();
	}
}
