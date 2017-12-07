package com.jk.dao;

import java.util.HashMap;
import java.util.List;

import com.jk.provide.entity.BugBean;
import com.jk.provide.entity.Users;

public interface BugMapper {

	int getBugCount(HashMap<String, Object> map);

	List<BugBean> getBugListAll(HashMap<String, Object> map);
	
}