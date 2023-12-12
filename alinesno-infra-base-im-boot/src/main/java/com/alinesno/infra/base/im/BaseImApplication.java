package com.alinesno.infra.base.im;

import com.alinesno.infra.base.im.config.AppConfiguration;
import org.jim.server.JimServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 集成一个Java开发示例工具
 * @author LuoAnDong
 * @since 2023年8月3日 上午6:23:43
 */
@SpringBootApplication
public class BaseImApplication {

	public static void main(String[] args) throws Exception {

//		JimServer jimServer = AppConfiguration.getJimServer();
		SpringApplication.run(BaseImApplication.class, args);
//		jimServer.start();

	}


}