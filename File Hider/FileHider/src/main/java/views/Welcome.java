package views;

import java.io.IOException;
import java.io.ObjectInputFilter.Status;
import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDAO;
import model.User;
import service.GenrateOTP;
import service.SendOTPService;
import service.UserService;

public class Welcome {
	Scanner sc=new Scanner(System.in);
	public void welcome() {
		Scanner scn=new Scanner(System.in);
		System.out.println("WELCOME");
		System.out.println("Press 1 to login");
		System.out.println("Press 2 to signup");
		System.out.println("Press 0 to exit");
		int ch=scn.nextInt();
		if(ch==1)
		{
			login();
		}
		else if(ch==2)
		{
			try {
				signup();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(ch==0)
		{
			System.exit(0);
		}
		else {
			System.out.println("Invalid Choice");
			welcome();
		}
		
			
		
		
	}

	private void signup() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter Name");
		String name=sc.nextLine();
		System.out.println("Enter Email");
		String email=sc.nextLine();	
//		if(UserDAO.isExsists(email))
//		{
//			System.out.println("User Already Exists Please Login");
////			login();
//			return;
//		}
		String genOTP=GenrateOTP.getOTP();
		SendOTPService.sendOTP(email, genOTP);
		System.out.println("Enter Otp");
		String otp=sc.nextLine();
		if(otp.equals(genOTP))
		{
			User user =new User(name, email);
			int response= UserService.saveUser(user);
			if(response==0)
			{
				System.out.println("User Registered");
			}
			else if(response==1)
			{
				System.out.println("User already exists");
			}
			else
			System.out.println("Hosh me aa");
		}
		else {
			System.out.println("Wrong OTP");
		}

	}

	
	
	
	
	
	
	
	
	private void login() {
		// TODO Auto-generated method stub
		System.out.println("Enter Email");
		String email =sc.nextLine();
		try {
			if(UserDAO.isExsists(email))
			{
				String genOTP=GenrateOTP.getOTP();
				SendOTPService.sendOTP(email, genOTP);
				System.out.println("Enter Otp");
				String otp=sc.nextLine();
				if(otp.equals(genOTP))
				{
					new UserView(email).home();
//					System.out.println("Welcome");
				}
				else {
					System.out.println("Wrong OTP");
				}
				
			}
			else{
				System.out.println("User not found");
			}
				
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
