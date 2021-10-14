package com.example.virtualbank.controllers;

import com.example.virtualbank.Account;
import com.example.virtualbank.Customer;
import com.example.virtualbank.virtualBankApplication;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class bankStatementController {
	@FXML
	private ChoiceBox<Account> choiceBox;

	@FXML
	private ListView<String> listView;

	public void initialize(){
		ArrayList<Account> accounts = ((Customer) loginController.loggedUser).getAccounts();
		choiceBox.getItems().addAll(accounts);
		choiceBox.getItems().sort(new Comparator<Account>() {
			@Override
			public int compare(Account account1, Account account2) {
				return account1.toString().compareTo(account2.toString());
			}
		});

		listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						System.out.println(item);
						super.updateItem(item, empty);

						if(empty) {
							setGraphic(null);
						} else {
							Label label = new Label(item);
							if(item.contains("+")) {
								label.setTextFill(Color.SEAGREEN);
							} else {
								label.setTextFill(Color.ORANGERED);
							}

							setGraphic(label);
						}
					}
				};
			}
		});
	}

	@FXML
	public void onItemSelected() {
		listView.refresh();
		listView.getItems().clear();
		listView.managedProperty().set(true);

		for (int i = 0; i < choiceBox.getValue().bankStatement.size(); i++) {
			listView.getItems().add(String.valueOf(choiceBox.getValue().bankStatement.get(i)));
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