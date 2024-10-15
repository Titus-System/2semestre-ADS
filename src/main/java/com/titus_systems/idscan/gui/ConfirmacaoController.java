package com.titus_systems.idscan.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
try {
        mainController.startImageProcessor(mainController.getSelectedImageFile());
        Stage currentStage = (Stage) confirmar.getScene().getWindow();
        currentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sucesso.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Sucesso");
        stage.setScene(new Scene(root));
        stage.show();
    
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }    

    @FXML
    private void cancelarImagem() {
    cancelar.getScene().getWindow().hide();
    }

}
