package CisGroupProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.driver.Message;

public class RegistrationWindow extends Application {

	ConnectionToSql cts = new ConnectionToSql();//
	Stage window;

	RadioButton AdminRadioButton = new RadioButton("Admin");
	RadioButton UserRadioButton = new RadioButton("User");

	Label StatusLabel = new Label("Cannot Register on the database");
	TextField VerifyAdminField = new TextField();
	Button VerifyAdminButton = new Button("verify");

	Label FirstNameLabel = new Label("First Name");
	TextField FirstNameField = new TextField();

	Label LastNameLabel = new Label("Last Name");
	TextField LastNameField = new TextField();

	Label AddressLabel = new Label("Address");
	TextField AddressField = new TextField();

	Label ZipLabel = new Label("Zip code");
	TextField ZipField = new TextField();

	Label StateLabel = new Label("State");
	TextField StateField = new TextField();

	Label UserNameLabel = new Label("Create UserName");
	TextField UserNameField = new TextField();

	Label PasswordLabel = new Label("Password");
	TextField PasswordField = new TextField();

	Label EmailLabel = new Label("Email");
	TextField EmailField = new TextField();

	Label SSNLabel = new Label("Social Security");
	TextField SSNField = new TextField();

	Label SecurityQuestionLabel = new Label("Security question");
	TextField SecurityQuestionField = new TextField(" ");

	Label SecurityAnswerLabel = new Label("Answer");
	TextField SecurityAnswerField = new TextField();

	public Button RegisterButton = new Button("Register");

	Button AdminRegistrationButton = new Button("Register admin");

	Button ClearButton = new Button("clear");

	Button CancelButton = new Button("Cancel");

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setTitle("Register");

		GridPane.setConstraints(FirstNameLabel, 0, 0);
		GridPane.setConstraints(FirstNameField, 1, 0);

		GridPane.setConstraints(LastNameLabel, 3, 0);
		GridPane.setConstraints(LastNameField, 4, 0);

		GridPane.setConstraints(AddressLabel, 0, 1);
		GridPane.setConstraints(AddressField, 1, 1);

		GridPane.setConstraints(ZipLabel, 0, 2);
		GridPane.setConstraints(ZipField, 1, 2);

		GridPane.setConstraints(StateLabel, 3, 2);
		GridPane.setConstraints(StateField, 4, 2);

		GridPane.setConstraints(UserNameLabel, 0, 3);
		GridPane.setConstraints(UserNameField, 1, 3);

		GridPane.setConstraints(PasswordLabel, 3, 3);
		GridPane.setConstraints(PasswordField, 4, 3);

		GridPane.setConstraints(EmailLabel, 0, 4);
		GridPane.setConstraints(EmailField, 1, 4);

		GridPane.setConstraints(SSNLabel, 3, 4);
		GridPane.setConstraints(SSNField, 4, 4);

		GridPane.setConstraints(SecurityQuestionLabel, 0, 5);
		GridPane.setConstraints(SecurityQuestionField, 1, 5);

		GridPane.setConstraints(SecurityAnswerLabel, 3, 5);
		GridPane.setConstraints(SecurityAnswerField, 4, 5);

		// GridPane.setConstraints(AdminCheckBox, 0, 6);

		GridPane.setConstraints(AdminRadioButton, 0, 6);

		GridPane.setConstraints(UserRadioButton, 0, 7);

		GridPane.setConstraints(VerifyAdminField, 1, 6);
		GridPane.setConstraints(VerifyAdminButton, 2, 6);
		VerifyAdminField.setVisible(false);
		VerifyAdminButton.setVisible(false);

		GridPane.setConstraints(StatusLabel, 1, 6);
		StatusLabel.setVisible(false);

		GridPane.setConstraints(RegisterButton, 1, 7);

		GridPane.setConstraints(AdminRegistrationButton, 1, 7);
		AdminRegistrationButton.setVisible(false);

		GridPane.setConstraints(ClearButton, 2, 7);

		GridPane.setConstraints(CancelButton, 3, 7);

		ClearButton.setOnAction(e -> {
			clear();

		});

		RegisterButton.setOnAction(e -> {
			if (ValidateFields() == false) {
				try {
					cts.insertInto(FirstNameField.getText(), LastNameField.getText(), AddressField.getText(),
							ZipField.getText(), StateField.getText(), UserNameField.getText(), PasswordField.getText(),
							EmailField.getText(), SSNField.getText(), SecurityQuestionField.getText(),
							SecurityAnswerField.getText());
					alert("User Created", "User is created: User name : " + UserNameField.getText());
					clear();
					window.close();
					LoginWindow lw = new LoginWindow();
					try {
						lw.start(window);
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}

				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			} else {
				System.out.println("null");
			}

		});

		AdminRegistrationButton.setOnAction(e -> {
			if(ValidateFields()==false){
			try {
				cts.insertIntoAdmin(FirstNameField.getText(), LastNameField.getText(), AddressField.getText(),
						ZipField.getText(), StateField.getText(), UserNameField.getText(), PasswordField.getText(),
						EmailField.getText(), SSNField.getText(), SecurityQuestionField.getText(),
						SecurityAnswerField.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Admin Created");
				alert.setHeaderText(null);
				alert.setContentText("User is created: User name : " + UserNameField.getText());
				alert.showAndWait();
				clear();
				window.close();
				
				  try {
					  LoginWindow lw = new LoginWindow();
					  lw.start(window);
				  } catch (Exception e1) {
					  // TODO Auto-generated catch block
					  e1.printStackTrace();
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			else{
				alert("cant create the account", "Some fields Missing");
			}

		});

		CancelButton.setOnAction(e -> {
			window.close();
			LoginWindow lw = new LoginWindow();
			try {
				lw.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		AdminRadioButton.setOnAction(e -> {
			UserRadioButton.setSelected(false);
			RegisterButton.setVisible(false);
			VerifyAdminField.setVisible(true);
			VerifyAdminButton.setVisible(true);
		});

		UserRadioButton.setOnAction(e -> {
			AdminRadioButton.setSelected(false);
			AdminRegistrationButton.setVisible(false);
			RegisterButton.setVisible(true);
			VerifyAdminField.setVisible(false);
			VerifyAdminButton.setVisible(false);
		});

		VerifyAdminButton.setOnAction(e -> {
			isAdmin(VerifyAdminField.getText().toString());

		});

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(15, 15, 15, 15));
		grid.setVgap(15.0);
		grid.setHgap(15.0);
		grid.getChildren().addAll(FirstNameLabel, FirstNameField, LastNameLabel, LastNameField, AddressLabel,
				AddressField, ZipLabel, ZipField, StateLabel, StateField, UserNameLabel, UserNameField, PasswordLabel,
				PasswordField, EmailLabel, EmailField, SSNLabel, SSNField, SecurityQuestionLabel, SecurityQuestionField,
				SecurityAnswerLabel, SecurityAnswerField, RegisterButton, ClearButton, CancelButton, VerifyAdminField,
				VerifyAdminButton, StatusLabel, AdminRadioButton, UserRadioButton, AdminRegistrationButton);

		Scene scene = new Scene(grid, 650, 350);
		window.setScene(scene);
		window.show();

	}

	public boolean ValidateFields() {
		if (FirstNameField.getText().isEmpty() || LastNameField.getText().isEmpty() || AddressField.getText().isEmpty()
				|| ZipLabel.getText().isEmpty() || StateField.getText().isEmpty() || UserNameField.getText().isEmpty()
				|| PasswordField.getText().isEmpty() || EmailField.getText().isEmpty() || SSNField.getText().isEmpty()
				|| SecurityQuestionField.getText().isEmpty() || SecurityAnswerField.getText().isEmpty()) {
			alert("Validate Fields", "One of more fields are empty");
			return true;
		}
		return false;

	}

	public void isAdmin(String s) {

		if (s.equals("8888")) {
			AdminRegistrationButton.setVisible(true);
			VerifyAdminField.clear();

		} else {
			alert("Invalid Admin Access Code", "You do not have a Admin previledge");
			VerifyAdminField.clear();

		}
	}

	public void clear() {
		FirstNameField.clear();
		LastNameField.clear();
		AddressField.clear();
		ZipField.clear();
		StateField.clear();
		UserNameField.clear();
		PasswordField.clear();
		EmailField.clear();
		SSNField.clear();
		SecurityQuestionField.clear();
		SecurityAnswerField.clear();

	}

	public void alert(String Title, String UserName) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(Title);
		alert.setHeaderText(null);
		alert.setContentText( UserName);
		alert.showAndWait();
	}
	
}
