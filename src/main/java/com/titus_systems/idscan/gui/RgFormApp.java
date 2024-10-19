package com.titus_systems.idscan.gui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import com.titus_systems.idscan.database.DatabaseConnection;
import com.titus_systems.idscan.database.RG;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RgFormApp extends Application {

    RG rgobject;

    public RgFormApp(RG rgobject){
        this.rgobject = rgobject;
    }

    @Override
    public void start(Stage primaryStage) {
        // Criação dos Labels e TextFields para cada campo de dados do RG
        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField(rgobject.getNome());

        Label dataNascimentoLabel = new Label("Data de Nascimento:");
        TextField dataNascimentoPicker = new TextField(rgobject.getdNasc());

        Label naturalidadeLabel = new Label("Naturalidade:");
        TextField naturalidadeField = new TextField(rgobject.getNaturalidade());

        Label cpfLabel = new Label("CPF:");
        TextField cpfField = new TextField(rgobject.getCpf());

        Label nomePaiLabel = new Label("Nome do Pai:");
        TextField nomePaiField = new TextField(rgobject.getPai());

        Label nomeMaeLabel = new Label("Nome da Mãe:");
        TextField nomeMaeField = new TextField(rgobject.getMae());

        Label rgNumberLabel = new Label("Número do RG:");
        TextField rgNumberField = new TextField(rgobject.getRg());

        Label orgaoExpedidorLabel = new Label("Órgão Expedidor:");
        TextField orgaoExpedidorField = new TextField(rgobject.getoExp());

        Label estadoLabel = new Label("Estado:");
        TextField estadoField = new TextField(rgobject.getEstado());

        Label dataExpedicaoLabel = new Label("Data de Expedição:");
        TextField dataExpedicaoPicker = new TextField(rgobject.getdExp());

        Label viaLabel = new Label("Via:");
        TextField viaField = new TextField(rgobject.getVia());

        Label ufLabel = new Label("UF:");
        TextField ufField = new TextField(rgobject.getUf());

        Label cnhLabel = new Label("CNH:");
        TextField cnhField = new TextField(rgobject.getCnh());

        Label fatorRhLabel = new Label("Fator Rh:");
        TextField fatorRhField = new TextField(rgobject.getFatorRh());

        Label nisPisPasepLabel = new Label("NIS/PIS/PASEP:");
        TextField nisPisPasepField = new TextField(rgobject.getNisPisPasep());

        Label ctpsLabel = new Label("CTPS:");
        TextField ctpsField = new TextField(rgobject.getCtps());

        Label tEleitorLabel = new Label("Título de Eleitor:");
        TextField tEleitorField = new TextField(rgobject.gettEleitor());

        Label certMilitarLabel = new Label("Certificado Militar:");
        TextField certMilitarField = new TextField(rgobject.getCertMiliar());

        Label identidadeProfissionalLabel = new Label("Identidade Profissional:");
        TextField identidadeProfissionalField = new TextField(rgobject.getIdProf());

        Label registroCivilLabel = new Label("Registro Civil:");
        TextField registroCivilField = new TextField(rgobject.getRegCivil());

        // Preenchimento dos campos com dados de exemplo
        //this.preencherCampos();

        // Botões para salvar ou cancelar
        Button saveButton = new Button("Salvar");
        Button cancelButton = new Button("Cancelar");

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
                rgobject.saveToDatabase(dbConnection);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        });

        // Ação para cancelar
        cancelButton.setOnAction(e -> {
            rgNumberField.clear();
            fatorRhField.clear();
            orgaoExpedidorField.clear();
            estadoField.clear();
            nisPisPasepField.clear();
            ctpsField.clear();
            tEleitorField.clear();
            dataExpedicaoPicker.clear();
            certMilitarField.clear();
            viaField.clear();
            identidadeProfissionalField.clear();
            ufField.clear();
            registroCivilField.clear();
            nomeField.clear();
            dataNascimentoPicker.clear();
            naturalidadeField.clear();
            cpfField.clear();
            nomePaiField.clear();
            nomeMaeField.clear();
            cnhField.clear();
        });

        // Layout do formulário usando GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

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

    public static void main(String[] args) {
        launch(args);
    }
}
