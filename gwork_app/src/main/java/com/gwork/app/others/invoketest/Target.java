package com.gwork.app.others.invoketest;

public class Target {

	public static void goToTest() throws Exception{
		throw new FormulaException(" this is a test exception ");
	}
	
}
