package com.titus_systems.idscan.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class IndicadorDeCarregamento extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Cria um ProgressIndicator (indicador de carregamento)
        ProgressIndicator progressIndicator = new ProgressIndicator();

        // Configura a aparência do indicador (se necessário)
        progressIndicator.setProgress(-1.0); // -1 indica modo indeterminado

        // Cria um layout para colocar o ProgressIndicator
        StackPane root = new StackPane(progressIndicator);

        // Cria a cena e adiciona o layout
        Scene scene = new Scene(root, 300, 200);

        // Configura a janela (Stage)
        primaryStage.setTitle("Indicador de Carregamento em JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
