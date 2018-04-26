package CisGroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionToSql {
	
	
	public static void main(String[] args) throws Exception {
		
		//getConnection();
		//createTable();
		//insertInto();
		//deleteFrom();
		display();
	}

	public static  Connection getConnection() throws Exception {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			
			return con;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}
	public static void display() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("select* from register");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	
	public static void createTable() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE table HELLO( ownerid varchar(25) PRIMARY KEY)");
																												
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println("Table could not be created, this is because table alreasy exists");

		} finally {
			System.out.println();
		}

	}

	public void insertInto(String a, String b, String c, String d,  String e,String f,String g, String h, String  i, String j, String k) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement add = con.prepareStatement(
					"INSERT INTO register ( firstname, lastname, Address, zipcode,  state, username, password_pw,"
					+ " email, socialsecurity, securityQuestion, securityAnswer) "
					+ "VALUES ( '"+a+"', '"+b+"', '"+c+"','"+d+"','"+e+"','"+f+"', '"+g+"','"+h+"','"+i+"','"+j+"','"+k+"')");
			add.executeUpdate();
			System.out.println("Row inserted");
		} catch (Exception e1) {
			System.out.println("cant add ");;

		} finally {
			
		}

	}
	
	public void insertIntoAdmin(String a, String b, String c, String d,  String e,String f,String g, String h, String  i, String j, String k) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement add = con.prepareStatement(
					"INSERT INTO admin ( firstname, lastname, Address, zipcode,  state, username, password_pw,"
					+ " email, socialsecurity, securityQuestion, securityAnswer) "
					+ "VALUES ( '"+a+"', '"+b+"', '"+c+"','"+d+"','"+e+"','"+f+"', '"+g+"','"+h+"','"+i+"','"+j+"','"+k+"')");
			add.executeUpdate();
			System.out.println("Row inserted");
		} catch (Exception e1) {
			System.out.println("cant add ");;

		} finally {
			
		}

	}

	public static void deleteFrom() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement add = con.prepareStatement("DELETE FROM terminal WHERE terminalid= 2");// creates
																					// exist
			add.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);

		} finally {
			System.out.println("Row deleted");
		}
	}

	
}
	


