package com.jk.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


/** 
* @author 作者 尚峰: 
* @version 创建时间：2017年11月14日 上午10:12:43 
* 类说明 
*/
public class RedisSessionDao extends AbstractSessionDAO {
	private static final String sessionIdPrefix = "shiro-session-";
    private static final String sessionIdPrefix_keys = "shiro-session-*";
    private static final long timeout = 2592000;
    private transient static Logger log = LoggerFactory.getLogger(RedisSessionDao.class);
    @Autowired
    private transient RedisTemplate<Serializable, Session> redisTemplate;
	@Override
	public void update(Session session) throws UnknownSessionException {
		  log.info("update shiro session ,sessionId is :{}", session.getId().toString());
	      redisTemplate.opsForValue().set(session.getId(), session, timeout, TimeUnit.SECONDS);
	}

	@Override
	public void delete(Session session) {
		 log.info("delete shiro session ,sessionId is :{}", session.getId().toString());
	     redisTemplate.opsForValue().getOperations().delete(session.getId());
	}

	@Override
	public Collection<Session> getActiveSessions() {
		 Set<Serializable> keys = redisTemplate.keys(sessionIdPrefix_keys);
	        if (keys.size() == 0) {
	            return Collections.emptySet();
	        }
	        List<Session> sessions = redisTemplate.opsForValue().multiGet(keys);
	        return Collections.unmodifiableCollection(sessions);
	}

	@Override
	protected Serializable doCreate(Session session) {
		  Serializable sessionId = sessionIdPrefix + UUID.randomUUID().toString();
	      assignSessionId(session, sessionId);
	      redisTemplate.opsForValue().set(sessionId, session, timeout, TimeUnit.SECONDS);
	      log.info("create shiro session ,sessionId is :{}", sessionId.toString());
	      return sessionId;
	}
	
	@Override
	protected Session doReadSession(Serializable sessionId) {
		  log.info("read shiro session ,sessionId is :{}", sessionId.toString());
	        return redisTemplate.opsForValue().get(sessionId);
	        
	}

}
