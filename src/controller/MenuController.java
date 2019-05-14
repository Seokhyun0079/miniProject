package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import model.vo.Susi;

public class Menucontroller {
	private ArrayList<Susi> susiList = new ArrayList<>();
	public Menucontroller() {//스시 삽입 메소드
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
				susiList.add(new Susi(menuTitle, menuImage, Integer.parseInt(menuPrice)));
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
	public ArrayList<Susi> getSusiList() {
		return susiList;
	}
	public void setSusiList(ArrayList<Susi> susiList) {
		this.susiList = susiList;
	}
	
}
