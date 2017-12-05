package com.jk.provide.service;

import java.util.List;

import com.jk.provide.entity.LoginUser;
import com.jk.provide.entity.PermissionBean;

public interface TextService {
	String testRequest(LoginUser user);
	
	List<LoginUser> getAllUserList();
	
	/**
	 * <pre>getSelTreeList(获取树表数据)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月23日 下午2:30:04    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月23日 下午2:30:04    
	 * 修改备注： 
	 * @return</pre>
	 */
	List<PermissionBean> getSelTreeList();
}
