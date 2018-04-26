package CisGroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateFlight extends Application {
	Stage window; 

	public static void main(String[] args) {
		launch(args);
	}
	
	
	TextField AdminUserName= new TextField();
	TextField AdminPassword= new TextField();
	Button EnterButton = new Button("Enter"); 
	Button BackButton= new Button ("Back");

	
	
	
	public void start(Stage primaryStage) throws Exception {
		
		
		
		
		window= primaryStage; 
		window.setTitle("Update flights");
		
		GridPane Grid= new GridPane(); 
		Grid.setPadding(new Insets(10,10,10,10));
		Grid.setVgap(10.0);
		
		Grid.getChildren().addAll(AdminPassword, AdminUserName, EnterButton, BackButton);
		
		GridPane.setConstraints(AdminUserName, 1, 0);
		AdminUserName.setPromptText("Admin UserName");
		AdminUserName.setMinWidth(150);
		
		GridPane.setConstraints(AdminPassword, 1, 1);
		AdminPassword.setPromptText("Admin Password");
		
		GridPane.setConstraints(EnterButton, 1, 2);
		EnterButton.setMinSize(80.0, 10.0);
		
		GridPane.setConstraints(BackButton, 2, 2);
		
		
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
		
		
		
	EnterButton.setOnAction(e->{
		try {
			Verify(AdminUserName.getText(), AdminUserName.getText(),AdminPassword.getText() );
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	});

		
		
		Scene scene= new Scene(Grid, 220,150); 
		window.setScene(scene);
		window.show(); 
		
	}
	public void Verify(String s, String T, String U) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con
					.prepareStatement("select username, password_pw from admin where username= '" + s + "'");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				// System.out.println(result.getString(1) + " " +
				// result.getString(2) + " " + result.getString(3));
				if (result.getString(1).equals(T) && result.getString(2).equals(U)) {
					BookedFlights BFS = new BookedFlights();// creating object of the
													// MainPage where it will be
													// directed after it is
													// authenticated.
					try {
						BFS.start(window);
					} catch (Exception e3) {

						e3.printStackTrace();
					}

				}else {
					RegistrationWindow RW= new RegistrationWindow();
					RW.alert("Mismatch", "Wrong Username or Password");
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}

	}

	public static Connection getConnection() throws Exception {
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
