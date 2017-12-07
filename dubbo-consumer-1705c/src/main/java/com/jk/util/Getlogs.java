package com.jk.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jk.provide.entity.LogDTO;
import com.jk.provide.entity.Users;

public class Getlogs {
     
    public static LogDTO getInfo(HttpServletRequest request){  
    	//获取登陆者信息(session)
//    	Subject subject = SecurityUtils.getSubject();
//    	Users u1 = (Users) subject.getSession().getAttribute("userMsg");
    	
    	Users u = (Users) request.getSession().getAttribute("userMsg");
    	
    	System.out.println(u.getName()+"=========="+u.getPassword());
    	//userlogin us=	(userlogin) request.getSession().getAttribute("userMsg");
       //StackTrace(堆栈轨迹)可以认为是一系列方法调用过程的集合。
    	StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        
    	LogDTO dl=new LogDTO();
    	dl.setClassname(stacks[2].getClassName());
    	dl.setMethodname(stacks[2].getMethodName());
    	dl.setCname(u.getName());
    	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	dl.setCztime(sim.format(new Date()));
    	dl.setLogid(UUID.randomUUID().toString());
    	dl.setFileName(stacks[2].getFileName());
    	dl.setLineNumber(stacks[2].getLineNumber());
    	//dl.setStatus(us.getcZw());
    	 //获取访问者IP
	    String	computerIp = request.getRemoteAddr();
		String computerName = "";
		if(!computerIp.equals("0:0:0:0:0:0:0:1")){
			try {
				//获取访问者计算机名称
				computerName = InetAddress.getByName(computerIp).getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}else{
			try {
				
				//获取计算机对象
				InetAddress addr = InetAddress.getLocalHost();
				computerName = addr.toString();
				//获取本机的ip地址
				computerIp = addr.getHostAddress();
			
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		dl.setCip("访问者IP是"+computerIp+"访问者计算机名称"+computerName);
		return dl;
		
    }  
	
	
}
