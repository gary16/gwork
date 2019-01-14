package com.gwork.app.zookeeper.basic;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class RunAll {

	@SuppressWarnings("all")
	public static void main(String args[]) throws Exception {
		//delNode();
		//createNode();
		//getNode();
		accquireLock();

	}

	@SuppressWarnings("all")
	public static void createNode() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client =
		CuratorFrameworkFactory.newClient(
		                        "10.16.73.131:2181,10.16.73.132:2181,10.16.73.133:2181",
		                        5000,
		                        3000,
		                        retryPolicy);
		client.start();
		client.create().withMode(CreateMode.EPHEMERAL).forPath("/edp_etl_tokens","init".getBytes());
		client.close();
	}

	@SuppressWarnings("all")
	public static void getNode() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client =
		CuratorFrameworkFactory.newClient(
		                        "10.16.73.131:2181,10.16.73.132:2181,10.16.73.133:2181",
		                        5000,
		                        3000,
		                        retryPolicy);
		client.start();
		byte[] dataArr =  client.getData().forPath("/edp_etl_tokens");
		String ss = new String(dataArr);
		System.out.println(ss);
		client.close();
	}
	
	@SuppressWarnings("all")
	public static void delNode() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client =
		CuratorFrameworkFactory.newClient(
		                        "10.16.73.131:2181,10.16.73.132:2181,10.16.73.133:2181",
		                        5000,
		                        3000,
		                        retryPolicy);
		client.start();
		client.delete().forPath("/edp_etl_tokens");
		client.close();
	}
	
	@SuppressWarnings("all")
	public static void accquireLock() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client =
		CuratorFrameworkFactory.newClient(
		                        "10.16.73.131:2181,10.16.73.132:2181,10.16.73.133:2181",
		                        5000,
		                        3000,
		                        retryPolicy);
		client.start();
		
		InterProcessMutex  interProcessMutex = new InterProcessMutex(client,"/edp_etl_locks");
		Boolean flag = interProcessMutex.acquire(0, TimeUnit.SECONDS);
		//interProcessMutex.release();
		
		client.close();
	}

}
