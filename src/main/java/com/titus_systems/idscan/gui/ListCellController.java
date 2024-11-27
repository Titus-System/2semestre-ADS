package com.titus_systems.idscan.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ListCellController {
    @FXML
    private Label nomeResultadoLabel;

    @FXML
    private Label cpfResultadoLabel;

    @FXML
    private Label rgResultadoLabel;

    public void setData(String nome, String cpf, String rg) {
        nomeResultadoLabel.setText(nome);
        cpfResultadoLabel.setText("CPF: " + cpf);
        rgResultadoLabel.setText("RG: " + rg);
    }
}