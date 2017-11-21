package com.jk.service.aop;

import org.springframework.util.Assert;

/** 
* @author 作者 尚峰: 
* @version 创建时间：2017年11月19日 下午12:06:41 
* 类说明 
*/
public class DataSourceSwitcher {
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal contextHolder =new ThreadLocal();
    
	@SuppressWarnings("unchecked")
	public static void setDataSource(String dataSource){
    	Assert.notNull(dataSource,"datasource connot be null");
    	contextHolder.set(dataSource);
    }
    
	public static void setMater(){
		clearDataSource();
	}
	
	public static void setSlave(){
		setDataSource("slave");
	}
	
	public static void setSlaveall(String key){
		setDataSource(key);
	}
	
	public static String getDataSource(){
		return (String) contextHolder.get();
	}
	public static void clearDataSource(){
		 contextHolder.remove();
	}
}
