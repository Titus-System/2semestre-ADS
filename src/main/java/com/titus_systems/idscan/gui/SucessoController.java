package com.titus_systems.idscan.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SucessoController {

    @FXML
    private Button okBotao;

    @FXML
    private void fecharJanela() {
        Stage stage = (Stage) okBotao.getScene().getWindow();
        stage.close();
    }
}
