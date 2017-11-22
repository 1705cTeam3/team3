package com.jk.provide.service.aop;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 
* @author 作者 尚峰: 
* @version 创建时间：2017年11月19日 下午12:17:34 
* 类说明 
*/
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceSwitcher.getDataSource();
	}

}
