package com.jk.tree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public List<MenuModel> getTreeList(Model model){
		List<MenuModel> menuModelList = treeService.getTreeList();
		return menuModelList;
 		
	}
}
