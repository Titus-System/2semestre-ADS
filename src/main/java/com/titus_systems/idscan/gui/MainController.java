package com.titus_systems.idscan.gui;

import java.io.File;
import java.util.HashMap;

import com.titus_systems.idscan.database.RG;
import com.titus_systems.idscan.ollama.IdPrompt;
import com.titus_systems.idscan.ollama.ImageProcessor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
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
            this.startImageProcessor(file);

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
            this.startImageProcessor(file);
        } else {
            event.setDropCompleted(false);
            statusLabel.setText("Nenhum arquivo selecionado");
            }
            event.setDropCompleted(true); 
            event.consume();
        }

    private void startImageProcessor(File file){
        statusLabel.setText("Arquivo carregado: " + file.getName());
        System.out.println("Status Label: " + this.statusLabel);
        System.out.println("File Path: " + file.getAbsolutePath());

        // Cria um indicador de carregamento e adiciona à interface enquanto o processamento está em andamento
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(-1.0); // Modo indeterminado

        // Exibe o indicador de carregamento em uma nova janela (ou em um painel existente, conforme necessário)
        Stage loadingStage = new Stage();
        VBox loadingBox = new VBox(progressIndicator);
        loadingBox.setStyle("-fx-alignment: center; -fx-padding: 20;");
        Scene loadingScene = new Scene(loadingBox, 200, 100);
        loadingStage.setScene(loadingScene);
        loadingStage.setTitle("Processando...");
        loadingStage.show();

        // Código para processar a imagem de forma assíncrona -> Precisa do caminho da imagem selecionada
        ImageProcessor imgProcessor = new ImageProcessor("gemma2:2b");
        IdPrompt prompt = new IdPrompt(true);

        imgProcessor.asyncProcessWithTesseract(file.getAbsolutePath(), prompt, result -> {
            System.out.println("Resultado: \n" + result);
            imgProcessor.setLastResponse(result);

            Platform.runLater(() -> {
                //codigo que executa após o processamento da imagem ser concluído.
                //Aqui deve ser colocada a lógica para exibição e confirmação das informações e posterior salvamento no banco de dados
                
                // Oculta o indicador de carregamento após o processamento
                loadingStage.close();
                
                //Criação do objeto RG para armazenamento temporário das informações extraídas
                HashMap<String,String> mappedResponse = imgProcessor.convertResponseToHashMap();
                RG rgObject = new RG(mappedResponse);

                // Criar e mostrar uma nova janela
                Stage newStage = new Stage();
                VBox vbox = new VBox();
                Label label = new Label("Resultado: \n" + result);
                label.setWrapText(true); 
                label.setMaxWidth(600);
                vbox.getChildren().add(label);


                Scene scene = new Scene(vbox, 700, 900);
                newStage.setScene(scene);
                newStage.setTitle("Resultado do Processamento");
                newStage.show();
            });
        });
    }
}