package service;

import java.util.Random;

import javax.swing.BoundedRangeModel;

public class GenrateOTP {

	public static String getOTP() {
		Random random =new Random();
		
		int otp=1000+random.nextInt(9000);
		
		return String.valueOf(otp);
		
	}
}
