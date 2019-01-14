package com.gwork.app.others.invoketest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunAll {
	public static void main(String args[]) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			Method method = Target.class.getMethod("goToTest", null);
			method.invoke(null, null);
		}
		catch(Exception e) {
			System.out.println(e.getCause().getMessage());
		}

	}
}
