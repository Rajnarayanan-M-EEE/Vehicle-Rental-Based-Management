package Rental_Management;
import java.text.ParseException;
import java.util.*;
public class Admin {
	private static String security_key="SSIET";
	Scanner scan=new Scanner(System.in);
	DatabaseConnectivity adminAccess=new DatabaseConnectivity();
	Vehicle access=new Vehicle();
	public boolean getkey(String key)
	{
		return (key.equals(security_key));
	}
	
	public void menu() throws ParseException
	{
		int menupage=1;
		while(menupage==1)
		{
			System.out.println("------******------- Welcome to the Admin Menu page ------******-------");
			System.out.println("Enter 1 to add Vehicle data");
			System.out.println("Enter 2 to add Customer to the membership");
			System.out.println("Enter 3 to display the Customer membership List");
			System.out.println("Enter 4 to display the Users List");
			System.out.println("Enter 5 to display the Vehicle List");
			System.out.println("Enter 6 to display the Rented Details");
			System.out.println("Enter 7 to edit the Vehicle List");
			System.out.println("Enter 8 to customer return the vehicle");
			System.out.println("Enter 9 to update Security deposit amount for customer");
			System.out.println("Enter 0 to exit the dashboard");
			int input=scan.nextInt();
			if(input==1)
			{
	        	access.set();
	        	System.out.println("Go to the admin menu page press '1' or exit menu page press '0'");
				menupage=scan.nextInt();
			}
			else if(input==2)
			{
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
				
				System.out.println(adminAccess.userSignup(c_name,c_age,c_mobile,c_add,c_aadhno,c_lic,c_username,c_password));
				System.out.println("Go to the admin menu page press '1' or exit menu page press '0'");
				menupage=scan.nextInt();
			}
			else if(input==3)
			{
				System.out.println("List of the customer are Register to our management");
				adminAccess.getCustomer();
				System.out.println("Go to the admin menu page press '1' or exit menu page press '0'");
				menupage=scan.nextInt();
			}
			else if(input==4)
			{
				System.out.println("List of the Admin are access application");
				adminAccess.getAdmin();
				System.out.println("Go to the admin menu page press '1' or exit menu page press '0'");
				menupage=scan.nextInt();
			}
			else if(input==5)
			{
				access.get();
				System.out.println("Go to the admin menu page press '1' or exit menu page press '0'");
				menupage=scan.nextInt();
			}
			else if(input==6)
			{
				System.out.println("List of the Vehicle are rented and the status of the vehicle");
				adminAccess.getRented();
				System.out.println("Go to the admin menu page press '1' or exit menu page press '0'");
				menupage=scan.nextInt();
			}
			else if(input==7)
			{
				access.service();
				System.out.println("Go to the admin menu page press '1' or exit menu page press '0'");
				menupage=scan.nextInt();
			}
			else if(input==8)
			{
				access.returnVehicle();
			}
			else if(input==9)
			{
				System.out.println("Enter the Customer ID");
				int cid=scan.nextInt();
				System.out.println("Enter the Sequrity deposite amount");
				int am=scan.nextInt();
				adminAccess.modifyCustomer(cid,am);
			}
			else if(input==0)
			{
				System.out.println("Exit the admin access page");
				menupage=0;
			}
			else
			{
				System.out.println("Please enter the valid number");
				menupage=1;
			}
		}
	}
	
}
