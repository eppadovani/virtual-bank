package com.example.virtualbank.controllers;

import com.example.virtualbank.Customer;
import com.example.virtualbank.Manager;
import com.example.virtualbank.virtualBankApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class registerController {
	@FXML
	private Label errorMsg;

	@FXML
	private PasswordField inputPassword;

	@FXML
	private TextField inputName;

	public static ArrayList<Customer> allCustomers = new ArrayList<>();
	public static ArrayList<Manager> allManagers = new ArrayList<>();

	@FXML
	protected void onManagerButtonClick(ActionEvent actionEvent) {
		try {
			Node node = (Node)actionEvent.getSource();
			Stage currentStage = (Stage)node.getScene().getWindow();

			currentStage.close();

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("managerRegistration-view.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(fxmlLoader.load(), 760, 550);
			stage.setTitle("Cryptle");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void onCustomerButtonClick(ActionEvent actionEvent) {
		try {
			Node node = (Node)actionEvent.getSource();
			Stage currentStage = (Stage)node.getScene().getWindow();

			currentStage.close();

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("customerRegistration-view.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(fxmlLoader.load(), 760, 550);
			stage.setTitle("Cryptle");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkLogins() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("loginInformation.txt"));
			return bufferedReader.readLine() != null;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@FXML
	protected void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
		if(checkLogins()) {
			try {
				Node node = (Node)actionEvent.getSource();
				Stage currentStage = (Stage)node.getScene().getWindow();

				currentStage.close();

				FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("login-view.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(fxmlLoader.load(), 760, 550);
				stage.setTitle("Cryptle");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
			fxmlLoader.setController(new popUpController("You didn't create any account yet."));
			Stage errorPopUpStage = new Stage();
			Scene errorPopUpScene = new Scene(fxmlLoader.load(), 320, 183);
			errorPopUpStage.setTitle("Cryptle");
			errorPopUpStage.setScene(errorPopUpScene);
			errorPopUpStage.setResizable(false);
			errorPopUpStage.show();
		}
	}

	private boolean checkSameUsername() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("loginInformation.txt"));
		String lineReading = bufferedReader.readLine();

		while(lineReading != null) {
			if(lineReading.equals(inputName.getText())) {
				return true;
			} else {
				lineReading = bufferedReader.readLine();
			}
		}
		return false;
	}

	@FXML
	protected void onManagerRegisterButtonClick(ActionEvent actionEvent) {
		try {
			String message;
			boolean valid = !(inputName.getText().isEmpty() || inputPassword.getText().isEmpty());

			if(valid) {
				if(checkSameUsername()) {
					message = "Username already in use.";
				} else {
					message = "You've created a new account!";

					Manager manager = new Manager(inputName.getText(), inputPassword.getText());
					allManagers.add(manager);
				}
			} else {
				message = "Fill all the fields.";
			}

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
			fxmlLoader.setController(new popUpController(message));
			Stage popUpStage = new Stage();
			Scene popUpScene = new Scene(fxmlLoader.load(), 272, 183);
			popUpStage.setTitle("Cryptle");
			popUpStage.setScene(popUpScene);
			popUpStage.setResizable(false);
			popUpStage.showAndWait();

			if(valid && !checkSameUsername()) {
				Node node = (Node)actionEvent.getSource();
				Stage currentStage = (Stage)node.getScene().getWindow();

				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("loginInformation.txt", true));
				bufferedWriter.append("manager");
				bufferedWriter.newLine();
				bufferedWriter.append(inputName.getText());
				bufferedWriter.newLine();
				bufferedWriter.append(inputPassword.getText());
				bufferedWriter.newLine();
				bufferedWriter.close();

				fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("register-view.fxml"));
				Scene scene = new Scene(fxmlLoader.load(), 760, 550);
				currentStage.setTitle("Cryptle");
				currentStage.setScene(scene);
				currentStage.setResizable(false);
				currentStage.show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void onCustomerRegisterButtonClick(ActionEvent actionEvent) {
		try {
			String message;
			boolean valid = !(inputName.getText().isEmpty() || inputPassword.getText().isEmpty());

			if(valid) {
				if(checkSameUsername()) {
					message = "Username already in use.";
				} else {
					message = "You've created a new account!";

					Customer customer = new Customer(inputName.getText(), inputPassword.getText());
					allCustomers.add(customer);
				}
			} else {
				message = "Fill all the fields.";
			}

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
			fxmlLoader.setController(new popUpController(message));
			Stage popUpStage = new Stage();
			Scene popUpScene = new Scene(fxmlLoader.load(), 272, 183);
			popUpStage.setTitle("Cryptle");
			popUpStage.setScene(popUpScene);
			popUpStage.setResizable(false);
			popUpStage.showAndWait();

			if(valid && !checkSameUsername()) {
				Node node = (Node)actionEvent.getSource();
				Stage currentStage = (Stage)node.getScene().getWindow();

				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("loginInformation.txt", true));
				bufferedWriter.append("customer");
				bufferedWriter.newLine();
				bufferedWriter.append(inputName.getText());
				bufferedWriter.newLine();
				bufferedWriter.append(inputPassword.getText());
				bufferedWriter.newLine();
				bufferedWriter.close();

				fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("register-view.fxml"));
				Scene scene = new Scene(fxmlLoader.load(), 760, 550);
				currentStage.setTitle("Cryptle");
				currentStage.setScene(scene);
				currentStage.setResizable(false);
				currentStage.show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
