package com.likang.myutil.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 *@Author likang  2017年11月29日 下午2:53:53
 *
 */
@Component
public class MySetting {
	@Value("${com.likang.name}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
