package com.example.virtualbank.controllers;

import com.example.virtualbank.virtualBankApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class popUpController {
	@FXML
	private Label popUpMsg;

	@FXML
	private Button okBtn;

	String popUpMessage;

	@FXML
	protected void onOkButtonClick(ActionEvent actionEvent) {
		Node node = (Node)actionEvent.getSource();
		Stage currentStage = (Stage)node.getScene().getWindow();
		currentStage.close();
	}

	public popUpController(String message) {
		popUpMessage = message;
	}

	public void initialize() {
		okBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				onOkButtonClick(event);
			}
		});

		popUpMsg.setText(popUpMessage);
	}
}
