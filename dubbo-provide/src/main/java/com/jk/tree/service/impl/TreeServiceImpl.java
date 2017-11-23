package com.jk.tree.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.tree.dao.TreeDao;
import com.jk.tree.entity.MenuModel;
import com.jk.tree.service.TreeService;
@Service("treeService")
public class TreeServiceImpl implements TreeService {
	@Autowired
	private TreeDao treeDao;

	 

	@Override
	public List<MenuModel> getTreeList() {
		List<MenuModel> listmenu = treeDao.getTreeList();
		List<MenuModel> tempList=new ArrayList<MenuModel>();
		for (int i = 0; i < listmenu.size(); i++){
			String id = String.valueOf(listmenu.get(i).getId());
			if (listmenu.get(i).getPid()==null)
			{
				MenuModel menuModel=new MenuModel();
				menuModel.setName(String.valueOf(listmenu.get(i).getName()));
				menuModel.setIconCls(String.valueOf(listmenu.get(i).getIconCls()));
				menuModel.setUrl(String.valueOf(listmenu.get(i).getUrl()));
				List<MenuModel> childList=new ArrayList<MenuModel>();
				for (int j = 0; j < listmenu.size(); j++)
				{
					MenuModel menuChildModel=new MenuModel();
					String sid = String.valueOf(listmenu.get(j).getPid());
					if (sid.equals(id))
					{
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
		return  tempList;
	}
	}

