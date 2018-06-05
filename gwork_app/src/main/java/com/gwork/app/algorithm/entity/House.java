
package com.gwork.app.algorithm.entity;

public class House {

    private String name;

    private int fortune;

    public House(String name,int fortune){
    	this.name=name;
    	this.fortune=fortune;
    }

	public String getName() {
		return name;
	}



	public int getFortune() {
		return fortune;
	}

	public void setFortune(int fortune) {
		this.fortune = fortune;
	}

	@Override
	public int hashCode() {
		return null==this.name?0:this.name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof House){
			House tmp =(House)obj;
			if(null!=this.name&&null!=tmp.getName()){
				return this.getName().equals(tmp.getName());
			}
		}
		return false;
	}

}
