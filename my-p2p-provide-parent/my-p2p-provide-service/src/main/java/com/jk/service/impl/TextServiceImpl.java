package com.jk.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.TextDao;
import com.jk.entity.LoginUser;
import com.jk.service.TextService;

@Service("textService")
public class TextServiceImpl implements TextService {

	@Autowired
	private TextDao textDao;
	
	public List<LoginUser> getAllUserList() {
		return textDao.getAllUserList();
	}

	@Override
	public String testRequest(LoginUser user) {
		System.out.println(user.getcName());
		System.out.println(user.getcPassword());
		return "success";
	}

	@Override
	public Map<String, Object> queryByUserId(LoginUser lo) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Map<String, Object> queryByUserId(LoginUser lo) {
//		System.out.println("11111");
//		Map<String, Object> map = new HashMap<String, Object>();
//		/*String img = (String)request.getSession().getAttribute("rand");*/
//		String md5Password = Md5Util.getMD5(lo.getcPassword());
//		if(lo != null){
//			LoginUser u = textDao.queryByUserId(lo.getcLoginid());
//			if(u != null){
//				if(u.getcPassword().equals(md5Password)){
//					map.put("success", true);
//					map.put("msg", "登录成功");
//					//session赋值
//					//request.getSession().setAttribute("userMsg", u);
//					
//				}else{
//					map.put("success", false);
//					map.put("msg", "密码错误");
//				}
//			}else{
//				map.put("success", false);
//				map.put("msg", "账号不存在");
//			}
//		}else{
//			map.put("success", false);
//			map.put("msg", "账号或密码不可为空");
//		}
//		return map;
//	}

}
