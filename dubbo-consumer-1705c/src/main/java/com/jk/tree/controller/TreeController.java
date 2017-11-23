package com.jk.tree.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.jk.tree.entity.MenuModel;
import com.jk.tree.service.TreeService;
 

@Controller
public class TreeController {
	@Autowired
	private TreeService treeService;
	/**
	 * <pre>getTreeList(活树)   
	 * 创建人：柴赵波   
	 * 创建时间：2017年11月22日 下午9:42:37       
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("getTreeList")
	public void getTreeList(HttpServletResponse req){
		List<MenuModel> menuModelList = treeService.getTreeList();
		req.setCharacterEncoding("utf-8");
 		String str = JSON.toJSONString(menuModelList);
 		try {
	    	req.getWriter().write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
