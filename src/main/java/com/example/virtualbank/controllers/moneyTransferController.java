package com.example.virtualbank.controllers;

import com.example.virtualbank.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;


public class moneyTransferController {
	@FXML
	private ChoiceBox<Account> choiceBoxUserSourceAccounts;

	@FXML
	private ChoiceBox<Account> choiceBoxUserDestinyAccounts;

	@FXML
	private ChoiceBox<Customer> choiceBoxUserSource;

	@FXML
	private ChoiceBox<Customer> choiceBoxUserDestiny;

	@FXML
	private TextField inputAmount;

	@FXML
	private Button transferButton;

	private ArrayList<Customer> customers = ((Manager) loginController.loggedUser).getCustomerList();

	public void initialize() {
		choiceBoxUserSource.getItems().addAll(customers);

		choiceBoxUserSourceAccounts.getItems().sort(new Comparator<Account>() {
			@Override
			public int compare(Account account1, Account account2) {
				return account1.toString().compareTo(account2.toString());
			}
		});

		choiceBoxUserDestinyAccounts.getItems().sort(new Comparator<Account>() {
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
	public void onUserSourceSelected() {
		choiceBoxUserSourceAccounts.disableProperty().set(false);
		choiceBoxUserSourceAccounts.getItems().setAll(choiceBoxUserSource.getSelectionModel().getSelectedItem().getAccounts());

		choiceBoxUserDestiny.getItems().setAll(customers);
		choiceBoxUserDestiny.getItems().remove(choiceBoxUserSource.getSelectionModel().getSelectedItem());

	}

	@FXML
	public void onUserDestinySelected() {
		User selectedUser = choiceBoxUserDestiny.getValue();
		if (selectedUser != null) {
			choiceBoxUserDestinyAccounts.disableProperty().set(false);
			choiceBoxUserDestinyAccounts.getItems().setAll(choiceBoxUserDestiny.getSelectionModel().getSelectedItem().getAccounts());
		}
	}

	@FXML
	public void onDestinyAccountSelected() {
		inputAmount.managedProperty().set(true);
		transferButton.managedProperty().set(true);
	}

	String message;

	@FXML
	protected void onTransferButtonClick(ActionEvent actionEvent) {
		double amount = 0;

		try {
			amount = Double.parseDouble(inputAmount.getText().replace(',', '.'));
			if(choiceBoxUserSourceAccounts.getValue() instanceof SimpleAccount) {
				if(choiceBoxUserSourceAccounts.getValue().withdraw(amount)) {
					message = "Transfer completed.";
					choiceBoxUserSourceAccounts.getValue().bankStatement.add("- " + amount);
					System.out.println(choiceBoxUserSourceAccounts.getValue().bankStatement);

					choiceBoxUserDestinyAccounts.getValue().deposit(amount);
				} else {
					message = "Out of balance.";
				}
			}

			if(choiceBoxUserSourceAccounts.getValue() instanceof SavingsAccount) {
				if(choiceBoxUserSourceAccounts.getValue().withdraw(amount)) {
					message = "Withdraw completed";
					choiceBoxUserSourceAccounts.getValue().bankStatement.add("- " + amount);
					System.out.println(choiceBoxUserSourceAccounts.getValue().bankStatement);

					choiceBoxUserDestinyAccounts.getValue().deposit(amount);
				} else {
					message = "Out of balance.";
				}
			}

			if(choiceBoxUserSourceAccounts.getValue() instanceof SpecialAccount) {
				if(choiceBoxUserSourceAccounts.getValue().withdraw(amount)) {
					message = "Withdraw completed";
					choiceBoxUserSourceAccounts.getValue().bankStatement.add("- " + amount);
					System.out.println(choiceBoxUserSourceAccounts.getValue().bankStatement);

					choiceBoxUserDestinyAccounts.getValue().deposit(amount);
				} else {
					message = "Out of balance and limit.";
				}
			}

		} catch (NumberFormatException numberFormatException) {
			message = "Invalid amount. Try again.";
			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
			fxmlLoader.setController(new popUpController(message));
			Stage popUpStage = new Stage();
			Scene popUpScene = null;
			try {
				popUpScene = new Scene(fxmlLoader.load(), 320, 183);
			} catch (IOException e) {
				e.printStackTrace();
			}
			popUpStage.setTitle("Cryptle");
			popUpStage.setScene(popUpScene);
			popUpStage.setResizable(false);
			popUpStage.showAndWait();

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

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("management-view.fxml"));
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