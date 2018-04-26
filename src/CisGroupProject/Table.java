package CisGroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Table extends Application {
 
    Stage window;
    String flyingTo1;
    String flyingFrom1;
    String dateValidate;
    String dateFlip;
    public static void main(String[] args) {
        launch(args);
    }
    public static Connection getConnection() throws Exception{
    try{
     String driver = "com.mysql.jdbc.Driver";
     String url = "jdbc:mysql://localhost:3306/demo";
     String username = "student";
     String password = "student";
     Class.forName(driver);
     
     Connection conn = DriverManager.getConnection(url,username,password);
     System.out.println("Connected");
     return conn;
    } catch(Exception e){System.out.println(e);}
    
    
    return null;
   }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        DatePicker date;
  
        window.setTitle("Book Your Flight");

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        Label flyingFromLabel = new Label("Flying From:");
        GridPane.setConstraints(flyingFromLabel , 0, 0);
        TextField flyingFromInput = new TextField("Flying From: ");
        GridPane.setConstraints(flyingFromInput, 1, 0);
      
        Label flyingToLabel = new Label("Flying To: ");
        GridPane.setConstraints(flyingToLabel, 0, 1);
        TextField flyingToInput = new TextField();
        flyingToInput.setPromptText("Flying To: ");
        GridPane.setConstraints(flyingToInput, 1, 1);
        date = new DatePicker();
  date.setPromptText("Pick Date: ");
  GridPane.setConstraints(date, 1, 2);
  
        Button findFlightButton = new Button("Find Flight");
        GridPane.setConstraints(findFlightButton, 2, 2);
      
  
        findFlightButton.setOnAction(e-> {
   try {
    verifyDatabase(flyingFromInput.getText(), flyingToInput.getText(), 
      dateFlip = date.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));
   } catch (Exception e1) {
    e1.printStackTrace();
   }
  });
        
  
        grid.getChildren().addAll(flyingFromLabel, flyingFromInput, flyingToLabel, flyingToInput, findFlightButton, date);

        Scene scene = new Scene(grid, 400, 200);
        window.setScene(scene);
        window.show();
    }
    
    public void verifyDatabase(String flyingFrom, String flyingTo, String date) throws Exception
    {
      try{
    
             flyingFrom1 = flyingFrom;
             flyingTo1 = flyingTo;
             dateValidate = date;
             
             Connection con = getConnection();
       PreparedStatement statement = con.prepareStatement("SELECT * FROM bookflight ");
       
       ResultSet result = statement.executeQuery();
       
             
             while(result.next()) {
              
              String toCity = result.getString("toCity"); // CHANGE TO COLUMN NAME 
              String toCityTest = flyingTo1;
              
              String FromCity = result.getString("Duration"); //CHANGE TO COLUMN NAME 
              String FromCityTest = flyingFrom1;
              
              String FlightDate = result.getString("FlightDate").replaceAll("\\D", "");
              String TestFlightDate = dateValidate; 
              
              
     
              if(toCityTest.equals(toCityTest) && FromCity.equals(FromCityTest) && FlightDate.equals(TestFlightDate))
              {
              System.out.println(toCity);
              }
   
              
    }
             
             //System.out.println(flyingFrom1 + flyingTo1 + date);

         }catch(NumberFormatException e){

         }
     
     
     
    }

}




	
