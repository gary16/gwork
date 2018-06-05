package com.gwork.app.algorithm.heap;

import java.util.Collection;

import com.alibaba.fastjson.JSON;

public class PriorityQueue {

	private Integer[] maxRecordArr;

	public PriorityQueue(int size) {
		maxRecordArr = new Integer[size < 1 ? 1 : size];
		for (int i=0;i<maxRecordArr.length;i++) {
			maxRecordArr[i]=0;
		}
	}

	public void putRecord(Integer record) {
		if (null == record)
			return;
		if (maxRecordArr[0] < record) {
			maxRecordArr[0] = record;
			this.sink(0);
		}
	}

	public void putRecord(Collection<Integer> records) {
		for(Integer tmp :records){
			this.putRecord(tmp);
		}
	}

	public void printMaxRecord() {
		System.out.println(JSON.toJSONString(maxRecordArr));
	}

	public void sink(int idx) {
		if (idx > maxRecordArr.length - 1)
			return;
		int left, right,cur=maxRecordArr[idx];
		left = right = Integer.MAX_VALUE;
		if (2 * idx + 1 <= maxRecordArr.length - 1) {
			left = maxRecordArr[2 * idx + 1];
		}
		if (2 * idx + 2 <= maxRecordArr.length - 1) {
			right = maxRecordArr[2 * idx + 2];
		}
		if(left==Integer.MAX_VALUE&&right == Integer.MAX_VALUE)
			return;
		if (left <= right&&left<cur) {
				swap(idx, 2 * idx + 1);
				this.sink(2 * idx + 1);
		}
		else if(right<left&&right<cur){
				swap(idx, 2 * idx + 2);
				this.sink(2 * idx + 2);
		}
	}

	public void swap(int srcIdx, int destIdx) {
		int tmp = maxRecordArr[srcIdx];
		maxRecordArr[srcIdx] = maxRecordArr[destIdx];
		maxRecordArr[destIdx] = tmp;
	}
	
	public static void main(String args[]) {
		
		PriorityQueue q = new PriorityQueue(8);
		q.putRecord(100);
		q.putRecord(45);
		q.putRecord(65);
		q.putRecord(99);
		q.putRecord(78);
		q.putRecord(55);
		q.putRecord(12);
		q.putRecord(1);
		q.putRecord(45);
		q.putRecord(90);
		q.putRecord(72);
		q.putRecord(32);
		q.putRecord(2);
		q.putRecord(81);
		q.putRecord(91);
		q.printMaxRecord();
	}
}
