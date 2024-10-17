package com.titus_systems.idscan.gui;

import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RgFormApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Criação dos Labels e TextFields para cada campo de dados do RG
        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();

        Label dataNascimentoLabel = new Label("Data de Nascimento:");
        TextField dataNascimentoPicker = new TextField();

        Label naturalidadeLabel = new Label("Naturalidade:");
        TextField naturalidadeField = new TextField();

        Label cpfLabel = new Label("CPF:");
        TextField cpfField = new TextField();

        Label nacionalidadeLabel = new Label("Nacionalidade:");
        TextField nacionalidadeField = new TextField();

        Label nomePaiLabel = new Label("Nome do Pai:");
        TextField nomePaiField = new TextField();

        Label nomeMaeLabel = new Label("Nome da Mãe:");
        TextField nomeMaeField = new TextField();

        Label rgNumberLabel = new Label("Número do RG:");
        TextField rgNumberField = new TextField();

        Label orgaoExpedidorLabel = new Label("Órgão Expedidor:");
        TextField orgaoExpedidorField = new TextField();

        Label observacaoLabel = new Label("Observação:");
        TextField observacaoField = new TextField();

        Label estadoLabel = new Label("Estado:");
        TextField estadoField = new TextField();

        Label dataExpedicaoLabel = new Label("Data de Expedição:");
        TextField dataExpedicaoPicker = new TextField();

        Label viaLabel = new Label("Via:");
        TextField viaField = new TextField();

        Label ufLabel = new Label("UF:");
        TextField ufField = new TextField();

        Label serieLabel = new Label("Série:");
        TextField serieField = new TextField();

        Label cnhLabel = new Label("ID CNH:");
        TextField cnhField = new TextField();

        Label fatorRhLabel = new Label("Fator Rh:");
        TextField fatorRhField = new TextField();

        Label nisPisPasepLabel = new Label("NIS/PIS/PASEP:");
        TextField nisPisPasepField = new TextField();

        Label ctpsLabel = new Label("CTPS:");
        TextField ctpsField = new TextField();

        Label tEleitorLabel = new Label("Título de Eleitor:");
        TextField tEleitorField = new TextField();

        Label certMilitarLabel = new Label("Certificado Militar:");
        TextField certMilitarField = new TextField();

        Label dniLabel = new Label("DNI:");
        TextField dniField = new TextField();

        Label identidadeProfissionalLabel = new Label("Identidade Profissional:");
        TextField identidadeProfissionalField = new TextField();

        Label cnsLabel = new Label("CNS:");
        TextField cnsField = new TextField();

        Label registroCivilLabel = new Label("Registro Civil:");
        TextField registroCivilField = new TextField();

        Label registroGeralLabel = new Label("Registro Geral:");
        TextField registroGeralField = new TextField();

        // Preenchimento dos campos com dados de exemplo
        preencherCampos(nomeField, dataNascimentoPicker, naturalidadeField, cpfField, 
        tEleitorField, nacionalidadeField, nomePaiField, nomeMaeField, rgNumberField, 
        orgaoExpedidorField, observacaoField, estadoField, dataExpedicaoPicker, viaField, ufField, 
        serieField, cnhField, fatorRhField, nisPisPasepField, ctpsField, certMilitarField, dniField, 
        identidadeProfissionalField, cnsField, registroCivilField, registroGeralField);

        // Botões para salvar ou cancelar
        Button saveButton = new Button("Salvar");
        Button cancelButton = new Button("Cancelar");

        // Ação para salvar os dados
        saveButton.setOnAction(e -> {
            // Coleta dos dados dos campos
            HashMap<String, String> rgData = new HashMap<>();
            rgData.put("Nome", nomeField.getText());
            rgData.put("Data de Nascimento", dataNascimentoPicker.getText());
            rgData.put("Naturalidade", naturalidadeField.getText());
            rgData.put("CPF", cpfField.getText());
            rgData.put("Nacionalidade", nacionalidadeField.getText());
            rgData.put("Nome do Pai", nomePaiField.getText());
            rgData.put("Nome da Mãe", nomeMaeField.getText());
            rgData.put("Número do RG", rgNumberField.getText());
            rgData.put("Órgão Expedidor", orgaoExpedidorField.getText());
            rgData.put("Observação", observacaoField.getText());
            rgData.put("Estado", estadoField.getText());
            rgData.put("Data de Expedição", dataExpedicaoPicker.getText());
            rgData.put("Via", viaField.getText());
            rgData.put("UF", ufField.getText());
            rgData.put("Série", serieField.getText());
            rgData.put("ID CNH", cnhField.getText());
            rgData.put("Fator Rh", fatorRhField.getText());
            rgData.put("NIS/PIS/PASEP", nisPisPasepField.getText());
            rgData.put("CTPS", ctpsField.getText());
            rgData.put("Título de Eleitor", tEleitorField.getText());
            rgData.put("Certificado Militar", certMilitarField.getText());
            rgData.put("DNI", dniField.getText());
            rgData.put("Identidade Profissional", identidadeProfissionalField.getText());
            rgData.put("CNS", cnsField.getText());
            rgData.put("Registro Civil", registroCivilField.getText());
            rgData.put("Registro Geral", registroGeralField.getText());

            // Exibir os dados ou processar conforme necessário
            System.out.println("Dados salvos: " + rgData);
        });

        // Ação para cancelar
        cancelButton.setOnAction(e -> {
            rgNumberField.clear();
            fatorRhField.clear();
            orgaoExpedidorField.clear();
            observacaoField.clear();
            estadoField.clear();
            nisPisPasepField.clear();
            ctpsField.clear();
            tEleitorField.clear();
            dataExpedicaoPicker.clear();
            certMilitarField.clear();
            dniField.clear();
            viaField.clear();
            identidadeProfissionalField.clear();
            cnsField.clear();
            serieField.clear();
            ufField.clear();
            registroCivilField.clear();
            registroGeralField.clear();
            nomeField.clear();
            dataNascimentoPicker.clear();
            naturalidadeField.clear();
            cpfField.clear();
            nacionalidadeField.clear();
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

        // Adicionando os elementos ao GridPane
        gridPane.add(nomeLabel, 0, 0);
        gridPane.add(nomeField, 1, 0);
        gridPane.add(dataNascimentoLabel, 0, 1);
        gridPane.add(dataNascimentoPicker, 1, 1);
        gridPane.add(naturalidadeLabel, 0, 2);
        gridPane.add(naturalidadeField, 1, 2);
        gridPane.add(cpfLabel, 0, 3);
        gridPane.add(cpfField, 1, 3);
        gridPane.add(nacionalidadeLabel, 0, 4);
        gridPane.add(nacionalidadeField, 1, 4);
        gridPane.add(nomePaiLabel, 0, 5);
        gridPane.add(nomePaiField, 1, 5);
        gridPane.add(nomeMaeLabel, 0, 6);
        gridPane.add(nomeMaeField, 1, 6);
        gridPane.add(rgNumberLabel, 0, 7);
        gridPane.add(rgNumberField, 1, 7);
        gridPane.add(orgaoExpedidorLabel, 0, 8);
        gridPane.add(orgaoExpedidorField, 1, 8);
        gridPane.add(observacaoLabel, 0, 9);
        gridPane.add(observacaoField, 1, 9);
        gridPane.add(estadoLabel, 0, 10);
        gridPane.add(estadoField, 1, 10);
        gridPane.add(dataExpedicaoLabel, 0, 11);
        gridPane.add(dataExpedicaoPicker, 1, 11);
        gridPane.add(viaLabel, 0, 12);
        gridPane.add(viaField, 1, 12);
        gridPane.add(ufLabel, 0, 13);
        gridPane.add(ufField, 1, 13);
        gridPane.add(serieLabel, 0, 14);
        gridPane.add(serieField, 1, 14);
        gridPane.add(cnhLabel, 0, 15);
        gridPane.add(cnhField, 1, 15);
        gridPane.add(fatorRhLabel, 0, 16);
        gridPane.add(fatorRhField, 1, 16);
        gridPane.add(nisPisPasepLabel, 0, 17);
        gridPane.add(nisPisPasepField, 1, 17);
        gridPane.add(ctpsLabel, 0, 18);
        gridPane.add(ctpsField, 1, 18);
        gridPane.add(tEleitorLabel, 0, 19);
        gridPane.add(tEleitorField, 1, 19);
        gridPane.add(certMilitarLabel, 0, 20);
        gridPane.add(certMilitarField, 1, 20);
        gridPane.add(dniLabel, 0, 21);
        gridPane.add(dniField, 1, 21);
        gridPane.add(identidadeProfissionalLabel, 0, 22);
        gridPane.add(identidadeProfissionalField, 1, 22);
        gridPane.add(cnsLabel, 0, 23);
        gridPane.add(cnsField, 1, 23);
        gridPane.add(registroCivilLabel, 0, 24);
        gridPane.add(registroCivilField, 1, 24);
        gridPane.add(registroGeralLabel, 0, 25);
        gridPane.add(registroGeralField, 1, 25);
        gridPane.add(saveButton, 0, 26);
        gridPane.add(cancelButton, 1, 26);

        // Configuração da cena e exibição
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setTitle("Formulário de RG");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Função para preencher campos com dados
    private void preencherCampos(
        TextField nomeField, TextField dataNascimentoPicker, TextField naturalidadeField, 
        TextField cpfField, TextField tEleitorField, TextField nacionalidadeField, 
        TextField nomePaiField, TextField nomeMaeField, TextField rgNumberField, 
        TextField orgaoExpedidorField, TextField observacaoField, TextField estadoField, 
        TextField dataExpedicaoPicker, TextField viaField, TextField ufField, 
        TextField serieField, TextField cnhField, TextField fatorRhField, 
        TextField nisPisPasepField, TextField ctpsField, TextField certMilitarField, 
        TextField dniField, TextField identidadeProfissionalField, TextField cnsField, 
        TextField registroCivilField, TextField registroGeralField) {
        nomeField.setText("Nome Completo");
        dataNascimentoPicker.setText("Data Nascimento");
        naturalidadeField.setText("CIDADE DE NASCIMENTO");
        cpfField.setText("nnnnnnnnn/nn");
        tEleitorField.setText("nn.nnn");
        nacionalidadeField.setText("Brasileira");
        nomePaiField.setText("Nome do Pai Exemplo");
        nomeMaeField.setText("Nome da Mãe Exemplo");
        rgNumberField.setText("123456789");
        orgaoExpedidorField.setText("SSP");
        observacaoField.setText("Sem observações");
        estadoField.setText("SP");
        dataExpedicaoPicker.setText("Data Expedicao");
        viaField.setText("1ª");
        ufField.setText("SP");
        serieField.setText("Série Exemplo");
        cnhField.setText("1234567890");
        fatorRhField.setText("O+");
        nisPisPasepField.setText("123456789");
        ctpsField.setText("CTPS Exemplo");
        certMilitarField.setText("Certificado Militar Exemplo");
        dniField.setText("DNI Exemplo");
        identidadeProfissionalField.setText("Identidade Profissional Exemplo");
        cnsField.setText("CNS Exemplo");
        registroCivilField.setText("Registro Civil Exemplo");
        registroGeralField.setText("RG Exemplo");  
    }

    public static void main(String[] args) {
        launch(args);
    }
}
