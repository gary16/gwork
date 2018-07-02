package com.gwork.app.springdemo.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwork.app.springdemo.basic.entity.Phone;
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

	@Override
	public Phone getPhone(String tid) {
		return phoneRepository.selectByPid(tid);
	}

	@Override
	public int insertPhone(Phone phone) {
		return phoneRepository.insertSelective(phone);
	}

}
