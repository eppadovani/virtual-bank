package com.example.virtualbank.controllers;

import com.example.virtualbank.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class loginController {
	@FXML
	private TextField inputName;

	@FXML
	private PasswordField inputPassword;

	public static User loggedUser;

	protected void customerScreen(ActionEvent actionEvent) throws IOException {
		Node node = (Node)actionEvent.getSource();
		Stage currentStage = (Stage)node.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("customer-menu.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 760, 550);
		currentStage.setTitle("Cryptle");
		currentStage.setScene(scene);
		currentStage.setResizable(false);
		currentStage.show();
	}

	protected void managerScreen(ActionEvent actionEvent) throws IOException {
		Node node = (Node)actionEvent.getSource();
		Stage currentStage = (Stage)node.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("manager-menu.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 760, 550);
		currentStage.setTitle("Cryptle");
		currentStage.setScene(scene);
		currentStage.setResizable(false);
		currentStage.show();
	}

	protected void popUpShow(String message) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
		fxmlLoader.setController(new popUpController(message));
		Stage popUpStage = new Stage();
		Scene popUpScene = new Scene(fxmlLoader.load(), 320, 183);
		popUpStage.setTitle("Cryptle");
		popUpStage.setScene(popUpScene);
		popUpStage.setResizable(false);
		popUpStage.showAndWait();
	}

	@FXML
	protected void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
		for (int i = 0; i < registerController.allCustomers.size(); i++) {
			Customer customer = registerController.allCustomers.get(i);

			if(customer.getUsername().equals(inputName.getText()) && customer.getPassword().equals(inputPassword.getText())) {
				loginController.loggedUser = customer;
				popUpShow("Welcome!");
				customerScreen(actionEvent);
			}
		}

		if(loginController.loggedUser != null) {
			return;
		}

		for (int i = 0; i < registerController.allManagers.size(); i++) {
			Manager manager = registerController.allManagers.get(i);

			if(manager.getUsername().equals(inputName.getText()) && manager.getPassword().equals(inputPassword.getText())) {
				loginController.loggedUser = manager;
				popUpShow("Welcome!");
				managerScreen(actionEvent);
			}

		}

		if(loginController.loggedUser == null) {
			popUpShow("Wrong credentials.");
		}
	}
}