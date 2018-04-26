package CisGroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class displayFlights extends Application{
	Stage window;
	static String s1;
	Label label1= new Label(s1);
	
	
	public static void main(String[] args) {
		
		try {
			display();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}

	
	public void start(Stage primaryStage) throws Exception {
		
		window= primaryStage;
		window.setTitle("fuck");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(15.0);

		grid.getChildren().add(label1);

		Scene scene = new Scene(grid, 200, 200);
		window.setScene(scene);
		window.show();
		
		
	}
	
	public static  void display() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("select* from register");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				//s1=result.getString(1);
				System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

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

}
