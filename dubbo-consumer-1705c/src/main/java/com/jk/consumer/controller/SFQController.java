package com.jk.consumer.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.jk.provide.entity.MenuModel;
import com.jk.provide.service.TextService;

@Controller
public class SFQController {
	@Autowired
	private TextService userservice; 
	 /**
	  * 查询手风琴
	  * @param req
	  */
	 @RequestMapping("findAllFunctionList")
	 public void findAllFunctionList(HttpServletResponse req){
		    System.out.println("123=========");
		    List<MenuModel> list = userservice.findMenuList();
		    req.setCharacterEncoding("utf-8");
		    System.out.println("1232222=========");
		    String jsons= JSON.toJSONString(list);
		    try {
		    	req.getWriter().write(jsons);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
