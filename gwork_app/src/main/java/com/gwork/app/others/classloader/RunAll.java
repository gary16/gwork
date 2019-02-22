package com.gwork.app.others.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RunAll {

	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
		method1();
	}

	public static void method1() throws Exception {
		byte[] src = CodeCompile.getDemoClassResource(1);
		CustomizeClassLoader loader1 = new CustomizeClassLoader(src);
		Class clz = loader1.loadClass("com.gwork.app.others.classloader.DemoModule");
		Object obj = clz.newInstance();
		Method mth1 = clz.getMethod("getName");
		String result = (String) mth1.invoke(obj, null);
		src = CodeCompile.getDemoClassResource(2);
		loader1 = new CustomizeClassLoader(src);
		clz = loader1.loadClass("com.gwork.app.others.classloader.DemoModule");
		//classloader永远不能获取
		Field fieldLoader = clz.getClass().getDeclaredField("classLoader");
		fieldLoader.set(clz, null);
		obj = clz.newInstance();
		mth1 = clz.getMethod("getName");
		result = (String) mth1.invoke(obj, null);
		System.out.println(result);
	}

	public static void method2() throws Exception {
		Class clz = CodeCompile.getDemoClass(1);
		Object obj = clz.newInstance();
		Method mth1 = clz.getMethod("getName");
		String result = (String) mth1.invoke(obj, null);
		System.out.println(result);
	}

}
