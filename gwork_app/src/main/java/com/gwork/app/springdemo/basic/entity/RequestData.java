package com.gwork.app.springdemo.basic.entity;

import java.io.Serializable;

public class RequestData<T> implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	private String sId;
	
	private T data;

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
