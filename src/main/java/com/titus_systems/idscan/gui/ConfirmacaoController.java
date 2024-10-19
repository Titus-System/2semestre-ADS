package com.titus_systems.idscan.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ConfirmacaoController{

    private MainController mainController; 
    
    @FXML
    private ImageView exibicaoImagem;

    @FXML
    private Button cancelar;

    @FXML
    private Button confirmar;

    public void setImage(Image image) {
        exibicaoImagem.setImage(image);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void confirmarImagem() {
        mainController.startImageProcessor(mainController.getSelectedImageFile());
        Stage currentStage = (Stage) confirmar.getScene().getWindow();
        currentStage.close();   
    }    

    @FXML
    private void cancelarImagem() {
    cancelar.getScene().getWindow().hide();
    }

}
