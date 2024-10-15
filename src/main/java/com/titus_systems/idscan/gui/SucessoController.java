package com.titus_systems.idscan.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SucessoController {

    @FXML
    private Button okBotão;

    @FXML
    private void fecharJanela() {
        Stage stage = (Stage) okBotão.getScene().getWindow();
        stage.close();
    }
}
