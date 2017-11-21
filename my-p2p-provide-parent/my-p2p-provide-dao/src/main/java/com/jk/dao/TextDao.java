package com.jk.dao;

import java.util.List;

import com.jk.entity.LoginUser;

public interface TextDao {


	LoginUser queryByUserId(String getcLoginid);

	List<LoginUser> getAllUserList();

}
