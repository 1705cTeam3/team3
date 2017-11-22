package com.jk.provide.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.TextDao;
import com.jk.provide.entity.LoginUser;
import com.jk.provide.service.TextService;

@Service("userservice")
public class TextServiceImpl implements TextService {

	@Autowired
	private TextDao textDao;

	@Override
	public List<LoginUser> getAllUserList() {
		return textDao.getAllUserList();
	}

	@Override
	public String testRequest(LoginUser user) {
		return "success";
	}

}
