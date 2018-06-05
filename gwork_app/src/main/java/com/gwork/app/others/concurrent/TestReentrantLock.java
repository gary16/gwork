package com.gwork.app.others.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

	private volatile int sum = 0;
	private ReentrantLock sumLock = new ReentrantLock();

	public static void main(String args[]) {
		TestReentrantLock test = new TestReentrantLock();
		BizHandler bh = new BizHandler(test);
		new Thread(bh,"t1").start();
		new Thread(bh,"t2").start();
		new Thread(bh,"t3").start();

	}

	public void increaseSum() {
		sumLock.lock();
		sum++;
		sumLock.unlock();
	}

	public void printSum() {
		System.out.println("the record is " + sum);
	}

}
