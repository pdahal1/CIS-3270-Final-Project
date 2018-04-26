package CisGroupProject;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 import javafx.event.*;

public class SplashScreen extends Application{
	Stage Window1;
	public static void main(String[]args){
		
		Application.launch(args);
			 
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Window1= primaryStage;
		
		
		GridPane p1 = new GridPane();
		Image img = new Image(getClass().getResourceAsStream("fly.gif"));
		
		p1.add(new ImageView(img), 0, 0);
		Button button = new Button("start");
		button.setOnAction(e -> {
		LoginWindow LW= new LoginWindow();
		try {
			LW.start(Window1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		});

		p1.getChildren().add(button);
		
		Scene scene =new Scene(p1,img.getWidth(),img.getHeight()+10);
		primaryStage.setTitle("loading");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	
	
	
}

	  




