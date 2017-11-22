package com.jk.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jk.provide.entity.LoginUser;
import com.jk.provide.service.TextService;


/** 
* @author 作者 尚峰: 
* @version 创建时间：2017年11月15日 上午10:59:11 
* 类说明 
*/
@Controller
public class UserInfoCon {

	@Autowired
	private TextService userservice; 
	
	@RequestMapping("getAllUserList")
	public void getAllUserList(){
		List<LoginUser> userList= userservice.getAllUserList();
		for (LoginUser tAtyUser : userList) {
			System.out.println(tAtyUser.getcName());
		}
	}
	
	@RequestMapping("testUserLogin")
	public void testUserLogin(){
		for (int i = 0 ; i < 10 ; i++) {
			LoginUser user=new LoginUser();
			user.setcName("张三"+i);
			user.setcPassword("shangfeng"+i);
			String su=  userservice.testRequest(user);
			System.out.println(su);
		}
	}
}
