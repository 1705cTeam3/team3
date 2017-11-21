package com.jk.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jk.entity.LoginUser;

public interface TextService {
    
	String testRequest(LoginUser user);
	
	List<LoginUser> getAllUserList();
	
	Map<String, Object> queryByUserId(LoginUser lo);
}
