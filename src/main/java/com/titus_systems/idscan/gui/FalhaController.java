package com.titus_systems.idscan.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FalhaController {

    @FXML
    private Label falhaDetalhes;

    @FXML
    private Button okBotaoFalha;

    @FXML
    private void fecharJanelaFalha() {
        Stage stage = (Stage) okBotaoFalha.getScene().getWindow();
        stage.close();
    }

    public void setDetalhes(String mensagemErro) {
        falhaDetalhes.setText(mensagemErro);
    }
}
