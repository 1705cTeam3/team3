package com.jk.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.jk.util.ResponseCode;
import com.jk.util.Result;
/** 
* @author 作者 尚峰: 
* @version 创建时间：2017年11月13日 上午10:10:48 
* 类说明 
*/
public class ShiroAuthenticationFilter extends PassThruAuthenticationFilter {

    private static Logger log = LoggerFactory.getLogger(ShiroAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            saveRequest(request);
            if (((HttpServletRequest) request).getHeader("Accept").contains("application/json")) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                Result result = new Result(ResponseCode.unauthenticated.getCode(), ResponseCode.unauthenticated.getMsg());
                response.getWriter().append(new Gson().toJson(result));
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                ((HttpServletResponse) response).sendRedirect("/hunt-admin");
            }
            return false;
        }
    }

}
