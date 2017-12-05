package com.jk.provide.service;

import java.util.List;

import com.jk.provide.entity.LoginUser;
import com.jk.provide.entity.PermissionBean;

public interface TextService {

	String testRequest(LoginUser user);

	List<LoginUser> getAllUserList();

	List<PermissionBean> getSelTreeList();
}
