/**
 * @author jialiang02.chen
 *
 */
package com.gwork.app.algorithm.others;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Group2MakeUp100 {


	public void handleCountGroup(Stack<Integer> operatorStack, int level) {

		if (level == 9) {
			this.handleEachGroup(operatorStack);
			// System.out.println(JSON.toJSONString(operatorStack));
			return;
		}

		operatorStack.push(0);
		handleCountGroup(operatorStack, level + 1);
		operatorStack.pop();
		operatorStack.push(1);
		handleCountGroup(operatorStack, level + 1);
		operatorStack.pop();
	}

	public void handleEachGroup(Stack<Integer> operatorStack) {
		Integer operatorArr[] = new Integer[operatorStack.size()];
		operatorStack.toArray(operatorArr);
		this.setOperatorOfGroup(operatorArr, 0);
	}
	
	public void setOperatorOfGroup(Integer operatorArr[], int index) {
		if (index == 8) {
			this.countValue(operatorArr);
			// System.out.println(JSON.toJSONString(operatorArr));
			return;
		}
		if (operatorArr[index] == 0) {
			setOperatorOfGroup(operatorArr, index + 1);
		}
		else{
			operatorArr[index] = 2;
			setOperatorOfGroup(operatorArr, index + 1);
			operatorArr[index] = 3;
			setOperatorOfGroup(operatorArr, index + 1);
		}
	}

	public void setOperatorOfGroup1(Integer operatorArr[], int index) {
		if (index == 8) {
			this.countValue(operatorArr);
			// System.out.println(JSON.toJSONString(operatorArr));
			return;
		}
		if (operatorArr[index] != 0) {
			operatorArr[index] = 2;
		}
		setOperatorOfGroup(operatorArr, index + 1);

		if (operatorArr[index] != 0) {
			operatorArr[index] = 3;
		}
		setOperatorOfGroup(operatorArr, index + 1);
	}

	public void countValue(Integer operatorArr[]) {


			StringBuilder sb = new StringBuilder("1");
			for (int idx = 0; idx < operatorArr.length; idx++) {
				int val = idx + 2;
				if (operatorArr[idx] == 2) {
					sb.append(",+," + (val));
				} else if (operatorArr[idx] == 3) {
					sb.append(",-," + (val));
				} else {
					sb.append((val));
				}
			}

			String[] src = sb.toString().split(",");
			int sum =0 ;
			for(int idx =0 ;idx<src.length;idx++){
				if (src[idx].equals("+")) {
					sum+=Integer.parseInt(src[++idx]);
				} else if (src[idx].equals("-")) {
					sum-=Integer.parseInt(src[++idx]);
				} else {
					sum+=Integer.parseInt(src[idx]);
				}
			}
			if(sum==100){
				System.out.println(sb.toString().replace(",", ""));
			}
			
	}

	public static void main(String args[]) {

		Group2MakeUp100 group2MakeUp100 = new Group2MakeUp100();
		group2MakeUp100.handleCountGroup(new Stack<Integer>(), 1);
	}

}