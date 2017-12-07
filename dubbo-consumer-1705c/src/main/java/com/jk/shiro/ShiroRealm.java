package com.jk.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.jk.provide.entity.Users;
import com.jk.provide.service.TextService;
import com.jk.util.Const;


/** 
* @author 作者 尚峰: 
* @version 创建时间：2017年11月14日 上午10:00:20 
* 类说明 
*/
public class ShiroRealm  extends AuthorizingRealm  {

	 private static Logger log = LoggerFactory.getLogger(ShiroRealm.class);
		
	 @Autowired
	 private RedisTemplate<Object, Object> redisTemplate;
	 
     @Autowired
	 private TextService textService;
     
    /* @Autowired
	 private TAtyUserRightMapper tAtyUserRightMapper;*/
	/**
	 * 用户登陆认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String loginid= (String) token.getPrincipal();
		Users sysUser=(Users) textService.queryLoginById(loginid);
		 AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginid, sysUser.getPassword(), ByteSource.Util.bytes(loginid), getName());
	     return authenticationInfo;
	}
	/**
	 * 授权管理
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		  /*  log.info("开始查询授权信息");
	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	        String loginStr = (String) principalCollection.getPrimaryPrincipal();
	        TAtyUser userinfo = tAtyUserMapper.getUserInfoByUserLoginId(loginStr);
	        List<TUserRight> userRightList=tAtyUserRightMapper.getUserRightById(userinfo.getcId());
	        Set<String> right = new HashSet<>();
	        for (TUserRight userPermission : userRightList) {
	        	if(StringUtils.isNotBlank(userPermission.getCRightKey())){
	        		right.add(userPermission.getCRightKey());
	        	}
	        }
	        info.addStringPermissions(right);
	        log.info("权限信息: \n{}", right.toString());*/
		return null;
	}
	  @Override
	    protected void doClearCache(PrincipalCollection principals) {
	        redisTemplate.delete(Const.shiro_cache_prefix + principals.getPrimaryPrincipal().toString());
	    }

	    @Override
	    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
	        log.debug("clearCachedAuthorizationInfo");
	    }
	
}
