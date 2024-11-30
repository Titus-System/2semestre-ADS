package com.titus_systems.idscan.gui;

import javax.swing.JOptionPane;

import com.titus_systems.idscan.ollama.OllamaEngine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        primaryStage.setTitle("ID Scanner");
        primaryStage.setScene(new Scene(root, 1150, 750)); // Ajuste o tamanho conforme necessário
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logosemfundoetexto_IDScan.png")));
        primaryStage.show();
        OllamaEngine ollamaEng = new OllamaEngine("gemma2:2b", 0.8f);
        try {
            ollamaEng.ping();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ollama não está em execução.\nInicie o Ollama para que a aplicação funcione corretamente.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
