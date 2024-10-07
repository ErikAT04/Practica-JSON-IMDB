package com.erikat.practica_json.Utils;

import javafx.scene.control.Alert;

//Creador de alertas

public class AlertUtils {
    public static void showAlert(String title, String content, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
