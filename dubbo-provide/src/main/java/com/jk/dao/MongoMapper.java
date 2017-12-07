package com.jk.dao;

import java.util.List;
import java.util.Map;

import com.jk.provide.entity.Operationlogs;

public interface MongoMapper {
	

	int getCount();

	List<Operationlogs> getListAll(Map<String, Object> map);

}
