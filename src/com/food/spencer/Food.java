package com.food.spencer;

import java.util.ArrayList;
import java.util.List;

public class Food {

	private String name;
	private List<Food> compatableList =  new ArrayList<Food>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Food> getCompatableList() {
		return compatableList;
	}

	public void setCompatableList(List<Food> compatableList) {
		this.compatableList = compatableList;
	}

	@Override
	public boolean equals(Object o){
		if(o == this) return true;
		
		if(!(o instanceof Food)) return false;
		
		Food f = (Food) o;
		
		return f.getName().equalsIgnoreCase(this.getName());
	}
	
	@Override
	public String toString(){
		return getName();
	}
}
