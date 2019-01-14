package com.gwork.app.others.transctionmock;

public class TargetImpl implements Business {

	@Override
	public void handle() throws Exception {
       throw new Exception();
	}

	@Override
	public void run() throws Exception {
       this.handle();
	}

}
