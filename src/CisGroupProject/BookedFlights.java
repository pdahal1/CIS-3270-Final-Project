package CisGroupProject;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class BookedFlights extends Application {
 
    Stage window;
    
    Button BackButton= new Button("Back");
   

     static String flyingTo1;
    static String flyingFrom1;
    static String dateValidate;
    String dateFlip;
    
    String f1;
    String f2;
    
    TableView<ReservationInfo> table;
   
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    public static Connection getConnection() throws Exception{
    try{
     String driver = "com.mysql.jdbc.Driver";
     String url = "jdbc:mysql://localhost:3306/project";
     String username = "root";
     String password = "root";
     Class.forName(driver);
     
     Connection conn = DriverManager.getConnection(url,username,password);
     System.out.println("Connected");
      return conn;
     }catch(Exception e){System.out.println(e);}
    
    
    return null;
   }
    
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
//        DatePicker date;
  
//        window.setTitle("Update Flights");
        
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(20, 20, 20, 20));
//        grid.setVgap(8);
//        grid.setHgap(10);
        
       // Scene scene = new Scene(grid, 400, 200);
        
//        Label flyingFromLabel = new Label("Flying From:");
        //GridPane.setConstraints(flyingFromLabel , 0, 0);
        //TextField flyingFromInput = new TextField();
        //flyingFromInput.setPromptText("Flying From: ");
        //GridPane.setConstraints(flyingFromInput, 1, 0);
      
       // Label flyingToLabel = new Label("Flying To: ");
        //GridPane.setConstraints(flyingToLabel, 0, 1);
        //TextField flyingToInput = new TextField();
       // flyingToInput.setPromptText("Flying To: ");
       // GridPane.setConstraints(flyingToInput, 1, 1);
        
       // date = new DatePicker();
       // date.setPromptText("Pick Date: ");
        //GridPane.setConstraints(date, 1, 2);
  
        //Button findFlightButton = new Button("Find Flight");
        //GridPane.setConstraints(findFlightButton, 2, 2);
        
       // GridPane.setConstraints(BackButton, 1, 3);
      
        //window.setScene(scene);
        //window.show();
        
       // grid.getChildren().addAll(flyingFromLabel, flyingFromInput, flyingToLabel, flyingToInput, findFlightButton, date, BackButton);
        
//  
//        findFlightButton.setOnAction(e-> {
//         
//         try {
         
                      
          TableColumn<ReservationInfo, String> FlightNumColumn = new TableColumn<>("BookNum");
                FlightNumColumn.setMinWidth(150);
                FlightNumColumn.setCellValueFactory(new PropertyValueFactory<>("BookNum"));
                
             TableColumn<ReservationInfo, String> FlyingToColumn = new TableColumn<>("FlightNum");
             FlyingToColumn.setMinWidth(150);
             FlyingToColumn.setCellValueFactory(new PropertyValueFactory<>("FlightNum"));
                 
                TableColumn<ReservationInfo, String> flyingFromColumn = new TableColumn<>("username");
                flyingFromColumn.setMinWidth(150);
                flyingFromColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
                //TableColumn<ReservationInfo, String> dateColumn = new TableColumn<>("date");
                //dateColumn.setMinWidth(150);
                //dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
                

                table = new TableView<>();
                                
                table.setItems(getReservationInfo());
                                               
                table.getColumns().addAll(FlightNumColumn, FlyingToColumn, flyingFromColumn);

                VBox vBox = new VBox();
                    
                vBox.setSpacing(20);       
                vBox.getChildren().addAll(table);
                
                Scene scene2 = new Scene(vBox);
                
                window.setScene(scene2);
                window.setTitle("Reservation Info");
                window.show();
                
                
        
         
         
         //} catch (Exception e1) {
             //e1.printStackTrace();
        // }
        
        //});
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
    
    
    
    public boolean verifyDatabase(String flyingFrom, String flyingTo, String date) throws Exception
    {
      try{
    
             flyingFrom1 = flyingFrom;
             flyingTo1 = flyingTo;
             dateValidate = date;
             
             Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM bookedflights ");
             ResultSet result = statement.executeQuery();
       
             
             while(result.next()) {
              
              String toCity = result.getString("flyingTo"); // CHANGE TO COLUMN NAME 
              String toCityTest = flyingTo1;
              
              String FromCity = result.getString("flyingFrom"); //CHANGE TO COLUMN NAME 
              String FromCityTest = flyingFrom1;
              
              String FlightDate = result.getString("FlightDate").replaceAll("\\D", "");
              String TestFlightDate = dateValidate; 
         
              if(toCity.equals(toCityTest) && FromCity.equals(FromCityTest) && FlightDate.equals(TestFlightDate))
              {               
               return true;              
              }
    
             } 
         }catch(NumberFormatException e){
         }
     
     return false;
     
    
    }
    
    public ObservableList<ReservationInfo> getReservationInfo() throws Exception{
     
    
        ObservableList<ReservationInfo> info = FXCollections.observableArrayList();
      try {
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM bookedflights ");
   
        ResultSet result = statement.executeQuery();
   
        
        
       while(result.next()) {
     
       String FlightNum = result.getString("FlightNum");
        String FlyingTo = result.getString("FlyingTo"); 
    String flyingFrom = result.getString("flyingFrom");
    String Date = result.getString("FlightDate");
    String Date1 = result.getString("FlightDate").replaceAll("\\D", "");;
    
    
    if(flyingTo1.equals(FlyingTo) && flyingFrom1.equals(flyingFrom) && dateValidate.equals(Date1))
    {
    
    info.add(new ReservationInfo(FlightNum, FlyingTo, flyingFrom, Date));
    }
 
            }
       }catch(Exception e) {System.out.println(e);}
      
     
        return info;
    }
    
    public class ReservationInfo{
     public String  FlightNum;
     public String  FlyingTo;
     public String  flyingFrom;
     public String  date;

        public ReservationInfo(){
        }

        public ReservationInfo(String FlightNum, String FlyingTo, String flyingFrom, String date){
           this.FlightNum = FlightNum;
           this.FlyingTo = FlyingTo;
              this.flyingFrom = flyingFrom;
              this.date = date;
        }
        
        
        public String getFlightNum() {
            return FlightNum;
        }
        public void setFlightNum(String FlightNum) {
            this.FlightNum = FlightNum;
        }
             
        public String getFlyingTo() {
            return FlyingTo;
        }
        public void setFlyingTo(String FlyingTo) {
            this.FlyingTo = FlyingTo;
        }
        
        
        public String getFlyingFrom() {
            return flyingFrom;
        }
        public void setFlyingFrom(String flyingFrom) {
            this.flyingFrom = flyingFrom;
        }     
   
        
        public String getDate() {
            return date;
        }
        
        public void setDate(String date) {
            this.date = date;
        }

    }
    
    
    
    
}





