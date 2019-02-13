package com.gwork.app.algorithm.sort;

import com.alibaba.fastjson.JSON;

public class QuickSort {

	private int partiton(Integer[] arr, int lo, int hi) {
		int left = lo;
		int right = hi+1;
		Integer val = arr[lo];
		while (true) {
			//第一个值，即索引最小位不算
			while (arr[++left] <= val) {
				if (left == hi) {
					break;
				}
			}
			//从索引最大开始
			while (arr[--right] >= val) {
				if (right == lo) {
					break;
				}
			}
			if (left >= right) {
				break;
			}
			exch(arr, left, right);
		}
		exch(arr, lo, right);
		return right+1;
	}

	private void exch(Integer[] arr, int v1, int v2) {
		Integer tmp = arr[v1];
		arr[v1] = arr[v2];
		arr[v2] = tmp;
	}

	public void sort(Integer[] arr, int lo, int hi) {
		if(lo<hi) {
			int split = this.partiton(arr, lo, hi);
			if (split > lo && split < hi) {
				this.sort(arr, lo, split);
				this.sort(arr, split , hi);
			}
		}
	}

	public static void main(String[] args) {
			//Integer[] arr = new Integer[] {67,88,78,90};
		Integer[] arr = new Integer[] { 50, 1, 4, 6, 100, 67, 34, 88,88, 90, 7, 3, 78, 101, 100, 2,1};
		QuickSort qs = new QuickSort();
		qs.sort(arr, 0, arr.length-1);
		System.out.println(JSON.toJSONString(arr));
	}

}
