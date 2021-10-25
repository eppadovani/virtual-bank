package com.example.virtualbank.controllers;

import com.example.virtualbank.Account;
import com.example.virtualbank.Customer;
import com.example.virtualbank.virtualBankApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class depositController {
	DecimalFormat dollar = new DecimalFormat("##.##");

	@FXML
	private TextField inputAmount;

	@FXML
	private Button depositButton;

	@FXML
	private ChoiceBox<Account> choiceBox;

	public void initialize(){
		ArrayList<Account> accounts = ((Customer) loginController.loggedUser).getAccounts();
		choiceBox.getItems().addAll(accounts);
		choiceBox.getItems().sort(new Comparator<Account>() {
			@Override
			public int compare(Account account1, Account account2) {
				return account1.toString().compareTo(account2.toString());
			}
		});

		String regex = "[0-9,]+";

		inputAmount.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty())
				return;

			if (!newValue.matches(regex)) {
				inputAmount.setText(oldValue);
			}
		});
	}

	@FXML
	public void onItemSelected() {
		inputAmount.managedProperty().set(true);
		depositButton.managedProperty().set(true);
	}

	@FXML
	protected void onDepositClick(ActionEvent actionEvent) throws IOException {
		double amount = 0;
		String message;
		try {
			amount = Double.parseDouble(inputAmount.getText().replace(',', '.'));
			message = "Deposit completed";

			choiceBox.getValue().deposit(amount);
			choiceBox.getValue().bankStatement.add("+ " + amount);
			System.out.println(choiceBox.getValue().bankStatement);

		}catch (NumberFormatException numberFormatException) {
			message = "Invalid amount. Try again.";
			return;
		}

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
			fxmlLoader.setController(new popUpController(message));
			Stage popUpStage = new Stage();
			Scene popUpScene = new Scene(fxmlLoader.load(), 320, 183);
			popUpStage.setTitle("Cryptle");
			popUpStage.setScene(popUpScene);
			popUpStage.setResizable(false);
			popUpStage.showAndWait();

			Node node = (Node)actionEvent.getSource();
			Stage currentStage = (Stage)node.getScene().getWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void onBackButtonClick(ActionEvent actionEvent) {
		try {
			Node node = (Node)actionEvent.getSource();
			Stage currentStage = (Stage)node.getScene().getWindow();

			currentStage.close();

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("customer-menu.fxml"));
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

}