package CisGroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ForgotPassword extends Application {
	Stage window;
	String String3;
	String String4;

	private Label EnterUserNameLabel = new Label("Enter your User name");
	private TextField EnterUserName = new TextField();
	private TextField SecurityQuestion = new TextField();
	private TextField SecurityAnswer = new TextField();
	private Button Next = new Button("Next");
	private Button Next1 = new Button("Next1");
	private Button Next2 = new Button("Next2");
	Button CancelButton= new Button("Cancel");
	
	

	public static void main(String[] args) {

		launch(args);
	}

	public void start(Stage PrimaryStage) throws Exception {

		window = PrimaryStage;
		window.setTitle("Password Recovery");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10.0);

		grid.getChildren().addAll(EnterUserNameLabel, EnterUserName, SecurityQuestion, SecurityAnswer, Next, Next1,
				Next2,CancelButton);

		Scene scene = new Scene(grid, 200, 200);
		window.setScene(scene);
		window.show();

		Next1.setVisible(false);
		Next2.setVisible(false);

		GridPane.setConstraints(EnterUserNameLabel, 0, 0);
		GridPane.setConstraints(EnterUserName, 0, 1);
		GridPane.setConstraints(Next, 0, 2);

		GridPane.setConstraints(SecurityQuestion, 0, 2);
		GridPane.setConstraints(Next1, 0, 3);

		GridPane.setConstraints(SecurityQuestion, 0, 2);
		SecurityQuestion.setVisible(false);

		GridPane.setConstraints(SecurityAnswer, 0, 3);
		SecurityAnswer.setVisible(false);
		
		GridPane.setConstraints(CancelButton, 3, 5);

		Next.setOnAction(e -> {
			try {
				Verify(EnterUserName.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		Next1.setOnAction(e -> {
			System.out.println(String3);
			if (SecurityAnswer.getText().equals(String3)) {
				alert("Password", "Your Password is: " + String4);

			} else {
				alert("Cant get your password", "your security Answer didn't match");
			}
		});
		
		CancelButton.setOnAction(e->{
			LoginWindow LW= new LoginWindow();
			try {
				LW.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

	public void Verify(String s) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement(
					"select username, securityquestion, securityanswer, password_pw  from register where username= '"
							+ s + "'");
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				if (result.getString(1).equals(s)) {
					Next.setVisible(false);
					SecurityQuestion.setVisible(true);
					SecurityQuestion.setText(result.getString(2));
					SecurityAnswer.setVisible(true);
					Next1.setVisible(true);
					GridPane.setConstraints(Next1, 0, 4);
					String3 = result.getString(3);
					String4 = result.getString(4);

				}
			}
		} catch (Exception e) {
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

	public void alert(String Title, String UserName) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(Title);
		alert.setHeaderText(null);
		alert.setContentText(UserName);
		alert.showAndWait();
	}

}
