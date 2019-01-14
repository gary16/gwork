package com.gwork.app.others.transctionmock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RunAll {

	public static void main(String args[]) throws Exception {

		Business target = new TargetImpl();
		InvocationHandler InvocationHandler = new ProxyHandler(target);
		Business proxy = (Business) Proxy.newProxyInstance(RunAll.class.getClassLoader(), new Class[] {Business.class}, InvocationHandler);
		proxy.handle();
		proxy.run();
	}
	
}
