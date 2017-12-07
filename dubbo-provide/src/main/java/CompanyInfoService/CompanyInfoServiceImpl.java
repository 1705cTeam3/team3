package CompanyInfoService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jk.provide.entity.Company_info;
import com.jk.provide.service.CompanyInfoService;

@Service("CompanyInfoService")
public class CompanyInfoServiceImpl implements CompanyInfoService {

	/**
	 * lxx
	 * 2017年12月1日 15:08:00
	 * 分页
	 */
	@Override
	public List<Company_info> getListAll(String page, String rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(String page, String rows) {
		// TODO Auto-generated method stub
		return 0;
	}

}
