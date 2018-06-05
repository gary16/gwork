package com.gwork.app.springdemo.basic.service.impl;

import com.gwork.app.springdemo.basic.entity.Phone;
import com.gwork.app.springdemo.basic.entity.RequestData;
import com.gwork.app.springdemo.basic.service.UnitService;

public class UnitServiceImpl implements UnitService {

	@Override
	public void testString(RequestData<Phone> data) {
		System.out.println(data.getData().getPname());
	}

}
