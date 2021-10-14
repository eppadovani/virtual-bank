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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class userInfoController {
	@FXML
	private ChoiceBox<Account> choiceBoxUserAccounts;

	@FXML
	private ChoiceBox<Customer> choiceBoxUser;

	@FXML
	private ListView<String> listView;

	public void initialize(){
		ArrayList<Customer> customers = ((Manager) loginController.loggedUser).getCustomerList();
		for (int i = 0; i < customers.size(); i++) {
			choiceBoxUserAccounts.getItems().addAll(customers.get(i).getAccounts());
		}

		choiceBoxUser.getItems().addAll(customers);

		choiceBoxUserAccounts.getItems().sort(new Comparator<Account>() {
			@Override
			public int compare(Account account1, Account account2) {
				return account1.toString().compareTo(account2.toString());
			}
		});

		listView.setSelectionModel(null);

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
	public void onUserSelected() {
		choiceBoxUserAccounts.managedProperty().set(true);
		choiceBoxUserAccounts.getItems().setAll(choiceBoxUser.getValue().getAccounts());
	}

	@FXML
	public void onAccountSelected() {
		listView.refresh();
		listView.getItems().clear();
		listView.managedProperty().set(true);

		for (int i = 0; i < choiceBoxUserAccounts.getValue().bankStatement.size(); i++) {
			listView.getItems().add(String.valueOf(choiceBoxUserAccounts.getValue().bankStatement.get(i)));
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