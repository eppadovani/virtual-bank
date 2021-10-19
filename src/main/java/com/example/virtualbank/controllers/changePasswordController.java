package com.example.virtualbank.controllers;

import com.example.virtualbank.Customer;
import com.example.virtualbank.Manager;
import com.example.virtualbank.virtualBankApplication;
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

public class changePasswordController {
	@FXML
	private TextField inputOldPassword;

	@FXML
	private TextField inputNewPassword;

	String message;

	public boolean changePass() {
		String newPassword = inputNewPassword.getText();
		if (loginController.loggedUser.auth(inputOldPassword.getText(), newPassword)){
			String temporaryString = "";
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader("loginInformation.txt"));
				String lineReading = bufferedReader.readLine();

				while (lineReading != null) {
					temporaryString = temporaryString.concat(lineReading+"\n");
					lineReading = bufferedReader.readLine();
				}
				bufferedReader.close();

				String [] lines = temporaryString.split("\n");

				for (int i = 0; i < lines.length; i++){
					String line = lines[i];
					if (line.equals(loginController.loggedUser.getUsername()) && i + 1 < lines.length)
						lines[i + 1] = newPassword;

						message = "Your password has been changed.";
				}

				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("loginInformation.txt"));
				bufferedWriter.write(String.join("\n", lines));
				bufferedWriter.close();
			}catch (IOException e){
				e.printStackTrace();
			}
			return true;
		}

		message = "Old password doesn't match.";
		return false;
	}

	@FXML
	protected void onChangePasswordButtonClick(ActionEvent actionEvent) throws IOException {
		if(changePass()) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
				fxmlLoader.setController(new popUpController(message));
				Stage errorPopUpStage = new Stage();
				Scene errorPopUpScene = new Scene(fxmlLoader.load(), 320, 183);
				errorPopUpStage.setTitle("Cryptle");
				errorPopUpStage.setScene(errorPopUpScene);
				errorPopUpStage.setResizable(false);
				errorPopUpStage.showAndWait();

				Node node = (Node)actionEvent.getSource();
				Stage currentStage = (Stage)node.getScene().getWindow();

				if(loginController.loggedUser.getType().equals("customer")) {
					fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("customer-menu.fxml"));
					Scene scene = new Scene(fxmlLoader.load(), 760, 550);
					currentStage.setTitle("Cryptle");
					currentStage.setScene(scene);
					currentStage.setResizable(false);
					currentStage.show();
				} else {
					fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("manager-menu.fxml"));
					Scene scene = new Scene(fxmlLoader.load(), 760, 550);
					currentStage.setTitle("Cryptle");
					currentStage.setScene(scene);
					currentStage.setResizable(false);
					currentStage.show();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("popUp-view.fxml"));
			fxmlLoader.setController(new popUpController(message));
			Stage errorPopUpStage = new Stage();
			Scene errorPopUpScene = new Scene(fxmlLoader.load(), 320, 183);
			errorPopUpStage.setTitle("Cryptle");
			errorPopUpStage.setScene(errorPopUpScene);
			errorPopUpStage.setResizable(false);
			errorPopUpStage.show();
		}
	}

	@FXML
	protected void onBackButtonClick(ActionEvent actionEvent) {
		if(loginController.loggedUser.getType().equals("customer")) {
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
		} else {
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

}
