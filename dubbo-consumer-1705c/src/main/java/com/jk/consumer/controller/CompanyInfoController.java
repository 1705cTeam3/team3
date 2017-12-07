package com.jk.consumer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.provide.entity.Company_info;
import com.jk.provide.entity.Operationlogs;
import com.jk.provide.service.CompanyInfoService;

@Controller
public class CompanyInfoController {
	
	private CompanyInfoService companyInfoService;

	/**
	 * <pre>CompanyInfo(公司档案录入)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年12月1日 下午3:05:32    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年12月1日 下午3:05:32    
	 * 修改备注： 
	 * @param page
	 * @param rows
	 * @return</pre>
	 */
	@RequestMapping("CompanyInfo")
	@ResponseBody
	public Map<String,Object> CompanyInfo(String page,String rows) {
		List<Company_info> pageList=companyInfoService.getListAll(page,rows);
		int count = companyInfoService.getCount(page,rows);
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", count);
		resultMap.put("rows", pageList);
		return resultMap;
	}
}
