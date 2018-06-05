package com.gwork.app.others.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class BizHandler implements Runnable {

	private TestReentrantLock tl;

	public BizHandler(TestReentrantLock tl) {
		this.tl = tl;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			tl.increaseSum();
			tl.printSum();
			try {
				Thread.currentThread().sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
