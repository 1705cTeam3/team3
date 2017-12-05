package com.jk.provide.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.provide.entity.LoginUser;
import com.jk.provide.entity.PermissionBean;
import com.jk.provide.mapper.TextDao;
import com.jk.provide.mapper.TreeGridMapper;
import com.jk.provide.service.TextService;

@Service("userserviceLXX")
public class TextServiceImpl implements TextService {

	@Autowired
	private TextDao textDao;
	
	@Autowired
	private TreeGridMapper treeGridMapper;

	@Override
	public List<LoginUser> getAllUserList() {
		return textDao.getAllUserList();
	}

	@Override
	public String testRequest(LoginUser user) {
		return "success";
	}
	
	/**
	 * 树表数据
	 */
	@Override
	public List<PermissionBean> getSelTreeList() {
		// TODO Auto-generated method stub
		System.err.println("===========================getSelTreeList====================================");
		return treeGridMapper.getSelTreeList();
	}

}
