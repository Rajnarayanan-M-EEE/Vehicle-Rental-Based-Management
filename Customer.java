package Rental_Management;
import java.text.ParseException;
import java.util.*;
public class Customer {
	static Scanner scan=new Scanner(System.in);
public boolean booking(String username) throws ParseException
{
	DatabaseConnectivity custAccess=new DatabaseConnectivity();
	System.out.println("Customer select vehicle if already not booked, Incase any booking it will be returned and to confrim your booking otherwise don't allow booking");
	//int c_id=scan.nextInt();
	String c=custAccess.custidget(username);
	String det[]=c.split("/");
	int cid=Integer.parseInt(det[0]);
	String c_name=det[1];
	boolean check=custAccess.custChecking(cid);
	if(check)
	{
		System.out.println("HI, SELECT VEHICLE ID IN THE BELOW LIST (Status will be Available only you can book otherwise anyother booked or vehicle in service");
		custAccess.getVehicle();
		
		System.out.println("Enter the Vehicle id");
		int v_id=scan.nextInt();
		System.out.println("Enter the From date of rental (dd-mm-yyyy format only)");
		String f_date=scan.nextLine();
		System.out.println("Enter the End date of rental (dd-mm-yyyy format only)");
		String e_date=scan.nextLine();
		custAccess.vehicleBooking(cid,c_name,v_id,f_date,e_date);
		return true;
	}
	else
	{
		System.out.println("You are already rented a vehicle at a time one customer rented one vehicle or low security deposite, Thank you");
		return false;
	}	
}
public void cancel() throws ParseException
{
	DatabaseConnectivity custCancel=new DatabaseConnectivity();
	System.out.println("Welcome to the Cancelletion");
	System.out.println("Enter your booking Id");
	int id=scan.nextInt();
	custCancel.vehicleCancel(id);
	System.out.println("Successfully cancelled");
	
}
}
