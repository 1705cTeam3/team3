package com.jk.interceptors;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jk.provide.entity.Operationlogs;
import com.jk.provide.entity.Users;


public class MyInterceptor implements HandlerInterceptor {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	Operationlogs oper=new Operationlogs();
	
	/* 
	   * 可以考虑作权限，日志，事务等等 
	   * 该方法在目标方法调用之前被调用； 
	   * 若返回TURE,则继续调用后续的拦截器和目标方法 
	   * 若返回FALSE,则不会调用后续的拦截器和目标方法 
	   * 
	   * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
	   * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），
	   * 不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
	   */

	// before the actual handler will be executed-- 在实际的处理程序被执行之前
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
	       
	    	long startTime = System.currentTimeMillis();
	       
	    	request.setAttribute("startTime", startTime);
	        if (handler instanceof HandlerMethod) {
	            StringBuilder sb = new StringBuilder(1000);
	
	            HandlerMethod h = (HandlerMethod) handler;
	            sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
	            sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
	            sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
	            sb.append("URI       : ").append(request.getRequestURI()).append("\n");
	          
	            
	            oper.setOperlogid(UUID.randomUUID().toString());
	            oper.setTomcatport(request.getServerPort());
	            oper.setOpercontroller(h.getBean().getClass().getName());
	            oper.setOperMethod(h.getMethod().getName());
	            oper.setOperParams(getParamString(request.getParameterMap()));
	            oper.setOperURI(request.getRequestURI());
	           
	            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            oper.setOpertime(sim.format(new Date()));
	           
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
	    		
	    		oper.setComputerIp(computerIp);
	    		oper.setComputerName(computerName);//访问者计算机名称
	    		
	    		
	    		
	    		System.out.println("执行前"+sb.toString());
	        }
	        return true;
	    }
	
	    
	    /** 
		   * 该方法在目标方法调用之后，渲染视图之前被调用； 
		   * 可以对请求域中的属性或视图做出修改 
		   * 
		   * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView
		   * （模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
		   */
	    // after the handler is executed--在执行处理程序之后
	    public void postHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	    	
	    	Users usr= (Users) request.getSession().getAttribute("userMsg");
	    	
		    	long startTime = (Long) request.getAttribute("startTime");
		      
		        long endTime = System.currentTimeMillis();
		       
		        long executeTime = endTime - startTime;
		        
		        if(handler instanceof HandlerMethod){
	        	
		        	oper.setOperloginId(usr.getName());
		        	oper.setOperlognsolr(usr.getStatus());
		        	oper.setHaoshi(executeTime+"ms");
		        	
		        	mongoTemplate.save(oper);
		        	
		        	
		        	StringBuilder sb = new StringBuilder(1000);
 	        	 sb.append("CostTime  : ").append(executeTime).append("ms").append("\n");
	        	 sb.append("-------------------------------------------------------------------------------");
	        	 System.out.println("执行后"+sb.toString());
	        }
	    }
	
	    
	    //获取方法中的参数
	    private String getParamString(Map<String, String[]> map) {
	        StringBuilder sb = new StringBuilder();
	        for(Entry<String,String[]> e:map.entrySet()){
	            sb.append(e.getKey()).append("=");
	            String[] value = e.getValue();
	            if(value != null && value.length == 1){
	                sb.append(value[0]).append("\t");
	            }else{
	                sb.append(Arrays.toString(value)).append("\t");
	            }
	        }
	        return sb.toString();
	    }
	
	    
	    /** 
		   * 在渲染视图之后被调用； 
		   * 可以用来释放资源 
		   * 
		   * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，
		   * 还可以进行一些资源清理，
		   * 类似于try-catch-finally中的finally，但仅调用处理器执行链中
		   */
	    
	    public void afterCompletion(HttpServletRequest arg0,
	            HttpServletResponse arg1, Object arg2, Exception arg3)
	            throws Exception {
	    }
}
