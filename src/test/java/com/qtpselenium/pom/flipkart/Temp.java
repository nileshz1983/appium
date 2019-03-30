package com.qtpselenium.pom.flipkart;

public class Temp {

	public static void main(String[] args) {
		
		String priceRange="Rs. 5001 - Rs. 10000|Rs. 2001 - Rs. 5000";
		String price="Rs. 9,990";
		int priceDisplayed=Integer.parseInt(price.split(" ")[1].replace(",", ""));
		System.out.println("Price displayed - "+priceDisplayed );
		
		String temp[] = priceRange.split("\\|");
		
		for(int j=0;j<temp.length;j++){
			System.out.println(temp[j]);
			String temp2[]=temp[j].split(" ");
			int lowerLimit = Integer.parseInt(temp2[1]);
			int upperLimit =  Integer.parseInt(temp2[4]);
			if(priceDisplayed>=lowerLimit & priceDisplayed<=upperLimit){
				System.out.println("good");
				break;
			}
			
		}

	}

}
