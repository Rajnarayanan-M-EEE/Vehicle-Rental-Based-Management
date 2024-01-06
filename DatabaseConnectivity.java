package Rental_Management;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.sql.ResultSet;
import java.util.*;
public class DatabaseConnectivity 
{
	static Scanner scan=new Scanner(System.in);
	public String execute(String s)
	{
		try
		{
			String output="";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_management","root","21EE037@r0702");
			Statement stmt=con.createStatement();
			ResultSet result=stmt.executeQuery(s);
			while(result.next())
			{
			output=result.getString(1);
			}
			return output;
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println(e.getMessage());
			return s;
		}
	}
	//Execute---------------------------------------------------
	
	public boolean update(String s)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_management","root","21EE037@r0702");
			Statement stmt=con.createStatement();
			stmt.executeUpdate(s);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println(e.getMessage());
			return false;
		}
	}
	//Update----------------------------------------------------
	
	public void display(String s,int size)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_management","root","21EE037@r0702");
			Statement stmt=con.createStatement();
			ResultSet result=stmt.executeQuery(s);
			while(result.next())
			{
				for(int i=1;i<=size;i++)
					System.out.print(result.getString(i)+" | ");
				
				System.out.println();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println(e.getMessage());
		}
	}
	
	
	public Boolean adminLogin(String n,String p)
	{
		
		String query="SELECT A_PASSWORD FROM admin WHERE A_USERNAME = '"+n+"';";
		String query_result=execute(query);
		String query1="SELECT A_USERNAME FROM admin WHERE A_PASSWORD = '"+p+"';";
		String query_result1=execute(query1);
		if(query_result.equals(p)&&query_result1.equals(n))
			return true;
		else
			return false;
	}
	
	
	public Boolean userLogin(String n,String p)
	{
		
		String query="SELECT C_PASSWORD FROM customer WHERE C_USERNAME = '"+n+"';";
		String query_result=execute(query);
		String query1="SELECT C_USERNAME FROM customer WHERE C_PASSWORD = '"+p+"';";
		String query_result1=execute(query1);
		if(query_result.equals(p)&&query_result1.equals(n))
			return true;
		else
			return false;
	}
	
	
	public String adminSignup(String nam,String mobile,String email,int age,String add,String username,String password)
	{
		String query="INSERT INTO ADMIN (A_NAME,A_MOBILE,A_AGE,A_ADDERSS,A_EMAIL,A_USERNAME,A_PASSWORD)"
				+ "VALUES('" + nam + "','" + mobile + "','" + age + "','" + add + "','" + email + "','"+ username + "','"  + password + "');";
		boolean query_result=update(query);
		if(query_result)
		return "Data Sueccessfully registered now login to access the application";
		else
			return "Data will be an error";
	}
	
	
	public String userSignup(String nam,int age,String mobile,String add,String aadh,String lic,String username,String password)
	{
		String query="INSERT INTO CUSTOMER (C_NAME,C_AGE,C_MOBILE,C_ADDRESS,AADHAR_NO,LICENSE_NO,C_STATUS,S_DEPOSIT,C_USERNAME,C_PASSWORD)"
				+ "VALUES('" + nam + "','"  + age + "','"+ mobile + "','"  + add + "','" + aadh +"','"+ lic + "','Not',0,'"+ username + "','"  + password + "');";
		boolean query_result=update(query);
		if(query_result)
			return "Data Sueccessfully registered now login to access the application";
			else
				return "Data will be an error";
	}
	
	
	public String setVehicle(String v_name,String v_model,String reg,String v_type,String v_status,int v_rent,int v_depo)
	{
		String query="INSERT INTO VEHICLE (V_NAME,V_MODEL,V_REGNO,V_STATUS,V_TYPE,RENT_PER_DAY,V_DEPOSIT)"
				+"VALUES('"+v_name+"','"+v_model+"','"+reg+"','"+v_status+"','"+v_type+"','"+v_rent+"','"+v_depo+"');";
		boolean query_result=update(query);
		if(query_result)
			return "Data Successfully stored to the list of the application";
			else
				return "Data will be an error";
	}
	
	
	public boolean custChecking(int id)
	{
		String query="SELECT C_STATUS FROM CUSTOMER WHERE C_ID ="+id+";";
		String query_result=execute(query);
		String query1="SELECT S_DEPOSIT FROM CUSTOMER WHERE C_ID ="+id+";";
		String query_result1=execute(query1);
		int sdeposit=Integer.parseInt(query_result1);
		if(query_result.equals("Not")&&sdeposit>=0)
			return true;
		else
			return false;
	}
	
	public void vehicleBooking(int c_id,String c_name,int v_id,String f_date,String e_date) throws ParseException
	{
		String queryv_type="SELECT V_TYPE FROM VEHICLE WHERE V_ID ="+v_id+";";
		String v_type=execute(queryv_type);
		String query2="SELECT RENT_PER_DAY FROM VEHICLE WHERE V_ID ="+v_id+";";
		String result2=execute(query2);
		String query3="SELECT V_DEPOSIT FROM VEHICLE WHERE V_ID ="+v_id+";";
		String result3=execute(query3);
		int vdep=Integer.parseInt(result3);
		int rentperday=Integer.parseInt(result2);
	     SimpleDateFormat dateForm=new SimpleDateFormat("dd-MM-yyyy");
	     String d=new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
	     
		String query="INSERT INTO RENT_DETAILS (C_ID,C_NAME,V_ID,R_STATUS,V_TYPE,B_DATE,FROM_DATE,END_DATE,FINE_AMOUNT,TOTAL_RENT,V_DEPOSIT)"
				+"VALUES('"+c_id+"','"+c_name+"','"+v_id+"','Booked','"+v_type+"','"+d+"','"+f_date+"','"+e_date+"',0,"+rentperday+",'"+vdep+"');";
		boolean query_result=update(query);
		String rid="SELECT R_ID FROM RENT_DETAILS WHERE C_ID="+c_id+" AND FROM_DATE='"+f_date+"';";
		String billno=execute(rid);
		System.out.println("Your Booking Id is "+billno+" Please remember for cancelation procces");
		System.out.println("Your Rental booked is confrimed please collect the vehicle on "+f_date+" and retrun "+e_date+" Incase you didn't retuen a car fine amount will be icreased per day at half of the rent per day "
				+ "If you cancel the rental before "+f_date+", After the cancelletion not available you didn't get car on time and return the rent amount will be reduced to your security deposit amount");
	}
	
	
	public void vehicleCancel(int id) throws ParseException
	{
		 SimpleDateFormat dateForm=new SimpleDateFormat("dd-MM-yyyy");
		    String d=new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
		    java.util.Date curr=dateForm.parse(d);
		    String query="SELECT FROM_DATE FROM RENT_DETAILS WHERE R_ID="+id+";";
		    String result=execute(query);
		    String query1="SELECT V_ID FROM RENT_DETAILS WHERE R_ID="+id+";";
		    String result1=execute(query1);
		    String query4="SELECT C_ID FROM RENT_DETAILS WHERE R_ID="+id+";";
		    String result4=execute(query4);
		    String query7="SELECT TOTAL_RENT FROM RENT_DETAILS WHERE R_ID="+id+";";
		    String result7=execute(query7);
		    int v_id=Integer.parseInt(result1);
		    int c_id=Integer.parseInt(result4);
		    java.util.Date fromdate=dateForm.parse(result);
		    long diff=Math.abs(fromdate.getTime()-curr.getTime());
			int r=(int) TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
			if(r>=0)
			{
				System.out.println("Cancelleation Available");
				String query2="UPDATE VEHICLE SET V_STATUS ='Available' WHERE V_ID="+v_id+";";
				update(query2);
				String query3="UPDATE CUSTOMER SET C_STATUS ='Not' WHERE C_ID="+c_id+";";
				update(query3);
				String query5="UPDATE RENT_DETAILS SET R_STATUS ='Cancelled' WHERE R_ID="+id+";";
				update(query5);
			}
			else
			{
				int rentpd=Integer.parseInt(result7);
				System.out.print("Cancelletion is not available, you cancle the Booking Pay a  half of the amount of days rent in your security deposite");
				int y=Math.abs(r);
				rentpd=rentpd*y;
				rentpd=rentpd/2;
				String query8="UPDATE RENT_DETAILS SET TOTAL_RENT ="+rentpd+" WHERE R_ID="+id+";";
				update(query8);
				 String query10="SELECT S_DEPOSIT FROM CUSTOMER WHERE C_ID="+c_id+";";
				    String result10=execute(query10);
				    int sdep=Integer.parseInt(result10);
				    sdep=sdep-rentpd;
				String query9="UPDATE CUSTOMER SET S_DEPOSIT ="+sdep+" WHERE C_ID="+c_id+";";
				update(query9);
				
			}
	}
	public String custidget(String username)
	{
		String query="SELECT C_ID FROM CUSTOMER WHERE C_USERNAME='"+username+"';";
		String si=execute(query);
		String query1="SELECT C_NAME FROM CUSTOMER WHERE C_USERNAME='"+username+"';";
		String si1=execute(query1);
		return si+"/"+si1;
		
	}
	public void getCustomer()
	{
		String query="SELECT * FROM CUSTOMER";
		int col=11;
		display(query,col);
	}
	
	public void getAdmin()
	{
		String query="SELECT * FROM ADMIN";
		int col=8;
		display(query,col);
	}
	
	public void getVehicle()
	{
		String query="SELECT * FROM VEHICLE";
		int col=8;
		display(query,col);
	}
	
	public void getRented()
	{
		String query="SELECT * FROM RENT_DETAILS";
		int col=12;
		display(query,col);
	}
	
	public boolean modifyVehicle(int v_id,String status)
	{
		String query="UPDATE VEHICLE SET V_STATUS = '"+status+"' WHERE V_ID="+v_id+";";
		return update(query);
	}
	
	public boolean modifyCustomer(int id,int amount)
	{
		String query="UPDATE CUSTOMER SET S_DEPOSIT = "+amount+" WHERE C_ID="+id+";";
		System.out.println("Successfully added");
		return update(query);
		}
	
	public void returnRent(int id) throws ParseException
	{
		 SimpleDateFormat dateForm=new SimpleDateFormat("dd-MM-yyyy");
		    String d=new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
		    java.util.Date curr=dateForm.parse(d);
		    String query1="SELECT FROM_DATE FROM RENT_DETAILS WHERE R_ID="+id+";";
		    String result1=execute(query1);
		    String query2="SELECT END_DATE FROM RENT_DETAILS WHERE R_ID="+id+";";
		    String result2=execute(query2);
		    String query3="SELECT V_ID FROM RENT_DETAILS WHERE R_ID="+id+";";
		    String result3=execute(query3);
		    String query4="SELECT C_ID FROM RENT_DETAILS WHERE R_ID="+id+";";
		    String result4=execute(query4);
		    String query5="SELECT TOTAL_RENT FROM RENT_DETAILS WHERE R_ID="+id+";";
		    String result5=execute(query5);
		    int rent=Integer.parseInt(result5);
		    int v_id=Integer.parseInt(result3);
		    int c_id=Integer.parseInt(result4);
		    java.util.Date fromdate=dateForm.parse(result1);
		    java.util.Date enddate=dateForm.parse(result2);
		    long t_rent=Math.abs(enddate.getTime()-fromdate.getTime());
		    int total_rent=(int) TimeUnit.DAYS.convert(t_rent,TimeUnit.MILLISECONDS);
		    total_rent=total_rent*rent;
		    long fine=Math.abs(curr.getTime()-enddate.getTime());
		    int fine_rent=(int) TimeUnit.DAYS.convert(fine,TimeUnit.MILLISECONDS);
		    fine_rent=fine_rent*rent;
			String query11="SELECT V_DEPOSIT FROM RENT_DETAILS WHERE R_ID="+id+";";
			String result11=execute(query11);
			int deposit=Integer.parseInt(result11);
			System.out.println("Enter the vehicle conditon in given Number (0 for No Damage, 25 for Low, 50 for Medium, 75 for High");
			int cond=scan.nextInt();
			int vdep=0;
			if(cond==0)
			{
				vdep=0;
			}
			else if(25==cond)
			{
				vdep=(25/100)*deposit;
			}
			else if(50==cond)
			{
				vdep=(50/100)*deposit;
			}
			else if(75==cond)
			{
				vdep=(75/100)*deposit;
			}
			String query12="UPDATE RENT_DETAILS SET V_DEPOSIT = "+vdep+" WHERE R_ID="+id+";";
			update(query12);
			int ret=deposit-vdep;
			System.out.println("Balance of vehicle deposit amount of "+ret+" to the customer ");
		    System.out.println("Total amount of the fine + rent is "+(total_rent+fine_rent));
		    String query6="UPDATE VEHICLE SET V_STATUS ='Available' WHERE V_ID="+v_id+";";
			update(query6);
			String query7="UPDATE CUSTOMER SET C_STATUS ='Not' WHERE C_ID="+c_id+";";
			update(query7);
			String query8="UPDATE RENT_DETAILS SET R_STATUS ='Returned' WHERE R_ID="+id+";";
			update(query8);
			String query9="UPDATE RENT_DETAILS SET FINE_AMOUNT ="+fine_rent+" WHERE R_ID="+id+";";
			update(query9);
			String query10="UPDATE RENT_DETAILS SET TOTAL_RENT ="+total_rent+" WHERE R_ID="+id+";";
			update(query10);
	}
}
