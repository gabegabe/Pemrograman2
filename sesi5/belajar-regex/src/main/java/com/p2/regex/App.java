package com.p2.regex;

import java.util.regex.*;

public class App{
	public static void main(String[] args){
		String text = "febrycool99@yahoo.co.id";
		String regex = "\\w+[.\\w]+?@\\w+\\.[a-zA-Z]{2,}.[a-zA-Z]{2,}";
		
		if(text.matches (regex)){
			System.out.println("email sesuai dengan regex");		
		} else {
			System.out.println("email tidak sesuai dengan regex");
		}

		
	}
}