package com.searchendeca.message;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "employee")
@RefreshScope
public class GlobalProperties {

	private String greetmsg;

	public String getGreetmsg() {
		return greetmsg;
	}

	public void setGreetmsg(String greetmsg) {
		this.greetmsg = greetmsg;
	}

}