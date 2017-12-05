package com.jk.provide.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.BugMapper;
import com.jk.dao.TextDao;
import com.jk.dao.TreeGridMapper;
import com.jk.provide.entity.BugBean;
import com.jk.provide.entity.LoginUser;
import com.jk.provide.entity.MenuModel;
import com.jk.provide.entity.PermissionBean;
import com.jk.provide.entity.Users;
import com.jk.provide.service.TextService;

@Service("userserviceLXX")
public class TextServiceImpl implements TextService {

	@Autowired
	private TextDao textDao;

	@Autowired
	private TreeGridMapper treeGridMapper;

	@Autowired
	private BugMapper bugMapper;

	@Override
	public List<LoginUser> getAllUserList() {
		return textDao.getAllUserList();
	}

	@Override
	public String testRequest(LoginUser user) {
		return "success";
	}

	@Override
	public List<Users> queryLogin(Users user) {
		System.out.println("登录调用services!!!---------");
		return textDao.queryLogin(user);
	}

	@Override
	public Users queryLoginById(String loginid) {
		return textDao.queryLoginById(loginid);
	}

	@Override
	public List<MenuModel> findMenuList() {
		System.out.println("1");
		List<MenuModel> listmenu = textDao.queryMenu();
		List<MenuModel> tempList = new ArrayList<MenuModel>();
		for (int i = 0; i < listmenu.size(); i++) {
			String id = String.valueOf(listmenu.get(i).getId());
			if (listmenu.get(i).getPid() == null) {
				MenuModel menuModel = new MenuModel();
				menuModel.setName(String.valueOf(listmenu.get(i).getName()));
				menuModel.setIconCls(String.valueOf(listmenu.get(i).getIconCls()));
				menuModel.setUrl(String.valueOf(listmenu.get(i).getUrl()));
				List<MenuModel> childList = new ArrayList<MenuModel>();
				for (int j = 0; j < listmenu.size(); j++) {
					MenuModel menuChildModel = new MenuModel();
					String sid = String.valueOf(listmenu.get(j).getPid());
					if (sid.equals(id)) {
						menuChildModel.setName(String.valueOf(listmenu.get(j).getName()));
						menuChildModel.setIconCls(String.valueOf(listmenu.get(j).getIconCls()));
						menuChildModel.setUrl(String.valueOf(listmenu.get(j).getUrl()));
						childList.add(menuChildModel);
					}
				}
				menuModel.setChild(childList);
				tempList.add(menuModel);
			}
		}
		return tempList;
	}

	/**
	 * 树表数据
	 */
	@Override
	public List<PermissionBean> getSelTreeList() {
		// TODO Auto-generated method stub
		System.err.println("==================getSelTreeList mapper By Lengxiaxi==========================");
		return treeGridMapper.getSelTreeList();
	}

	/**
	 * 用户管理 分页 lengxiaxi 2017年11月26日 15:11:03
	 */

	@Override
	public List<Users> getListAll(HashMap<String, Object> map, String searchAnds, String searchColumnNames,
			String searchConditions, String searchVals) {
		/*
		 * searchAnds:and searchColumnNames:myid searchConditions:=
		 * searchVals:gs
		 */
		whereCondition(map, searchAnds, searchColumnNames, searchConditions, searchVals);
		return textDao.getListAll(map);
	}

	/**
	 * <pre>
	 * whereCondition(提取条件拼接公共部分)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月30日 下午3:04:14    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月30日 下午3:04:14    
	 * 修改备注： 
	 * &#64;param map
	 * &#64;param searchAnds
	 * &#64;param searchColumnNames
	 * &#64;param searchConditions
	 * &#64;param searchVals
	 * </pre>
	 */
	private void whereCondition(HashMap<String, Object> map, String searchAnds, String searchColumnNames,
			String searchConditions, String searchVals) {

		int i = 0;
		// 初始化要拼接的sql条件语句
		String whereCondition = " where 1 = 1 ";
		String[] searchAndsArray = null;
		String[] searchColumnNamesArray = null;
		String[] searchConditionsArray = null;
		String[] searchValsArray = null;

		if (StringUtils.isNotBlank(searchAnds)) {
			searchAndsArray = searchAnds.split(",");
			searchColumnNamesArray = searchColumnNames.split(",");
			searchConditionsArray = searchConditions.split(",");
			searchValsArray = searchVals.split(",");
			i = searchAndsArray.length;
			for (int j = 0; j < i; j++) {
				whereCondition += " " + searchAndsArray[j];
				whereCondition += " " + searchColumnNamesArray[j];
				whereCondition += " " + searchConditionsArray[j];
				if (searchConditionsArray[j].equals("like")) {
					whereCondition += " " + "'%" + searchValsArray[j] + "%'";
				} else {
					whereCondition += " " + "'" + searchValsArray[j] + "'";
				}
			}
			map.put("sql", whereCondition);
		}
	}

	@Override
	public int getCount(HashMap<String, Object> map, String searchAnds, String searchColumnNames,
			String searchConditions, String searchVals) {
		// TODO Auto-generated method stub
		whereCondition(map, searchAnds, searchColumnNames, searchConditions, searchVals);
		return textDao.getCount(map);
	}

	/**
	 * lxx 2017年11月26日 17:43:48 删除当前行
	 */
	@Override
	public void delUsers(Integer userId) {
		// TODO Auto-generated method stub
		textDao.delUsers(userId);
	}

	@Override
	public int getBugCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return bugMapper.getBugCount(map);
	}

	@Override
	public List<BugBean> getBugListAll(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return bugMapper.getBugListAll(map);
	}

	/**
	 * lxx 2017年11月28日 13:44:58
	 */
	@Override
	public List<PermissionBean> getCombTreeList() {
		// TODO Auto-generated method stub
		return treeGridMapper.getCombTreeList();
	}

	/**
	 * lxx 2017年11月28日 22:12:28 程式管理 新增
	 */
	@Override
	public boolean addInfo(PermissionBean permissionBean) {
		// TODO Auto-generated method stub
		if (treeGridMapper.addInfo(permissionBean) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * lxx 2017年11月29日 11:53:19 修改管理信息
	 */
	@Override
	public void editInfo(PermissionBean permissionBean) {
		// TODO Auto-generated method stub
		treeGridMapper.editInfo(permissionBean);
	}

	/**
	 * lxx 2017年11月29日 17:22:40 删除当前行
	 */
	@Override
	public void delInfo(int id) {
		// TODO Auto-generated method stub
		treeGridMapper.delInfo(id);
	}

}
