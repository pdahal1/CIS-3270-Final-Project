package CisGroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainPage extends Application {
	Stage window;
	Button BookFlightButton=new Button("Book Flight");
	Button ViewFlightStatusButton= new Button("View Status");
	Button UpdateFlightButton = new Button("Update Flight");
	Button CancelButton = new Button ("Cancel");
	
	Button SignOut= new Button("Signout");
	
	

	public static void main(String[] args) throws Exception {
		launch(args);
		//getConnection();
		
	}

	public void start(Stage PrimaryStage) throws Exception {
		window=PrimaryStage;
		window.setTitle("Main Menu");
		
		GridPane grid= new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(15);
		grid.setHgap(15);
		
		GridPane.setConstraints(BookFlightButton, 0, 0);
		GridPane.setConstraints(ViewFlightStatusButton, 0, 1);
		GridPane.setConstraints(UpdateFlightButton, 0, 2);
		GridPane.setConstraints(CancelButton, 0,3);
		GridPane.setConstraints(SignOut, 0, 4);
		
		
		grid.getChildren().addAll(BookFlightButton, ViewFlightStatusButton, UpdateFlightButton, CancelButton, SignOut );
		Scene scene= new Scene(grid, 200,200);
		window.setScene(scene);
		window.show();
		
		CancelButton.setOnAction(e->{
			LoginWindow LW= new LoginWindow();
			try {
				LW.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		BookFlightButton.setOnAction(e->{
			BookFlight BF= new BookFlight();
			try {
				BF.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		ViewFlightStatusButton.setOnAction(e->{
			ViewStatus VS= new ViewStatus();
			try {
				VS.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		UpdateFlightButton.setOnAction(e->{
			 UpdateFlight UF= new UpdateFlight();
			try {
				UF.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		SignOut.setOnAction(e->{
			window.close();
		});
		

	}
	
	public static Connection getConnection() throws Exception{
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
		System.out.println("Connection Established");
		
	} catch (Exception e) {
		System.out.println(e);
	}
		return null;
	
	}
	
	
	
}

