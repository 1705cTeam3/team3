package com.jk.dao;

import java.util.List;

import com.jk.provide.entity.PermissionBean;

public interface TreeGridMapper {

	List<PermissionBean> getSelTreeList();

	List<PermissionBean> getCombTreeList();

	int addInfo(PermissionBean permissionBean);

	void editInfo(PermissionBean permissionBean);

	void delInfo(int id);

}
