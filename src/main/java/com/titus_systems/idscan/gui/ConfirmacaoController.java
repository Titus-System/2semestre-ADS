package com.titus_systems.idscan.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;

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
        ArrayList<String> imagePaths = new ArrayList<>();
        imagePaths.add(mainController.getSelectedImageFile().getAbsolutePath());
        mainController.startImageProcessor(imagePaths);
        Stage currentStage = (Stage) confirmar.getScene().getWindow();
        currentStage.close();   
    }    

    @FXML
    private void cancelarImagem() {
    cancelar.getScene().getWindow().hide();
    }

}
