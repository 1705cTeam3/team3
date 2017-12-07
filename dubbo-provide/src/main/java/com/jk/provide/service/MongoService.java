package com.jk.provide.service;

import java.util.List;

import com.jk.provide.entity.Operationlogs;

public interface MongoService {


	List<Operationlogs> getListAll(String page, String rows);

	int getCount(String page, String rows);

	List<Operationlogs> getListAllForBackUp(String page, String rows);

	int getCountForBackUp(String page, String rows);

}
