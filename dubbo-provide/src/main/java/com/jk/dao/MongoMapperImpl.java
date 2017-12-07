package com.jk.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.jk.provide.entity.Operationlogs;
@Repository
public class MongoMapperImpl implements MongoMapper{
	
	@Autowired()
	private MongoTemplate mongoTemplate;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		List<Operationlogs> findAll = mongoTemplate.findAll(Operationlogs.class);
		return findAll.size();
	}

	@Override
	public List<Operationlogs> getListAll(Map<String, Object> map) {
		/*//跳过多少条，设定起始页
		query.skip(wenZhangBean.getStartIndex());
		//设定每页条数
		query.limit(wenZhangBean.getRows());
		List<WenZhangBean> find = mongoTemplate.find(query, WenZhangBean.class);
		return find;*/
		Query query = new Query();
		query.skip((int) map.get("start"));
		query.limit((int) map.get("size"));
		List<Operationlogs> findAll = mongoTemplate.find(query, Operationlogs.class);
		return findAll;
	}

}
