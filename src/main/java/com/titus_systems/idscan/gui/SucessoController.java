package com.titus_systems.idscan.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SucessoController {

    @FXML
    private Button okBotaoSucesso;

    @FXML
    private void fecharJanelaSucesso() {
        Stage stage = (Stage) okBotaoSucesso.getScene().getWindow();
        stage.close();
    }
}
