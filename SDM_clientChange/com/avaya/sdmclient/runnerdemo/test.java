package com.avaya.sdmclient.runnerdemo;

import java.util.ArrayList;
import java.util.List;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String test = "VALUE_NOTES|Product Notes|Notes|-|hcajhvv;sajvjhv;vhjevjhv;jhdvahv;sdvv$asdbvhjabvh;asjvhavjh;hsbvdshbv";
		String testStr = "VALUE_GFD_GHCDFGC|hgvv|Numeric|-|-, VALUE_GFD_DLRBN|dfkjbn|Numeric|-|-, FEAT_GFD_DKJKBNDNB|sdbfsdb|On/Off|-|-,";
		String temp = "";
		List<String> f1 = new ArrayList<>();
		List<String> f2 = new ArrayList<>();
		List<String> f3 = new ArrayList<>();
		List<String> f4 = new ArrayList<>();
		List<String> f5 = new ArrayList<>();
		
//		if(testStr.charAt(0) == ','){
//			temp = te
//		}
//		else if(testStr.charAt(0) == '|'){
//			
//		}
		String []parentParts = testStr.split("\\,");
		//String []parentParts = testStr.split("\\,");
		System.out.println(parentParts.length);
		for(String s : parentParts){
			
			System.out.println(s);
			String []childParts = s.split("\\|");
			
			for(int i=0;i<childParts.length;i++){
				if(i==0){
					f1.add(childParts[i]);
					System.out.println(childParts[i]);
				}
				if(i==1){
					f2.add(childParts[i]);
					System.out.println(childParts[i]);
				}
				if(i==2){
					f3.add(childParts[i]);
					System.out.println(childParts[i]);
				}
				if(i==3){
					f4.add(childParts[i]);
					System.out.println(childParts[i]);
				}
				if(i==4 && childParts[i].contains("$")){
					String []subChild = childParts[i].split("\\$");
					f5.add(childParts[i]);
					for(String ss : subChild)
					System.out.println("Subchild: "+ss);
				}
				if(i==4){
					f5.add(childParts[i]);
					System.out.println(childParts[i]);
				}
			}
			
		}
		System.out.println("test");
		
		for(String s : f1){
			System.out.println("First Part: "+s);
		}
		for(String s : f2){
			System.out.println("Second Part: "+s);
		}
		for(String s : f3){
			System.out.println("Third Part: "+s);
		}
		for(String s : f4){
			System.out.println("Fourth Part: "+s);
		}
		for(String s : f5){
			System.out.println("Fifth Part: "+s);
		}
	}

}
