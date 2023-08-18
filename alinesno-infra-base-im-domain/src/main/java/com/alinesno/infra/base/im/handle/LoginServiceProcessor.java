/**
 * 
 */
package com.alinesno.infra.base.im.handle;

import cn.hutool.core.util.RandomUtil;
import org.jim.core.*;
import org.jim.core.packets.*;
import org.jim.core.session.id.impl.UUIDSessionIdGenerator;
import org.jim.core.utils.Md5;
import org.jim.server.processor.login.LoginCmdProcessor;
import org.jim.server.protocol.AbstractProtocolCmdProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LoginServiceProcessor extends AbstractProtocolCmdProcessor implements LoginCmdProcessor {

	private Logger log = LoggerFactory.getLogger(LoginServiceProcessor.class);

	public static final Map<String, User> tokenMap = new HashMap<>();

	public User getUser(LoginReqBody loginReqBody, ImChannelContext imChannelContext) {
		String text = loginReqBody.getUserId()+loginReqBody.getPassword();
		String key = ImConst.AUTH_KEY;
		String token = Md5.sign(text, key, CHARSET);
		User user = getUser(token);
		user.setUserId(loginReqBody.getUserId());
		return user;
	}

	public User getUser(String token) {
		//demo中用map，生产环境需要用cache
		User user = tokenMap.get(token);
		if(Objects.nonNull(user)){
			return user;
		}
		User.Builder builder = User.newBuilder()
				.userId(UUIDSessionIdGenerator.instance.sessionId(null))
				.nick("zhangsan")
				//模拟的群组,正式根据业务去查数据库或者缓存;
				.addGroup(Group.newBuilder().groupId("100").name("J-IM朋友圈").build());

		builder.avatar("").status(UserStatusType.ONLINE.getStatus());

		user = builder.build();
		if (tokenMap.size() > 10000) {
			tokenMap.clear();
		}
		tokenMap.put(token, user);
		return user;
	}

	/**
	 * 登陆成功返回状态码:ImStatus.C10007
	 * 登录失败返回状态码:ImStatus.C10008
	 * 注意：只要返回非成功状态码(ImStatus.C10007),其他状态码均为失败,此时用户可以自定义返回状态码，定义返回前端失败信息
	 */
	@Override
	public LoginRespBody doLogin(LoginReqBody loginReqBody, ImChannelContext imChannelContext) {
		if(Objects.nonNull(loginReqBody.getUserId()) && Objects.nonNull(loginReqBody.getPassword())){
			return LoginRespBody.success();
		}else {
			return LoginRespBody.failed();
		}
	}

	@Override
	public void onSuccess(User user, ImChannelContext channelContext) {
		log.info("登录成功回调方法");
	}

	@Override
	public void onFailed(ImChannelContext imChannelContext) {
		log.info("登录失败回调方法");
	}
}
