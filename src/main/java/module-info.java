module com.example.virtualbank {
	requires javafx.controls;
	requires javafx.fxml;

	opens com.example.virtualbank to javafx.fxml;
	exports com.example.virtualbank;
	exports com.example.virtualbank.controllers;
	opens com.example.virtualbank.controllers to javafx.fxml;
}