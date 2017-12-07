package com.jk.dao;

import java.util.List;

import com.jk.provide.entity.UsersBean;

public interface UsersMapper {

	List<UsersBean> findAllUserList();

}
