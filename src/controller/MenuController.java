package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import model.vo.Food;

public class MenuController {
	private ArrayList<Food> susiList = new ArrayList<>();
	Map<Food, Integer> orderMap = new HashMap<>();
	public MenuController() {//스시 삽입 메소드
		try{
			//파일 객체 생성
			File file1 = new File("D:\\JavaProject\\miniProject\\src\\menuTextFiles\\menuTitle.txt");
			File file2 = new File("D:\\JavaProject\\miniProject\\src\\menuTextFiles\\menuPrice.txt");
			File file3 = new File("D:\\JavaProject\\miniProject\\src\\menuTextFiles\\menuImage.txt");
			//입력 스트림 생성
			FileReader filereader = new FileReader(file1);
			FileReader filereader2 = new FileReader(file2);
			FileReader filereader3 = new FileReader(file3);
			BufferedReader in = new BufferedReader(filereader);
			BufferedReader in2 = new BufferedReader(filereader2);
			BufferedReader in3 = new BufferedReader(filereader3);
			String menuTitle;
			String menuImage;
			String menuPrice;

			//MenuController에서 구현 
			while ((menuTitle = in.readLine()) != null) {
				menuPrice = in2.readLine();
				menuImage = in3.readLine();
				System.out.println(menuTitle + " " + menuPrice + " " + menuImage);
				susiList.add(new Food(menuTitle, menuImage, Integer.parseInt(menuPrice)));
			}
			in.close();
			in2.close();
			in3.close();
			filereader.close();
			filereader2.close();
			filereader3.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}    // TODO: handle exception
	}
	public ArrayList<Food> getSusiList() {
		return susiList;
	}
	public void setSusiList(ArrayList<Food> susiList) {
		this.susiList = susiList;
	}
	public void orederFood(Food food){
		if(orderMap.get(food)==null)orderMap.put(food, 1);
		else orderMap.put(food, orderMap.get(food)+1);
		System.out.println("size " + orderMap.size() + " " + orderMap.get(food));
	}
	public int calcOrder(){
		 Iterator<Food> mapIter = orderMap.keySet().iterator();
		 int result = 0;
		 while(mapIter.hasNext()){
			 Food food = mapIter.next();
			 int count = orderMap.get(food);
			 result += food.getPrice()*count;
		 }
		 return result;
	}
}
