package com.example.virtualbank.controllers;

import com.example.virtualbank.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class newAccountController {

	Random random = new Random();
	int randomId = random.nextInt(9999);


	@FXML
	protected void onRegularButtonClick(ActionEvent actionEvent) {
		try {
			String message = "You've created a new account.";

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
			fxmlLoader.setController(new popUpController(message));
			Stage popUpStage = new Stage();
			Scene popUpScene = new Scene(fxmlLoader.load(), 272, 183);
			popUpStage.setTitle("Cryptle");
			popUpStage.setScene(popUpScene);
			popUpStage.setResizable(false);
			popUpStage.showAndWait();

			((Customer)loginController.loggedUser).getAccounts().add(new SimpleAccount(randomId, 0));
			((Customer) loginController.loggedUser).setRegularAccounts(1);
			System.out.println(((Customer) loginController.loggedUser).getRegularAccounts());


			Node node = (Node)actionEvent.getSource();
			Stage currentStage = (Stage)node.getScene().getWindow();

			fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("customer-menu.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 760, 550);
			currentStage.setTitle("Cryptle");
			currentStage.setScene(scene);
			currentStage.setResizable(false);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void onSavingsButtonClick(ActionEvent actionEvent) {
		try {
			String message = "You've created a new account.";

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
			fxmlLoader.setController(new popUpController(message));
			Stage popUpStage = new Stage();
			Scene popUpScene = new Scene(fxmlLoader.load(), 272, 183);
			popUpStage.setTitle("Cryptle");
			popUpStage.setScene(popUpScene);
			popUpStage.setResizable(false);
			popUpStage.showAndWait();

			SavingsAccount savingsAccount = new SavingsAccount(randomId, 0);


			((Customer)loginController.loggedUser).getAccounts().add(savingsAccount);
			((Customer)loginController.loggedUser).getSavingsAccountList().add(savingsAccount);

			((Customer) loginController.loggedUser).setSavingsAccounts(1);
			System.out.println(((Customer) loginController.loggedUser).getSavingsAccounts());


			Node node = (Node)actionEvent.getSource();
			Stage currentStage = (Stage)node.getScene().getWindow();

			fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("customer-menu.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 760, 550);
			currentStage.setTitle("Cryptle");
			currentStage.setScene(scene);
			currentStage.setResizable(false);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected  void onSpecialButtonClick(ActionEvent actionEvent) {
		try {
			String message = "You've created a new account.";

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
			fxmlLoader.setController(new popUpController(message));
			Stage popUpStage = new Stage();
			Scene popUpScene = new Scene(fxmlLoader.load(), 272, 183);
			popUpStage.setTitle("Cryptle");
			popUpStage.setScene(popUpScene);
			popUpStage.setResizable(false);
			popUpStage.showAndWait();

			SpecialAccount specialAccount = new SpecialAccount(randomId, 0);

			((Customer)loginController.loggedUser).getAccounts().add(specialAccount);
			((Customer)loginController.loggedUser).getSpecialAccountsList().add(specialAccount);

			((Customer) loginController.loggedUser).setSpecialAccounts(1);
			System.out.println(((Customer) loginController.loggedUser).getSpecialAccounts());


			Node node = (Node)actionEvent.getSource();
			Stage currentStage = (Stage)node.getScene().getWindow();

			fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("customer-menu.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 760, 550);
			currentStage.setTitle("Cryptle");
			currentStage.setScene(scene);
			currentStage.setResizable(false);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}