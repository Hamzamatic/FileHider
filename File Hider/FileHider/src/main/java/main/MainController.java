package main;

import views.Welcome;

public class MainController {
	public static void main(String[] args) {
		Welcome w=new Welcome();
		while(true)
		{
			w.welcome();
		}
		
	}

}
