package com.titus_systems.idscan.gui;

import java.io.File;

import com.titus_systems.idscan.ollama.ImageProcessor;

import io.github.ollama4j.utils.PromptBuilder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private TextField filePath;

    @FXML
    private Label statusLabel;

    @FXML
    public void handleUpload() {
        // Abrir o seletor de arquivos
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione a imagem do documento");
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            filePath.setText(file.getAbsolutePath());
            statusLabel.setText("Arquivo carregado: " + file.getName());

            // Código para processar a imagem de forma assíncrona -> Precisa do caminho da imagem selecionada
            ImageProcessor imgProcessor = new ImageProcessor("gemma2:2b");
            PromptBuilder prompt = new PromptBuilder();
            prompt.add("what is this image?");

            imgProcessor.asyncProcessWithTesseract(file.getAbsolutePath(), prompt, result -> {
                System.out.println("Resultado: " + result);
                Platform.runLater(() -> {
                    // Criar e mostrar uma nova janela
                    Stage newStage = new Stage();
                    VBox vbox = new VBox();
                    Label label = new Label("Resultado: " + result);
                    vbox.getChildren().add(label);

                    Scene scene = new Scene(vbox, 700, 900);
                    newStage.setScene(scene);
                    newStage.setTitle("Resultado do Processamento");
                    newStage.show();
                });
            });

        } else {
            statusLabel.setText("Nenhum arquivo selecionado");
        }
    }
}
