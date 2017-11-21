package com.jk.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jk.entity.LoginUser;
import com.jk.service.TextService;

@Controller
public class UserInfoCon {

	@Autowired
	private TextService textService; 
	
	@RequestMapping("getAllUserInfo")
	public void getAllUserInfo(){
		List<LoginUser> userList= textService.getAllUserList();
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
			String su=  textService.testRequest(user);
			System.out.println(su);
		}
	}
	@RequestMapping("loginUser")
	public Map<String,Object> loginUser(LoginUser lo,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map = textService.queryByUserId(lo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;                                                              
	}
	
}
