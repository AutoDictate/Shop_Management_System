package OnlineShop;
import java.io.*;
import java.util.*;
import java.sql.*;

public class Shop  {
	static int UID;
	static int CUID;
	public static void main(String[] args) throws IOException
	{
		DatabaseConnection.makeDatabase();
		//Scanner br=new Scanner(System.in);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("WELCOME TO ONLINE SHOPPING SYSTEM\n");
		int ch;
		do
		{
			System.out.println("*****************************************************\n");
			System.out.println("1 - REGISTER AS ADMIN");
			System.out.println("2 - REGISTER AS CUSTOMER");
			System.out.println("3 - LOGIN TO SYSTEM");
			System.out.println("4 - EXIT");
			System.out.println("*****************************************************\n");
			System.out.print("Enter choice : ");
			ch=Integer.parseInt(br.readLine());
			if(ch==1)
				registerAdmin();
			else if(ch==2)
				registerCustomer();
			else if(ch==3)
				loginSystem();
			else if(ch==4)
				System.out.println("Thank you");
			else
				System.out.println("Wrong choice");
		}while(ch!=4);
		
	}
	static void loginSystem()
	{
		String chc;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nWELCOME TO LOGIN PAGE\n");
		System.out.println("*****************************************************\n");
		
		//selecting data from login info table
		ArrayList<Integer> id=new ArrayList<>();
		ArrayList<String> pass=new ArrayList<>();
		ArrayList<Character> type=new ArrayList<>();
		
		try
		{
			int uid;
			String passw;
			char tp=' ';
			Class.forName("com.mysql.jdbc.Driver");

			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
			PreparedStatement ps=con.prepareStatement("select * from logininfo" );

			ResultSet rs=ps.executeQuery();
			//System.out.println("RS  "+rs.next());
			while(rs.next())
			{
				id.add(Integer.parseInt(rs.getString("userID")));
				pass.add(rs.getString("password"));
				type.add((rs.getString("userType")).charAt(0));
			}
			//System.out.println(id);System.out.println(pass);System.out.println(type);
			int flag1=0,flag2=0;
			int f1,f2;
			do
			{
				System.out.print("Enter USER ID : ");
				uid=Integer.parseInt(br.readLine());
				System.out.print("Enter PASSWORD : ");
				passw=br.readLine();
				f1=id.indexOf(uid);
				f2=pass.indexOf(passw);
				if(f1 == f2 && f1 != -1)
				{
					flag1=1;
					flag2=1;
				}
				if(flag1 == 0)
				{
					System.out.println("INVALID CREDENTIALS , ENTER AGAIN !");
					System.out.print("Do you want to continue ( Y for yes, N for No) : ");
					chc=br.readLine();
					if(chc.equalsIgnoreCase("N"))
						break;
				}
					
			}while(flag1==0 || flag2==0);

			if(flag1==1 && flag2==1)
			{
				tp=type.get(id.indexOf(uid));
			}
			//System.out.println(tp);
			//continue the code here........
			if(tp=='A')
			{
				Admin ob=new Admin(uid,passw);
				ob.AdminPage();
			}
			else if(tp=='C')
			{
				Customer ob=new Customer(uid,passw);
				ob.CustomerPage();
			}
		
		}
		catch(Exception e)
		{
			//System.out.println(e);
			e.printStackTrace();
		}
		
	}
	static void registerAdmin()throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String pass,name,num,addr,email;
		int age;
		System.out.println("\nWELCOME TO ADMIN REGISTRATION PAGE\n");
		System.out.println("*****************************************************\n");
		setUID();
		System.out.println("ADMIN ID = "+UID);
		System.out.print("Enter password = ");
		pass=br.readLine();
		System.out.print("Enter Name = ");
		name=br.readLine();
		System.out.print("Enter age = ");
		age=Integer.parseInt(br.readLine());
		System.out.print("Enter contact number = ");
		num=br.readLine();
		System.out.print("Enter address = ");
		addr=br.readLine();
		System.out.print("Enter email = ");
		email=br.readLine();
		
		//inserting data into database
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
			PreparedStatement ps=con.prepareStatement("insert into adminInfo(AdminID,Name,Age,Email,Address,ContactNumber) values(?,?,?,?,?,?)");
			PreparedStatement ps1=con.prepareStatement("insert into loginInfo(userID,password,userType) values(?,?,?)");
			ps.setString(1, Integer.toString(UID));
			ps.setString(2, name);
			ps.setString(3,Integer.toString(age));
			ps.setString(4, email);
			ps.setString(5, addr);
			ps.setString(6, num);
			ps1.setString(1, Integer.toString(UID));
			ps1.setString(2,pass);
			ps1.setString(3, Character.toString('A'));
			int x=ps.executeUpdate();
			int y=ps1.executeUpdate();
			if(x>0 && y>0)
				System.out.println("REGISTRATION DONE SUCCESSFULLY !\n");
			else
				System.out.println("REGISTRATION FAILED !\n");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	static void registerCustomer()throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String pass,name,num,addr,email;
		int age;
		System.out.println("\nWELCOME TO CUSTOMER REGISTRATION PAGE\n");
		System.out.println("*****************************************************\n");
		setCUID();
		System.out.println("CUSTOMER ID = "+CUID);
		System.out.print("Enter password = ");
		pass=br.readLine();
		System.out.print("Enter Name = ");
		name=br.readLine();
		System.out.print("Enter age = ");
		age=Integer.parseInt(br.readLine());
		System.out.print("Enter contact number = ");
		num=br.readLine();
		System.out.print("Enter address = ");
		addr=br.readLine();
		System.out.print("Enter email = ");
		email=br.readLine();
		
		//inserting data into database
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
			PreparedStatement ps=con.prepareStatement("insert into custInfo(CustID,Name,Age,Email,Address,ContactNumber) values(?,?,?,?,?,?)");
			PreparedStatement ps1=con.prepareStatement("insert into loginInfo(userID,password,userType) values(?,?,?)");
			ps.setString(1, Integer.toString(CUID));
			ps.setString(2, name);
			ps.setString(3,Integer.toString(age));
			ps.setString(4, email);
			ps.setString(5, addr);
			ps.setString(6, num);
			ps1.setString(1, Integer.toString(CUID));
			ps1.setString(2,pass);
			ps1.setString(3, Character.toString('C'));
			int x=ps.executeUpdate();
			int y=ps1.executeUpdate();
			if(x>0 && y>0)
				System.out.println("REGISTRATION DONE SUCCESSFULLY !\n");
			else
				System.out.println("REGISTRATION FAILED !\n");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	static void setCUID()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
			PreparedStatement ps=con.prepareStatement("select CustID from custinfo");
			ResultSet rs=ps.executeQuery();
			int x=199;
			while(rs.next()) {
				x=Integer.parseInt(rs.getString("CustID"));
			}
			CUID=x+1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println(e);
		}
	}
	static void setUID()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
			PreparedStatement ps=con.prepareStatement("select AdminID from admininfo");
			ResultSet rs=ps.executeQuery();
			int x=99;
			while(rs.next()) {
				x=Integer.parseInt(rs.getString("AdminID"));
			}
			UID=x+1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println(e);
		}
	}
}
