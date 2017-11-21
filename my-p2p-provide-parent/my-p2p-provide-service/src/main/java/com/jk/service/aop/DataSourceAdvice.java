package com.jk.service.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/** 
* @author 作者 尚峰: 
* @version 创建时间：2017年11月19日 下午12:17:19 
* 类说明 
*/
public class DataSourceAdvice implements MethodBeforeAdvice,AfterReturningAdvice,ThrowsAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("切入点："+target.getClass().getName()+"类中 "+method.getName()+"方法");
		if(method.getName().startsWith("add")||method.getName().startsWith("save")){
			System.out.println("切换到主数据源");
			DataSourceSwitcher.setMater();
		}else {
			if(method.getName().startsWith("get")){
				System.out.println("切换到：slave");
				DataSourceSwitcher.setSlave();
			}
		}
	}
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		
	}
	
	public void afterThrowing(Method method, Object[] args, Object target,Exception ex) throws Throwable{
		DataSourceSwitcher.setSlave();
		System.out.println("出现异常切换到slave");
	}
}
