package com.titus_systems.idscan.gui;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import com.titus_systems.idscan.database.DatabaseConnection;
import com.titus_systems.idscan.database.RG;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RGConsulta extends Application {

    RG rgobject;

    public RGConsulta(RG rgobject){
        this.rgobject = rgobject;
    }

    @Override
    public void start(Stage primaryStage) {
        // Criação dos Labels e TextFields para cada campo de dados do RG
        Label nomeLabel = new Label("Nome:");
        nomeLabel.setStyle("-fx-text-fill: white;");
        TextField nomeField = new TextField(rgobject.getNome());

        Label dataNascimentoLabel = new Label("Data de Nascimento:");
        dataNascimentoLabel.setStyle("-fx-text-fill: white;");
        TextField dataNascimentoPicker = new TextField(rgobject.getdNasc());

        Label naturalidadeLabel = new Label("Naturalidade:");
        naturalidadeLabel.setStyle("-fx-text-fill: white;");
        TextField naturalidadeField = new TextField(rgobject.getNaturalidade());

        Label cpfLabel = new Label("CPF:");
        cpfLabel.setStyle("-fx-text-fill: white;");
        TextField cpfField = new TextField(rgobject.getCpf());

        Label nomePaiLabel = new Label("Nome do Pai:");
        nomePaiLabel.setStyle("-fx-text-fill: white;");
        TextField nomePaiField = new TextField(rgobject.getPai());

        Label nomeMaeLabel = new Label("Nome da Mãe:");
        nomeMaeLabel.setStyle("-fx-text-fill: white;");
        TextField nomeMaeField = new TextField(rgobject.getMae());

        Label rgNumberLabel = new Label("Número do RG:");
        rgNumberLabel.setStyle("-fx-text-fill: white;");
        TextField rgNumberField = new TextField(rgobject.getRg());

        Label orgaoExpedidorLabel = new Label("Órgão Expedidor:");
        orgaoExpedidorLabel.setStyle("-fx-text-fill: white;");
        TextField orgaoExpedidorField = new TextField(rgobject.getoExp());

        Label estadoLabel = new Label("Estado:");
        estadoLabel.setStyle("-fx-text-fill: white;");
        TextField estadoField = new TextField(rgobject.getEstado());

        Label dataExpedicaoLabel = new Label("Data de Expedição:");
        dataExpedicaoLabel.setStyle("-fx-text-fill: white;");
        TextField dataExpedicaoPicker = new TextField(rgobject.getdExp());

        Label viaLabel = new Label("Via:");
        viaLabel.setStyle("-fx-text-fill: white;");
        TextField viaField = new TextField(rgobject.getVia());

        Label ufLabel = new Label("UF:");
        ufLabel.setStyle("-fx-text-fill: white;");
        TextField ufField = new TextField(rgobject.getUf());

        Label cnhLabel = new Label("CNH:");
        cnhLabel.setStyle("-fx-text-fill: white;");
        TextField cnhField = new TextField(rgobject.getCnh());

        Label fatorRhLabel = new Label("Fator Rh:");
        fatorRhLabel.setStyle("-fx-text-fill: white;");
        TextField fatorRhField = new TextField(rgobject.getFatorRh());

        Label nisPisPasepLabel = new Label("NIS/PIS/PASEP:");
        nisPisPasepLabel.setStyle("-fx-text-fill: white;");
        TextField nisPisPasepField = new TextField(rgobject.getNisPisPasep());

        Label ctpsLabel = new Label("CTPS:");
        ctpsLabel.setStyle("-fx-text-fill: white;");
        TextField ctpsField = new TextField(rgobject.getCtps());

        Label tEleitorLabel = new Label("Título de Eleitor:");
        tEleitorLabel.setStyle("-fx-text-fill: white;");
        TextField tEleitorField = new TextField(rgobject.gettEleitor());

        Label certMilitarLabel = new Label("Certificado Militar:");
        certMilitarLabel.setStyle("-fx-text-fill: white;");
        TextField certMilitarField = new TextField(rgobject.getCertMiliar());

        Label identidadeProfissionalLabel = new Label("Identidade Profissional:");
        identidadeProfissionalLabel.setStyle("-fx-text-fill: white;");
        TextField identidadeProfissionalField = new TextField(rgobject.getIdProf());

        Label registroCivilLabel = new Label("Registro Civil:");
        registroCivilLabel.setStyle("-fx-text-fill: white;");
        TextField registroCivilField = new TextField(rgobject.getRegCivil());

        // Preenchimento dos campos com dados de exemplo
        //this.preencherCampos();

        // Botões para salvar ou cancelar
        Button saveButton = new Button("Salvar Alterações");
        saveButton.setStyle("-fx-background-color: #34D399; -fx-text-fill: white; -fx-font-weight: bold;");
        Button cancelButton = new Button("Excluir");
        cancelButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold;");


        // Ação para salvar os dados
        saveButton.setOnAction(e -> {
            // Coleta dos dados dos campos
            HashMap<String, String> rgData = new HashMap<>();
            rgData.put("nome", nomeField.getText());
            rgData.put("dataNascimento", dataNascimentoPicker.getText());
            rgData.put("naturalidade", naturalidadeField.getText());
            rgData.put("cpf", cpfField.getText());
            rgData.put("nomePai", nomePaiField.getText());
            rgData.put("nomeMae", nomeMaeField.getText());
            rgData.put("registroGeral", rgNumberField.getText());
            rgData.put("orgaoExpedidor", orgaoExpedidorField.getText());
            rgData.put("estado", estadoField.getText());
            rgData.put("dataExpedicao", dataExpedicaoPicker.getText());
            rgData.put("via", viaField.getText());
            rgData.put("uf", ufField.getText());
            rgData.put("cnh", cnhField.getText());
            rgData.put("fatorRh", fatorRhField.getText());
            rgData.put("nisPisPasep", nisPisPasepField.getText());
            rgData.put("ctps", ctpsField.getText());
            rgData.put("tEleitor", tEleitorField.getText());
            rgData.put("certMilitar", certMilitarField.getText());
            rgData.put("identidadeProfissional", identidadeProfissionalField.getText());
            rgData.put("registroCivil", registroCivilField.getText());

            // Exibir os dados ou processar conforme necessário
            System.out.println("Dados salvos: " + rgData);
            this.rgobject = new RG(rgData);
            Connection dbConnection = new DatabaseConnection().getConnectionToDatabase("idScan");
            try {
                System.out.println("numero do rg:"+ rgobject.getRg());
                rgobject.checkDuplicatesInDatabase(dbConnection);
                showSuccessMessage("Informações atualizadas com sucesso!");
                // rgobject.saveToDatabase(dbConnection);
            } catch (Exception e1) {
                e1.printStackTrace();
                showFailureMessage("Erro ao atualizar informações no banco de dados");
            }
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        });

        // Ação para excluir
        cancelButton.setOnAction(e -> {
            Connection con = new DatabaseConnection().getConnectionToDatabase("idScan");
            try {
                this.rgobject.removeFromDatabase(con, rgobject);
                showSuccessMessage("RG excluído com sucesso!");
            } catch (Exception e1) {
                e1.printStackTrace();
                showFailureMessage("Erro ao excluir RG do banco de dados");
            }
            cancelButton.getScene().getWindow().hide();
        });

        // Layout do formulário usando GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: #4D607C;");

        // Adicionando os elementos ao GridPane em duas colunas
        gridPane.add(nomeLabel, 0, 0);
        gridPane.add(nomeField, 1, 0);
        gridPane.add(dataNascimentoLabel, 3, 0);
        gridPane.add(dataNascimentoPicker, 4, 0);

        gridPane.add(naturalidadeLabel, 0, 1);
        gridPane.add(naturalidadeField, 1, 1);
        gridPane.add(cpfLabel, 3, 1);
        gridPane.add(cpfField, 4, 1);

        gridPane.add(nomePaiLabel, 0, 2);
        gridPane.add(nomePaiField, 1, 2);
        gridPane.add(nomeMaeLabel, 3, 2);
        gridPane.add(nomeMaeField, 4, 2);

        gridPane.add(rgNumberLabel, 0, 3);
        gridPane.add(rgNumberField, 1, 3);
        gridPane.add(orgaoExpedidorLabel, 3, 3);
        gridPane.add(orgaoExpedidorField, 4, 3);

        gridPane.add(estadoLabel, 0, 4);
        gridPane.add(estadoField, 1, 4);
        gridPane.add(dataExpedicaoLabel, 3, 4);
        gridPane.add(dataExpedicaoPicker, 4, 4);

        gridPane.add(viaLabel, 0, 5);
        gridPane.add(viaField, 1, 5);
        gridPane.add(ufLabel, 3, 5);
        gridPane.add(ufField, 4, 5);

        gridPane.add(cnhLabel, 0, 6);
        gridPane.add(cnhField, 1, 6);
        gridPane.add(fatorRhLabel, 3, 6);
        gridPane.add(fatorRhField, 4, 6);

        gridPane.add(nisPisPasepLabel, 0, 7);
        gridPane.add(nisPisPasepField, 1, 7);
        gridPane.add(ctpsLabel, 3, 7);
        gridPane.add(ctpsField, 4, 7);

        gridPane.add(tEleitorLabel, 0, 8);
        gridPane.add(tEleitorField, 1, 8);
        gridPane.add(certMilitarLabel, 3, 8);
        gridPane.add(certMilitarField, 4, 8);

        gridPane.add(identidadeProfissionalLabel, 0, 9);
        gridPane.add(identidadeProfissionalField, 1, 9);
        gridPane.add(registroCivilLabel, 3, 9);
        gridPane.add(registroCivilField, 4, 9);

        // Botões
        gridPane.add(saveButton, 0, 10);
        gridPane.add(cancelButton, 1, 10);

         // Configurar layout dos botões centralizados
        GridPane buttonPane = new GridPane();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setHgap(10);
        buttonPane.add(saveButton, 0, 0);
        buttonPane.add(cancelButton, 1, 0);

        gridPane.add(buttonPane, 0, 10, 5, 1); // Centralizar botões

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Configuração da cena e exibição
        Scene scene = new Scene(scrollPane, 900, 600);
        primaryStage.setTitle("Formulário de RG");
        primaryStage.setScene(scene);
        primaryStage.toFront();
        primaryStage.requestFocus();
        primaryStage.show();

    }

    public void showFailureMessage(String mensagemErro) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/falha.fxml"));
            Parent failureRoot = loader.load();
            FalhaController falhaController = loader.getController();
            falhaController.setDetalhes(mensagemErro);

            Stage failureStage = new Stage();
            failureStage.setScene(new Scene(failureRoot));
            failureStage.setTitle("Erro na operação de banco de dados");
            failureStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao mostrar a tela de falha: " + e.getMessage());
        }
    }

    public void showSuccessMessage(String successMessage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sucessoEdicao.fxml"));
            Parent failureRoot = loader.load();
            SucessoEdicaoController sucessoController = loader.getController();
            sucessoController.setDetalhes(successMessage);

            Stage failureStage = new Stage();
            failureStage.setScene(new Scene(failureRoot));
            failureStage.setTitle("Operação bem sucedida!");
            failureStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao mostrar a tela de falha: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}