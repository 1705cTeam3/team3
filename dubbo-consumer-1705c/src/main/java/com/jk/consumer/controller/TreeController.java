package com.jk.consumer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.provide.entity.MessageBean;
import com.jk.provide.entity.PermissionBean;
import com.jk.provide.service.TextService;

@Controller
public class TreeController {

	@Autowired
	private TextService userservice;

	/**
	 * <pre>
	 * getTreegrid(加载树表数据)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月22日 下午10:49:39    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月22日 下午10:49:39    
	 * 修改备注：
	 * </pre>
	 */
	@RequestMapping("getTreegrid")
	@ResponseBody
	public Map<String, Object> getTreegrid() {
		// 查询所有数据作为参数，只为取出id进行，和pid比较，决定平行拼数据，还是嵌套拼接
		List<PermissionBean> treeNode = userservice.getSelTreeList();
		// 开始拼json格式数据
		List<Map<String, Object>> treeList = getTreeStr2(treeNode, 0);
		Map<String, Object> treeMap = new HashMap<String, Object>();
		treeMap.put("rows", treeList);
		treeMap.put("total", 50);
		System.err.println("-----------------------");
		return treeMap;
	}

	private List<Map<String, Object>> getTreeStr2(List<PermissionBean> list, Integer pid) {
		List<Map<String, Object>> newlist = new ArrayList<Map<String, Object>>();
		for (PermissionBean t : list) {
			Map<String, Object> map = null;
			if (t.getPid().equals(pid)) {
				map = new HashMap<String, Object>();
				map.put("id", t.getPermissionId());// treegrid控制父节点
				map.put("_parentId", t.getPid());// treegrid控制子节点
				map.put("name", t.getName());
				map.put("iconCls", t.getIconcls());// treegrid控制图标
				map.put("ico", t.getIconcls());
				map.put("state", t.getState());// treegrid控制是否展开（close/open）
				map.put("url", t.getUrl());
				map.put("myid", t.getMyid());
				map.put("type", t.getType());
				map.put("status", t.getStatus());
				map.put("des", t.getDescription());
				// 放入隐藏域修改回显时使用
				map.put("sort", t.getSort());
				map.put("permissionId", t.getPermissionId());
				// 递归调用，多维数组插入拼json｛｛｝，｛｝，｛｛｝，｛｛｝，｛｝｝｝｝
				map.put("children", getTreeStr2(list, t.getPermissionId()));
			}
			if (map != null) {
				newlist.add(map);
			}
		}
		return newlist;
	}

	/**
	 * <pre>
	 * getTreeList(combtree)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月28日 上午11:32:17    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月28日 上午11:32:17    
	 * 修改备注： 
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("getSelTreeList")
	@ResponseBody
	public List getTreeList() {
		List<PermissionBean> treeNode = userservice.getSelTreeList();
		List<Map<String, Object>> treeList = getCombTreeStr2(treeNode, 0);
		return treeList;
	}

	private List<Map<String, Object>> getCombTreeStr2(List<PermissionBean> list, Integer pid) {
		List<Map<String, Object>> newlist = new ArrayList<Map<String, Object>>();
		for (PermissionBean t : list) {
			Map<String, Object> map = null;
			if (t.getPid().equals(pid)) {
				map = new HashMap<String, Object>();
				map.put("id", t.getPermissionId());
				map.put("text", t.getName());
				map.put("iconCls", t.getIconcls());// treegrid控制图标
				map.put("pid", t.getPid());// treegrid控制子节点
				map.put("children", getCombTreeStr2(list, t.getPermissionId()));
			}
			if (map != null) {
				newlist.add(map);
			}
		}
		return newlist;
	}

	/**
	 * <pre>
	 * addInfo(程式管理添加)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月28日 下午7:15:35    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月28日 下午7:15:35    
	 * 修改备注： 
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("addInfo")
	@ResponseBody
	// 不用bean接收因为前台传过来的有时间类型，自动转为string，与实体类不对应，不想改实体类了，直接多个属性接收
	public Map<String, Object> addInfo(String permissionId, String name, String pid, String sort, String iconCls,
			String type, String isused) {

		Map<String, Object> hashMap = new HashMap<String, Object>();

		PermissionBean permissionBean = new PermissionBean();
		permissionBean.setName(name);
		permissionBean.setPid(Integer.parseInt(pid));
		permissionBean.setSort(Integer.parseInt(sort));
		permissionBean.setIconcls(iconCls);
		permissionBean.setType(type);
		permissionBean.setIsused(isused);
		permissionBean.setStatus("A");
		Date today = new Date();
		permissionBean.setCreated(today);

		boolean isok = false;
		// 判断新增或修改
		if (StringUtils.isNotEmpty(permissionId)) {
			try {
				permissionBean.setPermissionId(Integer.parseInt(permissionId));
				userservice.editInfo(permissionBean);
				isok = true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			isok = userservice.addInfo(permissionBean);
		}
		hashMap.put("yn", isok);
		return hashMap;
	}
	
	/**
	 * <pre>delInfo(删除树当前行)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月29日 下午5:03:07    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月29日 下午5:03:07    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("delInfo")
	@ResponseBody
	public Map<String, Object> delInfo(String id) {
		Map<String, Object> hashMap = new HashMap<>();
		MessageBean messageBean = new MessageBean();
		try {
			userservice.delInfo(Integer.parseInt(id));
			messageBean.setStatus(true);
			messageBean.setMsg("删除成功");
			messageBean.setTitle("提示");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		hashMap.put("rsp", messageBean);
		return hashMap;
	}
}
