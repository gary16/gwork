package com.gwork.app.springdemo.basic.service;

import com.gwork.app.springdemo.basic.entity.Phone;

public interface DemoService {

	public int getAllPhoneCount();

	public Phone getPhone(String tid);
	
	public int insertPhone(Phone phone);
}
