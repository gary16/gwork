package com.gwork.app.springdemo.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwork.app.springdemo.basic.repository.PhoneRepository;
import com.gwork.app.springdemo.basic.service.DemoService;
import com.gwork.common.logging.annotation.ActionLog;

@Service
public class DemoServiceImpl implements DemoService {

	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@ActionLog
	@Override
	public int getAllPhoneCount() {
		return this.phoneRepository.selectAllCount();
	}

}
