package OnlineShop;
import java.sql.*;
import java.util.*;
import java.io.*;
public class DatabaseConnection {
	static String root;
	public static void makeDatabase()throws IOException
	{
		
		//creating a table return 0 for execute update
		int x;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			int flag=1;
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("CONNECTING TO DATABASE.......");
			do
			{
				System.out.print("ENTER ROOT PASSWORD = ");
				root=br.readLine();
			    try
			    {
			    	Connection check=DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false","root","#AJSurya@1128");
			    	flag=1;
			    }
			    catch(Exception e)
			    {
			    	flag=0;
			    	System.out.println("WRONG PASSWORD ENTERED ! ENTER AGAIN !");
			    }
			}while(flag==0);
			PreparedStatement query;
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false","root","#AJSurya@1128");
			//creating database onlineshop
		    query=con.prepareStatement("create database onlineshop");
		    x=query.executeUpdate();
		   // System.out.println(x);
			/*if(x!=0)
				System.out.println("DATABASE CREATED SUCCESSFULLY......");*/
			
			//selecting database onlineshop
			query=con.prepareStatement("use onlineshop");
		    x=query.executeUpdate();
		    //System.out.println(x);
			/*if(x==0)
				System.out.println("DATABASE SELECTED SUCCESSFULLY......");*/
			
			//creating table admininfo
			query=con.prepareStatement("create table admininfo ( AdminId int not null, Name varchar(20) not null, Age int not null, Email varchar(30) not null, Address varchar(30) not null, ContactNumber varchar(11) not null, primary key(AdminID) )");
		    x=query.executeUpdate();
		    //System.out.println(x);
			/*if(x==0)
				System.out.println("TABLE admininfo CREATED SUCCESSFULLY......");*/
			
			//creating table custinfo
			query=con.prepareStatement("create table custinfo ( CustId int not null, Name varchar(20) not null, Age int not null, Email varchar(30) not null, Address varchar(30) not null, ContactNumber varchar(11) not null, primary key(CustID) )");
		    x=query.executeUpdate();
		    //System.out.println(x);
			/*if(x==0)
				System.out.println("TABLE custinfo CREATED SUCCESSFULLY......");*/
			
			//creating table logininfo
			query=con.prepareStatement("create table logininfo ( userID int not null, password varchar(20) not null, userType char not null, primary key(userID) )");
		    x=query.executeUpdate();
		    //System.out.println(x);
			/*if(x==0)
				System.out.println("TABLE logininfo CREATED SUCCESSFULLY......");*/
			
			//creating table products
			query=con.prepareStatement("create table products (  productID int not null, Name varchar(20) not null, Type varchar(20) not null, Quantity int not null, Price float not null, primary key(productID) )");
		    x=query.executeUpdate();
		    //System.out.println(x);
			/*if(x==0)
				System.out.println("TABLE products CREATED SUCCESSFULLY......");*/
			
			//creating table bills
			query=con.prepareStatement("create table bills ( bill_id int not null, cust_name varchar(20) not null, bill_addr varchar(30) not null, total_amt float not null, primary key(bill_id) )");
		    x=query.executeUpdate();
		    //System.out.println(x);
			/*if(x==0)
				System.out.println("TABLE bills CREATED SUCCESSFULLY......");*/
			
		}
		catch(Exception e)
		{
			System.out.println("");
		}
		System.out.println("DATABASE CONNECTED SUCCESSFULLY.....\n");
	}
	/*public static void main(String args[])
	{
		makeDatabase();
	}*/
}
