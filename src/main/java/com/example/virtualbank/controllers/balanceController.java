package com.example.virtualbank.controllers;

import com.example.virtualbank.Account;
import com.example.virtualbank.Customer;
import com.example.virtualbank.virtualBankApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import java.text.DecimalFormat;

public class balanceController {
	DecimalFormat dollar = new DecimalFormat("##.##");

	@FXML
	private Label balance;

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
	}

	@FXML
	public void onItemSelected() {
		double currentBalance = choiceBox.getValue().getBalance();
		dollar.format(currentBalance);

		balance.setText("Your balance: $" + currentBalance);
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