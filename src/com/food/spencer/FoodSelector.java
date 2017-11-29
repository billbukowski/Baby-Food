package com.food.spencer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FoodSelector {

	public static List<Food> foods = new ArrayList<Food>();
	public static void main(String[] args) {
		getFoods();
		
//		not going to do it this way anymore...
//		JFrame f=new JFrame();//creating instance of JFrame  
//        
//		JTextArea area=new JTextArea(findFoods("Figs").toString());
//		area.setBounds(10,30, 200,200);
//		
//		JScrollPane scroll = new JScrollPane (area, 
//				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		
//		JButton b=new JButton("click");//creating instance of JButton  
//		b.setBounds(130,100,100, 40);//x axis, y axis, width, height  
//		          
//		//f.add(b);//adding button in JFrame  
//		f.add(scroll);
//		          
//		f.setSize(500,300);//width and height  
//		f.setLayout(null);//using no layout managers  
//		f.setVisible(true);//making the frame visible  
//			
//		findFoods("apples");
		
		//System.out.println(foods.toString());
		
//		for(Food food:foods){
//			System.out.println(food.getName());
//			for(Food compatableFood: food.getCompatableList()){
//				System.out.println("\t" + compatableFood.getName());
//			}
//		}
	}
	
	public static String findFoods(String startingFood){
		Food food1 = new Food();
		int count = 0; 
		List<String> finalFoodSet = new ArrayList<String>();
		
		for(Food food: foods){
			if(food.getName().equalsIgnoreCase(startingFood)){
				//System.out.println("Found food1");
				food1 = food;
			}
		}
		
		for(Food food2: food1.getCompatableList()){
			for(Food food3: food2.getCompatableList()){
				if(food1.getCompatableList().contains(food3)){
					//System.out.println(food3.getName());
					if(!finalFoodSet.contains(food3.getName() + " and " + food2.getName())){
						finalFoodSet.add(food2.getName() + " and " + food3.getName());
						count++;
					}
				}
			}
		}
		
		StringBuilder s = new StringBuilder();
		
		finalFoodSet.stream().sorted().forEach(x->s.append(x + "\n"));
		System.out.println(count);
		return s.toString();
	}
	
	public static List<Food> getCompatableFoods(Food f){
		List<Food> l = new ArrayList<Food>();
		for(Food food:foods){
			if(f.equals(food)) l = food.getCompatableList();
		}
		return l;
	}
	
	
	
	public static void getFoods(){

        // The name of the file to open.
        String fileName = "/Users/bill/Documents/workspace/food/Spencer Food/Foods.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                
                String[] foodLine = line.split(":");
                Food foodItem = new Food();
                foodItem.setName(foodLine[0]);
                if(!foods.contains(foodItem)){
                	foods.add(foodItem);
                }else{
                	foodItem = foods.get(foods.indexOf(foodItem));
                }
                
                List<Food> compatableList = new ArrayList<Food>();
                String[] compatables = foodLine[1].split(",");
                
                for(String compatable: compatables){
                	Food compatableFood = new Food();
                	compatableFood.setName(compatable.trim());
                	if(!foods.contains(compatableFood)){
                		foods.add(compatableFood);
                	}else{
                		compatableFood = foods.get(foods.indexOf(compatableFood));
                	}
                	compatableList.add(compatableFood);//change this
                }
                foodItem.setCompatableList(compatableList);
                //foods.add(foodItem);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}

}
