package com.titus_systems.idscan.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SucessoEdicaoController {

    @FXML
    private Label sucessoDetalhes;

    @FXML
    private Button okBotaoSucesso;

    @FXML
    private void fecharJanelaSucesso() {
        Stage stage = (Stage) okBotaoSucesso.getScene().getWindow();
        stage.close();
    }

    public void setDetalhes(String mensagemErro) {
        sucessoDetalhes.setText(mensagemErro);
    }
}