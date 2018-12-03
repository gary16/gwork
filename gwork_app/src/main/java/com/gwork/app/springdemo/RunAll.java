package com.gwork.app.springdemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gwork.app.springdemo.basic.entity.Phone;
import com.gwork.app.springdemo.basic.entity.RequestData;
import com.gwork.app.springdemo.basic.service.DemoService;

public class RunAll {

	@SuppressWarnings("all")
	public static void main(String args[]) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		run1();
		long maxWorkerId = -1L ^-16;
		System.out.println(maxWorkerId);
		//run2("unitSerivce","testString","{\"data\":{\"pname\":\"fdf\",\"tid\":0},\"sId\":\"123\"}");
	
	}

	@SuppressWarnings("all")
	public static void run1() {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		ac.start();
		DemoService demoService = ac.getBean(DemoService.class);
		Phone phone = demoService.getPhone("1");
		System.out.println(phone);
	}

	@SuppressWarnings("all")
	public static void run2(String serviceName,String methodName,String jsonStr) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/applicationContext2.xml");
		ac.start();
		Object obj = ac.getBean(serviceName);

		RequestData<Phone> rd = new RequestData<Phone>();
		rd.setsId("123");
		Phone phone = new Phone();
		phone.setPname("fdf");
		rd.setData(phone);
		System.out.println(		JSON.toJSONString(rd));	
		// 获取class
		Class srcClz = obj.getClass();
		// 获取方法名
		Method[] mthArr = srcClz.getMethods();
		LocalVariableTableParameterNameDiscoverer discover= new LocalVariableTableParameterNameDiscoverer();
		
		int matchTime =0;
		for (Method tmpMth : mthArr) {
			if(tmpMth.getName().equals(methodName)) {
				System.out.println(		JSON.toJSONString(discover.getParameterNames(tmpMth)));	
				// 反射调用
				String[] param = discover.getParameterNames(tmpMth);
				Parameter[] paramArr = tmpMth.getParameters();
				for(Parameter tmpParam : paramArr) {
					ParameterizedType  t = (ParameterizedType)tmpParam.getParameterizedType();
					Type[] genArr = t.getActualTypeArguments();
					for(Type tmpType :genArr) {
						Class a =(Class)tmpType;
					
						JSONObject jObj = JSON.parseObject(jsonStr);
						RequestData requestData = JSON.parseObject(jsonStr, RequestData.class);
						Object data =JSON.parseObject(JSON.toJSONString(jObj.get("data")), a);
						requestData.setData(data);
						Object tmp = (Object)requestData;
						tmpMth.invoke(obj, tmp);
					}
				}
				
			}
		}	
	}

}
