package com.jk.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeTaskUtil {

	public static void main(String[] args) {
	}
	
	public static void startTimeTask(int firstTime,int eachTime, final String methodName) {
		Runnable runnable = new Runnable() {  
            public void run() {  
            }  
        };  
        ScheduledExecutorService service = Executors  
                .newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, firstTime, eachTime, TimeUnit.SECONDS);  
	}
}
