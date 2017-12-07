package com.jk.provide.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.jk.dao.MongoMapper;
import com.jk.provide.entity.Operationlogs;
import com.jk.provide.service.MongoService;
import com.jk.util.Page;

@Service("mongoService")
public class MongoServiceImpl implements MongoService {
	
	@Autowired
	private MongoMapper mongoMapper;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * lxx
	 * 2017年11月30日 19:47:35
	 * mongo操作日志分页查询
	 */
	@Override
	public int getCount(String page, String rows) {
		
		List<Operationlogs> findAll = mongoTemplate.findAll(Operationlogs.class);
		
		return findAll.size();
	}
	
	@Override
	public List<Operationlogs> getListAll(String page, String rows) {
		int pageInt = page ==null? 1 :Integer.parseInt(page);
		int rowsInt = rows ==null? 5 :Integer.parseInt(rows);
		
		Page pageModel = new Page();
		
		pageModel.setPage(pageInt);
		pageModel.setRows(rowsInt);
//		根据page，rows计算其他参数
		pageModel.calculate();
		
//		将开始条数，每页条数放入map最为参数传入获得list的方法，为xml  limit语句分页准备  limit start，size
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageModel.getStartIndex());
		map.put("size", pageModel.getRows());
		Query query = new Query();
		query.skip((int) map.get("start"));
		query.limit((int) map.get("size"));
		List<Operationlogs> findAll = mongoTemplate.find(query, Operationlogs.class);
		return findAll;
	}
	
	/**
	 * lxx
	 * 2017年12月1日 20:11:45
	 * mongo 备份日志 分页查询
	 */
	@Override
	public int getCountForBackUp(String page, String rows) {
		// TODO Auto-generated method stub
		Query query = queryCondition();
		List<Operationlogs> list = mongoTemplate.find(query, Operationlogs.class);
		return list.size();
	}
	
	@Override
	public List<Operationlogs> getListAllForBackUp(String page, String rows) {
		int pageInt = page ==null? 1 :Integer.parseInt(page);
		int rowsInt = rows ==null? 5 :Integer.parseInt(rows);
		
		Page pageModel = new Page();
		
		pageModel.setPage(pageInt);
		pageModel.setRows(rowsInt);
//		根据page，rows计算其他参数
		pageModel.calculate();
		
//		将开始条数，每页条数放入map最为参数传入获得list的方法，为xml  limit语句分页准备  limit start，size
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageModel.getStartIndex());
		map.put("size", pageModel.getRows());
		//拼条件
		Query query = queryCondition();
		query.skip((int) map.get("start"));
		query.limit((int) map.get("size"));
		List<Operationlogs> findAll = mongoTemplate.find(query, Operationlogs.class);
		return findAll;
	}

	private Query queryCondition() {
		Query query = new Query();
		Criteria criteria = new Criteria();
		Pattern pattern = Pattern.compile("^.*"+"findDbBackUpAllList"+".*$",Pattern.CASE_INSENSITIVE);
		Criteria whereType = Criteria.where("operMethod").regex(pattern);
		query.addCriteria(whereType);
		return query;
	}
}
