package com.titus_systems.idscan.gui;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
            // Aqui você pode chamar o código para processar a imagem e interagir com o banco de dados
        } else {
            statusLabel.setText("Nenhum arquivo selecionado");
        }
    }
}
