package CisGroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.javaws.Main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewStatus extends Application{
	Stage window; 
	Button ViewButton = new Button("View Status"); 
	TextField UserName = new TextField(); 
	TextField FlightNumber= new TextField();
	Button BackButton = new Button("Back");
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		ViewButton.setOnAction(e->{
			
		});
		
		window= primaryStage;
		window.setTitle("View Status");
		
		GridPane grid= new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		
		grid.getChildren().addAll(ViewButton, UserName, FlightNumber,BackButton);
		
		GridPane.setConstraints(UserName, 0, 1);
		UserName.setPromptText("UserName");
		
		GridPane.setConstraints(FlightNumber, 1, 1);
		FlightNumber.setPromptText("Flightnumber");
		
		GridPane.setConstraints(ViewButton,2, 1);
		GridPane.setConstraints(BackButton, 2, 2);
		BackButton.setMinSize(80, 10);
		
		Scene scene= new Scene(grid, 400,100);
		window.setScene(scene);
		window.show();
		
		BackButton.setOnAction(e -> {
			window.close();
			MainPage MP = new MainPage();
			try {
				MP.start(window);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	
	
	public  void Verify(String s, String T, String U) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("select username, bookingnumber from bookedflights where flightnumber= '"+s+"'");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				//System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
				if ( result.getString(1).equals(T)&& result.getString(2).equals(U)){
					MainPage MP = new MainPage();// creating object of the MainPage where it will be directed after it is authenticated.
					try {
						MP.start(window);
					} catch (Exception e3) {
						e3.printStackTrace();
					}
	
				}
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
