package Rental_Management;
import java.text.ParseException;
import java.util.*;
public class LoginSignUp{
	DatabaseConnectivity db_page=new DatabaseConnectivity();
	Customer book=new Customer();
	Admin access=new Admin();
	//private static String key="SSIET";
	static Scanner scan=new Scanner(System.in);
	public void user() throws ParseException
	{
		int entry=1;
		while(entry==1)
		{
			System.out.println("Press 1 for Login or 2 for Sign Up or press 0 to exit");
			int loginDetail=scan.nextInt();
			if(loginDetail==1)
			{
				Boolean ret=false;
				System.out.println("Welcome to the User Login page");	
				System.out.println("Please enter the username");				
				String user_username=scan.next();
				System.out.println("Please enter the password");				
				String user_password=scan.next();
				ret=db_page.userLogin(user_username,user_password);
				if(ret)
				{
					System.out.println("Welcome to the Customer Access Page");
					int c_access=1;
					while(c_access==1)
					{	
						System.out.println("Press '1' for Booking or Press '2' for cancelletion or Press'0' exit");
						int cust=scan.nextInt();
						if(cust==1)
						{
							boolean value=false;
							value=book.booking(user_username);
							if(value)
							{
								System.out.println("Thank you");
							}
							else 
								System.out.println("Your Booking is cancelled");
							System.out.println("If you want to continue the Customer access page press '1' press '0' exit");
							c_access=scan.nextInt();
						}
						else if(cust==2)
						{
							book.cancel();
							System.out.println("Your Booking is cancelled");
							System.out.println("If you want to continue the Customer access page press '1' press '0' exit");
							c_access=scan.nextInt();
						}
						else if(cust==0)
							c_access=0;
						else
							System.out.println("Please enter the valid Number");
					}
				}
				else
				{
					System.out.println("Incorrect username and password");
				}
				
				System.out.println("If you want to continue the application press 1 or othervise exit");
				entry=scan.nextInt();
			}
			else if(loginDetail==2)
			{
				
				System.out.println("Welcome to the Customer SignUp page");
				System.out.println("Enter your name");
				scan.nextLine();
				String c_name=scan.nextLine();
				System.out.println("Enter your Age");
				int c_age=scan.nextInt();
				System.out.println("Enter your Mobile");
				String c_mobile=scan.next();
				System.out.println("Enter your Address");
				scan.nextLine();
				String c_add=scan.nextLine();
				System.out.println("Enter your Aadhar Number XXXX-XXXX-XXXX");
				String c_aadhno=scan.nextLine();
				System.out.println("Enter your License Number");
				String c_lic=scan.nextLine();
				System.out.println("Enter your username");
				String c_username=scan.next();
				System.out.println("Enter your password");
				String c_password=scan.next();
				System.out.println("Ensure to provide Sequrity deposite amount of 50000, Copy of License, Aadhar card then only vehicle eill be provide");
				System.out.println(db_page.userSignup(c_name,c_age,c_mobile,c_add,c_aadhno,c_lic,c_username,c_password));
				
				System.out.println("If you want to continue the application press 1 or othervise exit");
				entry=scan.nextInt();
			}
			else if(loginDetail==0)
				entry=0;
			else
			{
				System.out.println("Please enter the valid Number");
				entry=1;
			}
		}
	}
	public void admin() throws ParseException
	{
		int ad=1;
		while(ad==1)
		{
			System.out.println("Please enter the Company Secret Key to enter the admin page");
			String key=scan.next();
			
			if(access.getkey(key))
			{
				int ad_entry=1;
				while(ad_entry==1)
				{
					System.out.println("Press 1 for Login or 2 for Sign Up or press 0 to exit");
					int loginDetail=scan.nextInt();
					if(loginDetail==1)
					{
						Boolean ret=false;
						System.out.println("Welcome to the Admin Login page");	
						System.out.println("Please enter the username");				
						String admin_username=scan.next();
						System.out.println("Please enter the password");				
						String admin_password=scan.next();
						ret=db_page.adminLogin(admin_username,admin_password);
						if(ret)
						{
							access.menu();
						}
						else
						{
							System.out.println("Incorrect username and password");
						}
						System.out.println("If you want to continue the application press 1 or othervise exit");
						ad=scan.nextInt();
					}
					else if(loginDetail==2)
					{
						System.out.println("Welcome to the Admin SignUp page");
						System.out.println("Enter your name");
						scan.nextLine();
						String a_name=scan.nextLine();
						System.out.println("Enter your Mobile");
						String a_mobile=scan.next();
						System.out.println("Enter your Email");
						String a_email=scan.next();
						System.out.println("Enter your Age");
						int a_age=scan.nextInt();
						System.out.println("Enter your Address");
						scan.nextLine();
						String a_add=scan.nextLine();
						System.out.println("Enter your username");
						String a_username=scan.next();
						System.out.println("Enter your password");
						String a_password=scan.next();
						
						System.out.println(db_page.adminSignup(a_name,a_mobile,a_email,a_age,a_add,a_username,a_password));
						
						System.out.println("If you want to continue the application press 1 or othervise exit");
					ad=scan.nextInt();
					}
					else if(loginDetail==0)
					{
						ad_entry=0;
					}
					else
					{
						System.out.println("Please enter the valid Number");
						ad_entry=1;
					}
				}
			}
			else
			{
				System.out.println("Please Enter the Correct Key");
				ad=1;
			}
		}
	}
}
