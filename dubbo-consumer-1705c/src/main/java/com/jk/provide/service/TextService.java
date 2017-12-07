package com.jk.provide.service;

import java.util.HashMap;
import java.util.List;

import com.jk.provide.entity.BugBean;
import com.jk.provide.entity.LoginUser;
import com.jk.provide.entity.MenuModel;
import com.jk.provide.entity.PermissionBean;
import com.jk.provide.entity.Users;
import com.jk.provide.entity.UsersBean;

public interface TextService {

	String testRequest(LoginUser user);

	List<LoginUser> getAllUserList();
	
	List<Users> queryLogin(Users user);

	Users queryLoginById(String loginid);

	List<MenuModel> findMenuList();

	//lxx 2017年11月26日 15:30:06
	List<PermissionBean> getSelTreeList();

	//lxx 2017年11月26日 15:29:53
	List<Users> getListAll(HashMap<String, Object> map, String searchAnds, String searchColumnNames, String searchConditions, String searchVals);

	//lxx 2017年11月26日 15:30:01
	int getCount(HashMap<String, Object> map, String searchAnds, String searchColumnNames, String searchConditions, String searchVals);

	//lxx 2017年11月26日 17:43:05 删除当前行
	void delUsers(Integer userIdINT);

	//lxx 2017年11月27日 11:01:07
	List<BugBean> getBugListAll(HashMap<String, Object> map);
	//lxx 2017年11月27日 11:01:13
	int getBugCount(HashMap<String, Object> map);

	//lxx 2017年11月28日 13:44:18
	List<PermissionBean> getCombTreeList();

	//lxx 2017年11月28日 22:11:49
	boolean addInfo(PermissionBean permissionBean);

	//lxx 2017年11月29日 11:52:30
	void editInfo(PermissionBean permissionBean);

	//lxx 2017年11月29日 17:22:08
	void delInfo(int i);


}
