package com.titus_systems.idscan.gui;

import java.io.File;

import com.titus_systems.idscan.ollama.ImageProcessor;

import io.github.ollama4j.utils.PromptBuilder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        String question = "Return the name of the id card holder and the informations present in the document as specified";

        if (file != null) {
            statusLabel.setText("Arquivo carregado: " + file.getName());
            System.out.println("Status Label: " + this.statusLabel);
            System.out.println("File Path: " + file.getAbsolutePath());

            // Código para processar a imagem de forma assíncrona -> Precisa do caminho da imagem selecionada
            ImageProcessor imgProcessor = new ImageProcessor("gemma2:2b");
            PromptBuilder prompt = new PromptBuilder();
            prompt.addLine("The following text was extracted with an OCR tool from an ID card from the Federal Republic of BRazil. It contains information such as name, date of birth, CPF, and RG.");
            prompt.addLine("Assume all this data is presented in brazilian portuguese and present it back in the same language, wwithout translation of any kind.");
            prompt.addLine("The name of the holder is identified by NOME");
            prompt.addLine("the date of bith is identified in the line bellow DATA NASCIMENTO");
            prompt.addLine("RG is identified as REGISTRO GERAL. It is a 10 digit number in the format nn.nnn.nnn-n");
            prompt.addLine("CPF is a 11 digit number composed of two parts separated by a slash. The first part has 9 digits and the second has 2 digits. in the format nnnnnnnnn/nn");
            prompt.addLine("The day the document was created is labeles DATA DE EXPEDIÇÃO");
            prompt.addLine("The city where the holder of the document was born is one line bellow REGISTRO CIVIL.");
            prompt.addLine("The data TITULO DE ELEITOR is a 15 digit number.");
            prompt.addLine("Do not be creative with the answer, provide only information contained in the text. Keep your answers as short as possible, providing only the needed information.");
            prompt.addSeparator();
            imgProcessor.asyncProcessWithTesseract(file.getAbsolutePath(), prompt, question, result -> {
                System.out.println("Resultado: " + result);
                Platform.runLater(() -> {
                    // Criar e mostrar uma nova janela
                    Stage newStage = new Stage();
                    VBox vbox = new VBox();
                    Label label = new Label("Resultado: " + result);
                    label.setWrapText(true); 
                    label.setMaxWidth(600);
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