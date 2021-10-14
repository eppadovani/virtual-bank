package com.example.virtualbank.controllers;

import com.example.virtualbank.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class managerWithdrawController {
	DecimalFormat dollar = new DecimalFormat("##.##");

	@FXML
	private TextField inputAmount;

	@FXML
	private Button withdrawButton;

	@FXML
	private ChoiceBox<Account> choiceBox;

	public void initialize(){
		ArrayList<Customer> customers = ((Manager) loginController.loggedUser).getCustomerList();
		for (int i = 0; i < customers.size(); i++) {
			choiceBox.getItems().addAll(customers.get(i).getAccounts());
		}

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
		withdrawButton.managedProperty().set(true);
	}

	String message;

	@FXML
	protected void onWithdrawClick(ActionEvent actionEvent) throws IOException {
		double amount = 0;
		amount = Double.parseDouble(inputAmount.getText().replace(',', '.'));

		try {
			if(choiceBox.getValue() instanceof SimpleAccount) {
				if(choiceBox.getValue().withdraw(amount)) {
					message = "Withdraw completed";
					choiceBox.getValue().bankStatement.add("- " + amount);
					System.out.println(choiceBox.getValue().bankStatement);
				} else {
					message = "Out of balance.";
				}
			}

			if(choiceBox.getValue() instanceof SavingsAccount) {
				if(choiceBox.getValue().withdraw(amount)) {
					message = "Withdraw completed";
					choiceBox.getValue().bankStatement.add("- " + amount);
					System.out.println(choiceBox.getValue().bankStatement);
				} else {
					message = "Out of balance.";
				}
			}

			if(choiceBox.getValue() instanceof SpecialAccount) {
				if(choiceBox.getValue().withdraw(amount)) {
					message = "Withdraw completed";
					choiceBox.getValue().bankStatement.add("- " + amount);
					System.out.println(choiceBox.getValue().bankStatement);
				} else {
					message = "Out of balance and limit.";
				}
			}

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

			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("manager-menu.fxml"));
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