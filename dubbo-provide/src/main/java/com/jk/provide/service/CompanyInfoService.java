package com.jk.provide.service;

import java.util.List;

import com.jk.provide.entity.Company_info;

public interface CompanyInfoService {

	List<Company_info> getListAll(String page, String rows);

	int getCount(String page, String rows);

}
