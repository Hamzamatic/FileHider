package views;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DataDAO;
import model.Data;

public class UserView {
	private String email;
	public UserView(String email) {
		this.email=email;
	}  
	
	public void home() {
		while(true)
		{
			System.out.println("Welcome"+this.email);
			System.out.println("Press 1 to Show Hidden Files");
			System.out.println("Press 2 to Hide a File");
			System.out.println("Press 3 to Unhide a File ");
			System.out.println("Press 0 to exit");
			Scanner scn=new Scanner(System.in);
			int ch=Integer.parseInt(scn.nextLine());
			if(ch==1)
			{
				try {
					List<Data> files=DataDAO.getAllfiles(this.email);
					System.out.println("ID - FILE NAME");
					for(Data file: files)
					{
						System.out.println(file.getId()+" - "+file.getFileName());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(ch==2)
			{
				System.out.println("Enter The file path");
				String path=scn.nextLine();
				File f=new File(path);
				Data file =new Data(0, f.getName(), path, this.email);
				try {
					DataDAO.hideFile(file);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(ch==3)
			{
				List<Data> files;
				try {
					files = DataDAO.getAllfiles(this.email);
				
				System.out.println("ID - FILE NAME");
				for(Data file: files)
				{
					System.out.println(file.getId()+" - "+file.getFileName());
				}
				System.out.println("Enter the id of file to unhide");
				int id=Integer.parseInt(scn.nextLine());
				boolean isValidID=false;
				for(Data file:files)
				{
					if(file.getId()==id) {
						isValidID=true;
						break;
					}
				}
				if(isValidID)
				{
					DataDAO.unhide(id);
				}
				else {
					System.out.println("Wrong id");
				}
				}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
			else {
				home();
			}
				
		}
	}

}
