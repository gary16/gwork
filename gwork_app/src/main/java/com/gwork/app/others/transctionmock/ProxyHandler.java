package com.gwork.app.others.transctionmock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements  InvocationHandler {

	private Business business;

	public ProxyHandler(Business business) {
		this.business = business;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().equals("handle")) {
			try {
				method.invoke(business, args);
			}
			catch(Exception e) {
				System.out.println("this is a test .prove need proxy");
			}
		}
		else {
			method.invoke(business, args);
		}
		return null;
	}

}
