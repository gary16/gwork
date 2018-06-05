package com.gwork.app.algorithm.others;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class HanoiTower {

	private int slotCnt;
	private int sum=0;

	public HanoiTower(int slotCnt) {
		this.slotCnt = slotCnt<3?3:slotCnt;
	}

	public void CountBestPath(int towerLevel, int srcIdx, int destIdx) {
		if((srcIdx<0||srcIdx>slotCnt)||(destIdx<0||destIdx>slotCnt)){
			System.out.println("起始位置必须在0和插槽数量之间");
		}
		if(srcIdx==destIdx){
			return;
		}
		this.handleBestPath(towerLevel, srcIdx, destIdx);
		System.out.println("summary move : "+sum);
	}
	
	
	public void handleBestPath(int towerLevel, int srcIdx, int destIdx) {

		if(towerLevel==0){
			return;
		}
		
		int mapArr[] = this.getIndexArr(srcIdx, destIdx);
		int storeLevel=0,storeIdx = this.getStoreIdx(mapArr);
		
		List<LevelInfo> queue = new ArrayList<>();
		for(int tmp=slotCnt,tmpLevel = towerLevel-1;tmp>=3&&tmpLevel>0;tmp--,tmpLevel--){
			if(tmp==3){
				storeLevel=tmpLevel;
				handleBestPath(storeLevel,srcIdx,storeIdx);
			}
			else{
				queue.add(new LevelInfo(tmpLevel,this.getStoreIdx(mapArr)));
			}
		}

		for(int i=queue.size()-1;i>=0;i--){
			System.out.println(queue.get(i).level+"  ("+srcIdx+"->"+queue.get(i).idx+")");
			sum++;
		}
		
		System.out.println(towerLevel+"  ("+srcIdx+"->"+destIdx+")");
		sum++;
	
		for(LevelInfo tmp:queue){
			System.out.println(tmp.level+"  ("+tmp.idx+"->"+destIdx+")");
			sum++;
		}
		
		handleBestPath(storeLevel,storeIdx,destIdx);
	}
	
    class LevelInfo{
    	
    	int  level;
    	int  idx;
    	
    	LevelInfo(int  level,int idx){
    		this.level=level;
    		this.idx=idx;
    	}
    	
    }

    
    private int[] getIndexArr(int srcIdx, int destIdx){
    	int mapArr[] = new int[slotCnt];
		mapArr[srcIdx-1]=1;
		mapArr[destIdx-1]=1;
		return mapArr;
    }
    
	private int getStoreIdx(int mapArr[] ) {
		for(int i =0 ; i<mapArr.length ; i++){
			if(mapArr[i]==0){
				mapArr[i]=1;
				return i+1;
			}
		}
		return 0;
	}
	
	public static void main(String args[]) {
		HanoiTower hanoiTower= new HanoiTower(10);
		hanoiTower.CountBestPath(4, 3, 1);
	}
}
