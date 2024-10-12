package com.titus_systems.idscan.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConfirmaçãoController {
    
    @FXML
    private ImageView exibiçãoImagem;

    @FXML
    private Button cancelar;

    @FXML
    private Button confirmar;

    public void setImage(Image image) {
        exibiçãoImagem.setImage(image);
    }

}
