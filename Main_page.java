package Rental_Management;
import java.text.ParseException;
import java.util.*;
public class Main_page
{
	public static void main(String[] args) throws ParseException 
	{
		LoginSignUp page=new LoginSignUp();
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Welcome to Car Rental Management System");
		int mainpage=1;
		while(mainpage==1)
		{
				System.out.println("Press 1 for User page or Press 2 Admin page or 0 to exit");
				int pageselection=scan.nextInt();
			if(pageselection==1)
			{
				page.user();
				System.out.println("If you continue the application press 1 and exit for 0");
				mainpage=scan.nextInt();
			}
			else if(pageselection==2)
			{
				page.admin();
				System.out.println("If you continue the application press 1 and exit for 0");
				mainpage=scan.nextInt();
			}
			else if(pageselection==0)
				mainpage=0;
			else
			{
				System.out.println("Please Enter the Valid Number");
				mainpage=1;
			}
		}
	}
}