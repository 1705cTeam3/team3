package com.jk.consumer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.provide.entity.BugBean;
import com.jk.provide.entity.LoginUser;
import com.jk.provide.entity.Users;
import com.jk.provide.service.TextService;
import com.jk.util.Md5Util;
import com.jk.util.Page;



/** 
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
	
	/**
	 * <pre>queryLogin(shiro权限验证)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月26日 下午4:46:21    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月26日 下午4:46:21    
	 * 修改备注： 
	 * @param users
	 * @param request
	 * @param resp
	 * @return</pre>
	 */
	@RequestMapping("queryLogin")
	@ResponseBody
	public String queryLogin(Users users,HttpServletRequest request,HttpServletResponse resp){
//		String img = (String)request.getSession().getAttribute("rand");
//		if(img.equals(users.getAuthImage())){
		
		if(users!=null && !"".equals(users.getName())){
		       
	 		System.err.println("当前md5密码是：--------》  "+Md5Util.createPassword(users.getPassword(), users.getName(), 2));
	 		users.setName(users.getName());
	 		users.setPassword(users.getPassword());
		    List<Users> list = userservice.queryLogin(users);
			if(list.size()>0){
				Users user2 = list.get(0);
				 try{
					 Subject subject=SecurityUtils.getSubject();
					 subject.login(new UsernamePasswordToken(users.getName(), users.getPassword()));
			         System.out.println("sessionTimeout===>"+subject.getSession().getTimeout());
			         //登陆成功
			         subject.getSession().setAttribute("userMsg", user2);
			         return "5";
			        }
			        catch (UnknownSessionException use) {
			        	 Subject subject=SecurityUtils.getSubject();
						 subject.login(new UsernamePasswordToken(users.getName(), users.getPassword()));
			            return "异常会话";
			        }
			        catch(UnknownAccountException ex){
			        	 Subject subject=SecurityUtils.getSubject();
						 subject.login(new UsernamePasswordToken(users.getName(), users.getPassword()));
						   return "账号错误";
					}
			        catch (IncorrectCredentialsException ice) {
			        	 Subject subject=SecurityUtils.getSubject();
						 subject.login(new UsernamePasswordToken(users.getName(), users.getPassword()));
						 //密码错误
						 return "4";
			        } 
			        catch (LockedAccountException lae) {
			        	 Subject subject=SecurityUtils.getSubject();
						 subject.login(new UsernamePasswordToken(users.getName(), users.getPassword()));
						 return "账号已被锁定，请与系统管理员联系";
			        }
			        catch (AuthenticationException ae) {
			        	 Subject subject=SecurityUtils.getSubject();
						 subject.login(new UsernamePasswordToken(users.getName(), users.getPassword()));
						 return "您没有授权";
			        } 
			        catch(Exception e){
			        	 Subject subject=SecurityUtils.getSubject();
						 subject.login(new UsernamePasswordToken(users.getName(), users.getPassword()));
						 return "出现未知异常,请与系统管理员联系";
			        }
			      
				
				
				//subject.getSession().setAttribute("logininfo", arg1);
				//又加密一次
				/*String cPassword=Md5Util.getMD5(login.getL_pass());
				if(password.equals(list.get(0).getL_pass())){
					//登陆成功
					request.getSession().setAttribute("userMsg", list.get(0));
					return "5";
				}else{
					//密码错误
					return "4";
				}*/
			
			}else{
				//用户名不存在
				return "2";
			}
		}
		//请输入用户名和密码
		   return "3";

//	}
//		//验证码错误
//		return "6";
	}
	
	/**
	 * <pre>findAllUserList(分页)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月26日 下午3:31:35    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月26日 下午3:31:35    
	 * 修改备注： 
	 * @param page
	 * @param rows
	 * searchAnds:and
		searchColumnNames:myid
		searchConditions:=
		searchVals:gs
	 * @return</pre>
	 */
	@RequestMapping("findAllUserList")
	@ResponseBody
	public Map<String,Object> findAllUserList(String page,String rows,String searchAnds,String searchColumnNames,String searchConditions,String searchVals) {
		
		int pageInt = page ==null? 1 :Integer.parseInt(page);
		int rowsInt = rows ==null? 5 :Integer.parseInt(rows);
		
		Page pageModel = new Page();
		
		pageModel.setPage(pageInt);
		pageModel.setRows(rowsInt);
//		根据page，rows计算其他参数
		pageModel.calculate();
		
//		将开始条数，每页条数放入map最为参数传入获得list的方法，为xml  limit语句分页准备  limit start，size
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageModel.getStartIndex());
		map.put("size", pageModel.getRows());
		
		List<Users> pageList=userservice.getListAll(map,searchAnds,searchColumnNames,searchConditions,searchVals);
		int count = userservice.getCount(map,searchAnds,searchColumnNames,searchConditions,searchVals);
		
//		查询到的条数和list放入map传递给前台
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", count);
		resultMap.put("rows", pageList);
		return resultMap;
	}
	
	/**
	 * <pre>delUsers(删除当前行)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月26日 下午5:41:01    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月26日 下午5:41:01    
	 * 修改备注： </pre>
	 */
	@RequestMapping("delUsers")
	public void delUsers(String userId) {
		System.err.println("==============================================================="+userId);
		Integer userIdINT = Integer.valueOf(userId);
		userservice.delUsers(userIdINT);
	}
	
	@RequestMapping("insertInfo")
	@ResponseBody
	public boolean insertInfo() {
		System.err.println("--------");
		return true;
	}
	
	/**
	 * <pre>bugInfo(动态模糊查询)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年11月27日 下午4:55:27    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年11月27日 下午4:55:27    
	 * 修改备注： 
	 * @param page
	 * @param rows
	 * @param searchName
	 * @param searchValue
	 * @return</pre>
	 */
	@RequestMapping("bugInfo")
	@ResponseBody
	public Map<String,Object> bugInfo(String page,String rows,String searchName,String searchValue) {

		System.err.println(searchName+"--------"+searchValue);
		
		int pageInt = page ==null? 1 :Integer.parseInt(page);
		int rowsInt = rows ==null? 5 :Integer.parseInt(rows);
		
		Page pageModel = new Page();
		
		pageModel.setPage(pageInt);
		pageModel.setRows(rowsInt);
//		根据page，rows计算其他参数
		pageModel.calculate();
		
//		将开始条数，每页条数放入map最为参数传入获得list的方法，为xml  limit语句分页准备  limit start，size
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageModel.getStartIndex());
		map.put("size", pageModel.getRows());
		
		if (searchName==null || searchName.equals("bugName")) {
			map.put("bugName", "bugName");
		}
		if (StringUtils.isNotEmpty(searchName) && searchName.equals("attachmentUrl" )) {
			map.put("attachmentUrl", searchName);
		}
		if (StringUtils.isNotEmpty(searchName) && searchName.equals("description")) {
			map.put("description", searchName);
		}
		
		map.put("searchValue", searchValue);
		
		List<BugBean> pageList=userservice.getBugListAll(map);
		int count = userservice.getBugCount(map);
		
//		查询到的条数和list放入map传递给前台
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", count);
		resultMap.put("rows", pageList);
		return resultMap;
	}
}
