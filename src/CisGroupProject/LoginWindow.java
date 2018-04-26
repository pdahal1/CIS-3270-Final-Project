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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginWindow extends Application {

	ConnectionToSql con = new ConnectionToSql();

	Stage window;
	ConnectionToSql cts = new ConnectionToSql();
	TextField loginField = new TextField();
	TextField passwordField = new TextField();
	
	Button loginButton = new Button("Login");

	Button RegisterButton = new Button("Register");
	Button ForgotPassword = new Button("Forgot Password");

	ConnectionToSql CTS = new ConnectionToSql();

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage PrimaryStage) throws Exception {
		window = PrimaryStage;
		window.setTitle("Login");

		GridPane.setConstraints(loginField, 1, 0);
		loginField.setPromptText("User Name");

		GridPane.setConstraints(passwordField, 1, 1);
		passwordField.setPromptText("Password");
		

		GridPane.setConstraints(loginButton, 1, 2);

		GridPane.setConstraints(RegisterButton, 1, 3);

		GridPane.setConstraints(ForgotPassword, 1, 4);

		RegistrationWindow rw = new RegistrationWindow();
		RegisterButton.setOnAction(e -> {
			try {
				rw.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		loginButton.setOnAction(e -> {
			try {
				Verify(loginField.getText(), loginField.getText(), passwordField.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		ForgotPassword.setOnAction(e -> {
			ForgotPassword FP = new ForgotPassword();
			try {
				FP.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(15.0);

		grid.getChildren().addAll(loginField, passwordField, loginButton, RegisterButton, ForgotPassword);

		Scene scene = new Scene(grid, 200, 200);
		window.setScene(scene);
		window.show();

	}

	public void Verify(String s, String T, String U) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con
					.prepareStatement("select username, password_pw from register where username= '" + s + "'");
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				if (result.getString(1).equals(T) && result.getString(2).equals(U)) {
					MainPage MP = new MainPage();
					try {
						MP.start(window);
						
					} catch (Exception e3) {

						e3.printStackTrace();
						
					}

				} else {
//					RegistrationWindow RW = new RegistrationWindow();
					alert("Mismatch", "Wrong Username or Password");
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
