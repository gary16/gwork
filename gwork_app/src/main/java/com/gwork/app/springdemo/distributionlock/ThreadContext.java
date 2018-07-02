package com.gwork.app.springdemo.distributionlock;

public class ThreadContext {

	private static ThreadLocal<LockInfo> lockInfoThreadLocal = new ThreadLocal<LockInfo>();

	public static LockInfo getLockInfo() {
		return lockInfoThreadLocal.get();
	}

	public static void setLockInfo(String lockFlag) {
		LockInfo tmpInfo = lockInfoThreadLocal.get();
		if(tmpInfo==null) {
		}
	}

	public static void removeLockInfo() {

	}

}
