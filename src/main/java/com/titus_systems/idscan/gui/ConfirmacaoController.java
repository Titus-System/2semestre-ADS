package com.titus_systems.idscan.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ConfirmacaoController{

    private MainController mainController; 
    
    @FXML
    private ImageView exibiçãoImagem;

    @FXML
    private Button cancelar;

    @FXML
    private Button confirmar;

    public void setImage(Image image) {
        exibiçãoImagem.setImage(image);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void confirmarImagem() {
    // Fecha a tela e execute o processamento no MainController
    confirmar.getScene().getWindow().hide();
    mainController.startImageProcessor(mainController.getSelectedImageFile());
    }    

    @FXML
    private void cancelarImagem() {
    cancelar.getScene().getWindow().hide();
    }

}
