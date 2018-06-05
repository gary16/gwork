package com.gwork.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gwork.webapp.annotation.MarkTest;

@Controller
@RequestMapping(value = "/first")
public class FirstController {

	@MarkTest
	@RequestMapping(value = "/test",method=RequestMethod.GET)
	public ResponseEntity test() {

		ResponseEntity responseEntity = new ResponseEntity(1, HttpStatus.OK);
		return responseEntity;
	}

}
