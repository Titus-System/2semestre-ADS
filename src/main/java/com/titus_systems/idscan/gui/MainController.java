package com.titus_systems.idscan.gui;

import java.io.File;

import com.titus_systems.idscan.ollama.ImageProcessor;

import io.github.ollama4j.utils.PromptBuilder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private ImageView selecao;

    // @FXML
    // private TextField filePath;

    @FXML
    private Label statusLabel;

    @FXML
    private Button ButtonUpload;

    @FXML
    public void initialize() {
        ButtonUpload.setPickOnBounds(true);
        selecao.setOnDragOver(event -> handleDragOver(event));
        selecao.setOnDragDropped(event -> handleDrop(event));
    }

    @FXML
    public void handleUpload() {
        // Abrir o seletor de arquivos
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione a imagem do documento");
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
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

    @FXML
    // Tornando possível arrastar arquivos para a interface
    private void handleDragOver(DragEvent event){
        if (event.getDragboard().hasFiles()){
        event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    @FXML
    // Tornando possível que a interface receba os arquivos arrastados até ela
    private void handleDrop(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            File file = event.getDragboard().getFiles().get(0);
            statusLabel.setText("Arquivo carregado: " + file.getName());
            event.setDropCompleted(true);
    } else {
        event.setDropCompleted(false);
        statusLabel.setText("Nenhum arquivo selecionado");
        }
        event.setDropCompleted(true); 
        event.consume();
    }
}