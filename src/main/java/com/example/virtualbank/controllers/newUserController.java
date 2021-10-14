package com.example.virtualbank.controllers;

import com.example.virtualbank.Account;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class newUserController {
	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private Label userLabel, passLabel;

	@FXML
	private Button registerBtn;

	@FXML
	private TextField inputName;

	@FXML
	private PasswordField inputPassword;

	public void initialize(){
		choiceBox.getItems().add("Customer");
		choiceBox.getItems().add("Manager");
	}

	String type, message;

	@FXML
	public void onItemSelected() {
		type = choiceBox.getValue();
		userLabel.managedProperty().set(true);
		passLabel.managedProperty().set(true);
		registerBtn.managedProperty().set(true);
		inputName.managedProperty().set(true);
		inputPassword.managedProperty().set(true);
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
	protected void	onRegisterButtonClick(ActionEvent actionEvent) throws IOException {
		boolean valid = !(inputName.getText().isEmpty() || inputPassword.getText().isEmpty());

		if(valid) {
			if(checkSameUsername()) {
				message = "Username already in use.";
			} else {
				message = "You've created a new account!";

				if(choiceBox.getValue().equals("Customer")) {
					Customer customer = new Customer(inputName.getText(), inputPassword.getText());
					registerController.allCustomers.add(customer);

					((Manager) loginController.loggedUser).getCustomerList().add(customer);

					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("loginInformation.txt", true));
					bufferedWriter.append("customer");
					bufferedWriter.newLine();
					bufferedWriter.append(inputName.getText());
					bufferedWriter.newLine();
					bufferedWriter.append(inputPassword.getText());
					bufferedWriter.newLine();
					bufferedWriter.close();
				} else {
					Manager manager = new Manager(inputName.getText(), inputPassword.getText());
					registerController.allManagers.add(manager);

					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("loginInformation.txt", true));
					bufferedWriter.append("manager");
					bufferedWriter.newLine();
					bufferedWriter.append(inputName.getText());
					bufferedWriter.newLine();
					bufferedWriter.append(inputPassword.getText());
					bufferedWriter.newLine();
					bufferedWriter.close();
				}
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

		Node node = (Node)actionEvent.getSource();
		Stage currentStage = (Stage)node.getScene().getWindow();

		fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("manager-menu.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 760, 550);
		currentStage.setTitle("Cryptle");
		currentStage.setScene(scene);
		currentStage.setResizable(false);
		currentStage.show();
	}



}