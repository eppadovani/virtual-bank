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

public class managerSavingsController {
	DecimalFormat dollar = new DecimalFormat("##.##");

	@FXML
	private TextField inputRate;

	@FXML
	private Button saveButton;

	@FXML
	private ChoiceBox<SavingsAccount> choiceBox;

	public void initialize() {
		ArrayList<Customer> customers = ((Manager) loginController.loggedUser).getCustomerList();
		for (int i = 0; i < customers.size(); i++) {
			choiceBox.getItems().addAll(customers.get(i).getSavingsAccountList());
		}

		choiceBox.getItems().sort(new Comparator<Account>() {
			@Override
			public int compare(Account account1, Account account2) {
				return account1.toString().compareTo(account2.toString());
			}
		});

		String regex = "[0-9,]+";

		inputRate.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty())
				return;

			if (!newValue.matches(regex)) {
				inputRate.setText(oldValue);
			}
		});
	}

	@FXML
	public void onItemSelected() {
		inputRate.managedProperty().set(true);
		saveButton.managedProperty().set(true);
	}

	@FXML
	protected void onSaveClick(ActionEvent actionEvent) throws IOException {
		double limitAmount = 0;
		String message;
		try {
			limitAmount = Double.parseDouble(inputRate.getText().replace(',', '.'));
			limitAmount = (limitAmount / 100);

			message = "Rate saved.";

			choiceBox.getValue().setPercentage(limitAmount);
			System.out.println(choiceBox.getValue().getPercentage());

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