package com.jk.consumer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.provide.entity.MessageBean;
import com.jk.provide.entity.Operationlogs;
import com.jk.provide.service.MongoService;
import com.jk.util.MySQLDatabaseBackup;

@Controller
public class MongoLogList {
	@Autowired
	private MongoService mongoService;

	/**
	 * <pre>
	 * queryMongoList(MongoDB日志展示)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年12月1日 下午8:07:15    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年12月1日 下午8:07:15    
	 * 修改备注： 
	 * &#64;param page
	 * &#64;param rows
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("queryMongoList")
	@ResponseBody
	public Map<String, Object> queryMongoList(String page, String rows) {
		List<Operationlogs> pageList = mongoService.getListAll(page, rows);
		int count = mongoService.getCount(page, rows);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", pageList);
		return resultMap;
	}

	@RequestMapping("findDbBackUpAllList")
	@ResponseBody
	/**
	 * <pre>
	 * findDbBackUpAllList(mongoDB数据库备份日志展示)   
	 * 创建人：lengXiaXi
	 * 创建时间：2017年12月1日 下午8:08:00    
	 * 修改人：lengXiaXi       
	 * 修改时间：2017年12月1日 下午8:08:00    
	 * 修改备注： 
	 * &#64;return
	 * </pre>
	 */
	public Map<String, Object> findDbBackUpAllList(String page, String rows) {
		List<Operationlogs> pageList = mongoService.getListAllForBackUp(page, rows);
		int count = mongoService.getCountForBackUp(page, rows);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", pageList);
		return resultMap;
	}

	@RequestMapping("backupDataBase")
	@ResponseBody
	public MessageBean backupDataBase(String ip,String account,String password,String savePath,String dataBase) {
		MessageBean messageBean = new MessageBean();
		try {
			MySQLDatabaseBackup.exportDatabaseTool(ip, account, password, savePath, dataBase);
			messageBean.setMsg("备份ok");
			messageBean.setStatus(true);
			messageBean.setTitle("警告");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			messageBean.setMsg("备份失败，请检查备份参数设置");
			messageBean.setStatus(false);
			messageBean.setTitle("警告");
		}
		return messageBean;
	};
	
	@RequestMapping("timeTask")
	@ResponseBody
	public MessageBean timeTask(String scheduleHour,String scheduleMinute) {
		int hourToSec = Integer.parseInt(scheduleHour);
		int minToSec = Integer.parseInt(scheduleMinute);
		MessageBean messageBean = new MessageBean();
		Runnable runnable = new Runnable() {  
            public void run() {  
            	backupDataBase("127.0.0.1", "root", "root", "d:\\", "erp");
            }  
        };  
        ScheduledExecutorService service = Executors  
                .newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, hourToSec, minToSec, TimeUnit.SECONDS); 
        messageBean.setMsg("定时备份ok");
		messageBean.setStatus(true);
		messageBean.setTitle("警告");
        return messageBean;
	}
}
