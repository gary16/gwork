package com.gwork.app.others.classtest;

public class Handler1 {

	private Handler2 handler2 = new Handler2();
	
	protected  class Handler2 {
		private String title="abcdefg";
		
		private String getTitle(int cnt ) {
			return title;
		}
	}
	
	public void printTitle() {
		System.out.println(handler2.getTitle(1));
	}
	
	public void printTitle2() {
		System.out.println(handler2.title);
	}
	
	private void a1() {
		
	}
	
	void a2(){
		
	}
	
	protected  void a3() {
		
	}
	
}
