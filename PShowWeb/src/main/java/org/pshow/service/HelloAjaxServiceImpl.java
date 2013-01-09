package org.pshow.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloAjaxServiceImpl implements HelloAjaxService {
	Logger logger = LoggerFactory.getLogger(HelloAjaxServiceImpl.class);
	@Override
	public Map<String, String> helloAjax() {
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("sk", "mamd");
		logger.info("componet invorked.");
		return hashMap;
	}

}
