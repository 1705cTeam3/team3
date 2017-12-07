package com.jk.dao;

import java.util.HashMap;
import java.util.List;

import com.jk.provide.entity.LoginUser;
import com.jk.provide.entity.MenuModel;
import com.jk.provide.entity.Users;

public interface TextDao {

	List<LoginUser> getAllUserList();

	List<Users> queryLogin(Users user);

	Users queryLoginById(String loginid);

	List<MenuModel> queryMenu();
	
	// lxx 2017年11月26日 15:31:06
	int getCount(HashMap<String, Object> map);

	//lxx 2017年11月26日 15:31:12
	List<Users> getListAll(HashMap<String, Object> map);

	//lxx 2017年11月26日 17:44:18 删除当前行
	void delUsers(Integer userId);


}
