package com.titus_systems.idscan.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.titus_systems.idscan.database.RG;
import com.titus_systems.idscan.ollama.IdPrompt;
import com.titus_systems.idscan.ollama.ImageProcessor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
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
    private Button buttonBuscar;

    @FXML
    private Button uploadButton;

    @FXML
    private Button dataSearchButton;

    @FXML
    private ProgressIndicator processamentoCarregando;

    @FXML
    public void initialize() {
        buttonBuscar.setPickOnBounds(true);
        selecao.setOnDragOver(event -> handleDragOver(event));
        selecao.setOnDragDropped(event -> handleDrop(event));
    }

    public File getSelectedImageFile() {
        return selectedImageFile;
    }

    @FXML
    private void consultaDados(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pesquisaDados.fxml"));
            Parent consultaRoot = loader.load();
            PesquisaDadosController pesquisa = new PesquisaDadosController();
            Stage stage = (Stage) ((Node) dataSearchButton).getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logosemfundoetexto_IDScan.png")));
            stage.getScene().setRoot(consultaRoot);
            dataSearchButton.setOnAction(e -> {
                HashMap<String,String> find =  pesquisa.pesquisarDados();
                find.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
            });
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela de consulta de dados: " + e.getMessage());
        }
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
            confirmationStage.setAlwaysOnTop(true);
            confirmationStage.setScene(new Scene(confirmationRoot));
            confirmationStage.setTitle("Confirme a imagem");
            confirmationStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logosemfundoetexto_IDScan.png")));
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
            failureStage.setAlwaysOnTop(true);
            failureStage.setScene(new Scene(failureRoot));
            failureStage.setTitle("Erro no processamento");
            failureStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logosemfundoetexto_IDScan.png")));
            failureStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao mostrar a tela de falha: " + e.getMessage());
        }
    }

    public Stage showSuccessMessage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sucesso.fxml"));
            Parent successRoot = loader.load();

            Stage successStage = new Stage();
            successStage.setScene(new Scene(successRoot));
            successStage.setTitle("Processamento em andamento");
            successStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logosemfundoetexto_IDScan.png")));
            successStage.show();
            return successStage;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void startImageProcessor(ArrayList<String> imagePaths){
        statusLabel.setText("Arquivo carregado: " + imagePaths.toString());
        System.out.println("Status Label: " + this.statusLabel);
        System.out.println("File Path: " + imagePaths.toString());

        for (String s: imagePaths){
            File file = new File(s);
            // Verifica se o arquivo é uma imagem
            if (!isImageFile(file)) {
                showFailureMessage("O arquivo selecionado não é um arquivo de imagem válido");
                return; // Interrompe o processamento se não for uma imagem
            }
    
            try {
                Image testImage = new Image(file.toURI().toString(), false);
                if (testImage.isError()) {
                    showFailureMessage("Imagem corrompida ou inválida");
                    return; // Interrompe o processamento ao detectar imagem inválida
                }
            } catch (Exception e) {
                return;
            }
        }

        Stage successStage = showSuccessMessage();

        // Código para processar a imagem de forma assíncrona -> Precisa do caminho da
        // imagem selecionada
        ImageProcessor imgProcessor = new ImageProcessor("gemma2:2b");
        IdPrompt prompt = new IdPrompt(true);

        imgProcessor.asyncProcessWithTesseract(imagePaths, prompt, result -> {
            System.out.println("Resultado: \n" + result);
            imgProcessor.setLastResponse(result);
            Platform.runLater(() -> {
                // codigo que executa após o processamento da imagem ser concluído.
                // Aqui deve ser colocada a lógica para exibição e confirmação das informações e
                // posterior salvamento no banco de dados

                // Oculta o indicador de carregamento após o processamento
                if (successStage != null){
                successStage.close();
                }
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
                    Stage newStage = new Stage();
                    newStage.setAlwaysOnTop(true);
                    newStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logosemfundoetexto_IDScan.png")));
                    rgForm.start(newStage);

                } catch (Exception e) {
                    e.printStackTrace();
                    showFailureMessage("Erro ao processar os dados extraídos.");
                }

            });
        });
    } 
}