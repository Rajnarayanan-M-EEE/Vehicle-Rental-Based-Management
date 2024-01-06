package Rental_Management;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Vehicle
{
	static Scanner scan=new Scanner(System.in);
	DatabaseConnectivity vehicle=new DatabaseConnectivity();
	public void set()
	{
		System.out.println("Enter Vehicle name");
    	String v_name=scan.nextLine();
    	System.out.println("Enter the vehicle model");
    	String v_model=scan.nextLine();
    	System.out.println("Enter the vehicle Register Number");
    	String reg=scan.nextLine();
    	System.out.println("Enter the Vehicle Type mention from (Car or Bike)");
    	String v_type=scan.next();
    	System.out.println("Enter the Vehicle Status mention from (Available or Not or Service)");
    	String v_status=scan.next();
    	System.out.println("Enter Vehicle Rent amount per day");
    	int v_rent=scan.nextInt();
    	System.out.println("Enter Vehicle Deposit amount");
    	int v_depo=scan.nextInt();
    	
    	System.out.println(vehicle.setVehicle(v_name,v_model,reg,v_type,v_status,v_rent,v_depo));
	}
	public void get()
	{
		System.out.println("List of the Vehicle our management");
		vehicle.getVehicle();
	}
	public void returnVehicle() throws ParseException
	{
		System.out.println("Enter the Rent Id");
		int id=scan.nextInt();
		vehicle.returnRent(id);
	}
	public void service()
	{
		int entry=1;
		while(entry==1)
		{
			System.out.println("To modify the vehicle status");
			System.out.println("If Vehicle required services Press '1' or exit Press '0' ");
			System.out.println("If Vehicle return after services Press '2' or exit Press '0'");
			int ser=scan.nextInt();
			if(ser==1)
			{
				System.out.println("Enter the Vehicle ID");
				int id=scan.nextInt();
				String sts="Service";
				boolean ret=vehicle.modifyVehicle(id,sts);
				if(ret)
					System.out.println("Successfully Modified");
				else
					System.out.println("You are provided the incorrect data");
				System.out.println("If you want to modify another data press '1' or exit press '0'");
				entry=scan.nextInt();
			}
			else if(ser==2)
			{
				System.out.println("Enter the Vehicle ID");
				int id=scan.nextInt();
				String sts="Available";
				boolean ret=vehicle.modifyVehicle(id,sts);
				if(ret)
					System.out.println("Successfully Modified");
				else
					System.out.println("You are provided the incorrect data");
				System.out.println("If you want to modify another data press '1' or exit press '0'");
				entry=scan.nextInt();
			}
			else if(ser==0)
				entry=0;
			else
				System.out.println("Please enter the valid numbers");
		}
	}
}
