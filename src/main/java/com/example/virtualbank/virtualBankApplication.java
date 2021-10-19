package com.example.virtualbank;

import com.example.virtualbank.controllers.loginController;
import com.example.virtualbank.controllers.popUpController;
import com.example.virtualbank.controllers.registerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class virtualBankApplication extends Application {

	@Override
	public void start(Stage stage) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(virtualBankApplication.class.getResource("register-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 760, 550);
		stage.setTitle("Cryptle");
		stage.setScene(scene);
		stage.setResizable(false);

		BufferedReader bufferedReader = new BufferedReader(new FileReader("loginInformation.txt"));
		String lineReading = bufferedReader.readLine();

		String password, username;

		while(lineReading != null) {
			if(lineReading.equals("manager")) {
				username = bufferedReader.readLine();
				password = bufferedReader.readLine();

				Manager manager = new Manager(username, password);
				registerController.allManagers.add(manager);
			} else if(lineReading.equals("customer")) {
				username = bufferedReader.readLine();
				password = bufferedReader.readLine();

				Customer customer = new Customer(username, password);
				registerController.allCustomers.add(customer);
			}

			lineReading = bufferedReader.readLine();
		}

		Image btcFavIcon = new Image(virtualBankApplication.class.getResourceAsStream("images/Bitcoinfavicon.png"));

		stage.getIcons().add(btcFavIcon);
		stage.show();

	}
	public static void main(String[] args) {
		launch();
	}
}