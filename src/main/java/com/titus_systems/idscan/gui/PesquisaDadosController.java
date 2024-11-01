package com.titus_systems.idscan.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PesquisaDadosController {
    
    @FXML
    private Button uploadButton;

    @FXML
    private void voltarParaUpload(){
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent uploadRoot = loader.load();
        Stage stage = (Stage) ((Node) uploadButton).getScene().getWindow(); 
        stage.getScene().setRoot(uploadRoot); // Substitui o conteúdo atual pelo layout de upload

    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }

