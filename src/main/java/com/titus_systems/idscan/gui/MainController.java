package com.titus_systems.idscan.gui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.titus_systems.idscan.database.RG;
import com.titus_systems.idscan.ollama.IdPrompt;
import com.titus_systems.idscan.ollama.ImageProcessor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {

    private File selectedImageFile;

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

    public File getSelectedImageFile() {
        return selectedImageFile;
    }

    @FXML
    public void handleUpload() {
        // Abrir o seletor de arquivos
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione a imagem do documento");
        selectedImageFile = fileChooser.showOpenDialog(new Stage());
        System.out.println("Arquivo selecionado: " + selectedImageFile);

        if (selectedImageFile != null) {
            openConfirmationDialog();
            // this.startImageProcessor(selectedImageFile);

        } else {
            statusLabel.setText("Nenhum arquivo selecionado");
        }
    }

    private void openConfirmationDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/confirmacao.fxml"));
            Parent confirmationRoot = loader.load();

            ConfirmacaoController confirmacaoController = loader.getController();
            Image image = new Image(selectedImageFile.toURI().toString());
            confirmacaoController.setImage(image);
            confirmacaoController.setMainController(this);

            // Exibe a tela de confirmação
            Stage confirmationStage = new Stage();
            confirmationStage.setScene(new Scene(confirmationRoot));
            confirmationStage.setTitle("Confirme a imagem");
            confirmationStage.showAndWait(); // Espera o usuário confirmar

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    // Tornando possível arrastar arquivos para a interface
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    @FXML
    // Tornando possível que a interface receba os arquivos arrastados até ela
    private void handleDrop(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            File file = event.getDragboard().getFiles().get(0);
            selectedImageFile = file;
            statusLabel.setText("Arquivo carregado: " + file.getName());
            event.setDropCompleted(true);
            openConfirmationDialog();
        } else {
            event.setDropCompleted(false);
            statusLabel.setText("Nenhum arquivo selecionado");
        }
        event.consume();
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png");
    }

    public void showFailureMessage(String mensagemErro) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/falha.fxml"));
            Parent failureRoot = loader.load();
            FalhaController falhaController = loader.getController();
            falhaController.setDetalhes(mensagemErro);

            Stage failureStage = new Stage();
            failureStage.setScene(new Scene(failureRoot));
            failureStage.setTitle("Erro no processamento");
            failureStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao mostrar a tela de falha: " + e.getMessage());
        }
    }

    public void showSuccessMessage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sucesso.fxml"));
            Parent successRoot = loader.load();

            Stage successStage = new Stage();
            successStage.setScene(new Scene(successRoot));
            successStage.setTitle("Processamento concluído");
            successStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void startImageProcessor(File file) throws IOException {
        statusLabel.setText("Arquivo carregado: " + file.getName());
        System.out.println("Status Label: " + this.statusLabel);
        System.out.println("File Path: " + file.getAbsolutePath());

        // Verifica se o arquivo é uma imagem
        if (!isImageFile(file)) {
            showFailureMessage("O arquivo selecionado não é um arquivo de imagem válido");
            return; // Interrompe o processamento se não for uma imagem
        }

        // Cria um indicador de carregamento e adiciona à interface enquanto o
        // processamento está em andamento
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(-1.0); // Modo indeterminado

        // Exibe o indicador de carregamento em uma nova janela (ou em um painel
        // existente, conforme necessário)
        Stage loadingStage = new Stage();
        VBox loadingBox = new VBox(progressIndicator);
        loadingBox.setStyle("-fx-alignment: center; -fx-padding: 20;");
        Scene loadingScene = new Scene(loadingBox, 200, 100);
        loadingStage.setScene(loadingScene);
        loadingStage.setTitle("Processando...");
        loadingStage.show();

        try {
            Image testImage = new Image(file.toURI().toString(), false);
            if (testImage.isError()) {
                loadingStage.close();  // Fecha o indicador de carregamento
                showFailureMessage("Imagem corrompida ou inválida");
                return; // Interrompe o processamento ao detectar imagem inválida
            }
        } catch (Exception e) {
            loadingStage.close();
            return;
        }

        // Código para processar a imagem de forma assíncrona -> Precisa do caminho da
        // imagem selecionada
        ImageProcessor imgProcessor = new ImageProcessor("gemma2:2b");
        IdPrompt prompt = new IdPrompt(true);
        imgProcessor.asyncProcessWithTesseract(file.getAbsolutePath(), prompt, result -> {
            System.out.println("Resultado: \n" + result);
            imgProcessor.setLastResponse(result);
            Platform.runLater(() -> {
                // codigo que executa após o processamento da imagem ser concluído.
                // Aqui deve ser colocada a lógica para exibição e confirmação das informações e
                // posterior salvamento no banco de dados

                // Oculta o indicador de carregamento após o processamento
                loadingStage.close();

                try {
                    // Criação do objeto RG para armazenamento temporário das informações extraídas
                    HashMap<String, String> mappedResponse = imgProcessor.convertResponseToHashMap(result);
                    if (mappedResponse == null || mappedResponse.isEmpty()) {
                        showFailureMessage("Nenhum texto foi identificado na imagem.");
                        return;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map.Entry<String, String> entry : mappedResponse.entrySet()) {
                        stringBuilder.append("Chave: " + entry.getKey() + " | Valor: " + entry.getValue() + "\n");
                    }
                    if (mappedResponse.size() < 1) {
                        stringBuilder.append("hashmap vazio");
                    }

                    RG rgObject = new RG(mappedResponse);
                    RgFormApp rgForm = new RgFormApp(rgObject);
                    rgForm.start(new Stage());
                    showSuccessMessage();

                } catch (Exception e) {
                    e.printStackTrace();
                    showFailureMessage("Erro ao processar os dados extraídos.");
                }

            });
        });
    } 
}