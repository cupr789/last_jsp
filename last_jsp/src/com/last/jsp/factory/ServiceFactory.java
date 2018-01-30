package com.last.jsp.factory;

import java.util.HashMap;
import java.util.Map;

import com.last.jsp.service.Service;
import com.last.jsp.service.impl.MenuServiceImpl;
import com.last.jsp.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private ServiceFactory() {
		
	}
	
	public static ServiceFactory getInstance() {
		return new ServiceFactory();
	}
	
	private static Map<String, Service> serviceMap;
	
	static {
		serviceMap =  new HashMap<String, Service>();
	}
	
	private void initInstance(String serviceName) {
		if(serviceName.equals("menu")) {
			serviceMap.put("menu", new MenuServiceImpl());
		}
		if(serviceName.equals("user")) {
			serviceMap.put("user", new UserServiceImpl());
		}
	}
	
	public Service getService(String serviceName) {
		if(!serviceMap.containsKey(serviceName)) {
			initInstance(serviceName);
		}
		return serviceMap.get(serviceName);
	}
}
