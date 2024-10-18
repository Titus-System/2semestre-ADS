package com.titus_systems.idscan.gui;

import java.util.HashMap;

import com.titus_systems.idscan.database.RG;

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

        Label nacionalidadeLabel = new Label("Nacionalidade:");
        TextField nacionalidadeField = new TextField();

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

        Label serieLabel = new Label("Série:");
        TextField serieField = new TextField(rgobject.getSerie());

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

        Label dniLabel = new Label("DNI:");
        TextField dniField = new TextField(rgobject.getDni());

        Label identidadeProfissionalLabel = new Label("Identidade Profissional:");
        TextField identidadeProfissionalField = new TextField(rgobject.getIdProf());

        Label cnsLabel = new Label("CNS:");
        TextField cnsField = new TextField(rgobject.getCns());

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
            rgData.put("Nome", nomeField.getText());
            rgData.put("Data de Nascimento", dataNascimentoPicker.getText());
            rgData.put("Naturalidade", naturalidadeField.getText());
            rgData.put("CPF", cpfField.getText());
            rgData.put("Nacionalidade", nacionalidadeField.getText());
            rgData.put("Nome do Pai", nomePaiField.getText());
            rgData.put("Nome da Mãe", nomeMaeField.getText());
            rgData.put("Número do RG", rgNumberField.getText());
            rgData.put("Órgão Expedidor", orgaoExpedidorField.getText());
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

            // Exibir os dados ou processar conforme necessário
            System.out.println("Dados salvos: " + rgData);
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
            dniField.clear();
            viaField.clear();
            identidadeProfissionalField.clear();
            cnsField.clear();
            serieField.clear();
            ufField.clear();
            registroCivilField.clear();
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
        gridPane.add(saveButton, 0, 26);
        gridPane.add(cancelButton, 1, 26);

        // Configuração da cena e exibição
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setTitle("Formulário de RG");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

     // Função para preencher campos com dados
     private void preencherCampos() {
        String nomeField = rgobject.getNome();
        String dataNascimentoPicker = rgobject.getdNasc();
        String naturalidadeField = rgobject.getNaturalidade();
        String cpfField = rgobject.getCpf();
        String tEleitorField = rgobject.gettEleitor();
        String nacionalidadeField = rgobject.getNacionalidade();
        String nomePaiField = rgobject.getPai();
        String nomeMaeField = rgobject.getMae();
        String rgNumberField = rgobject.getRg();
        String orgaoExpedidorField = rgobject.getoExp();
        String observacaoField = rgobject.getObservacao();
        String estadoField = rgobject.getEstado();
        String dataExpedicaoPicker = rgobject.getdExp();
        String viaField = rgobject.getVia();
        String ufField = rgobject.getUf();
        String serieField = rgobject.getSerie();
        String cnhField = rgobject.getCnh();
        String fatorRhField = rgobject.getFatorRh();
        String nisPisPasepField = rgobject.getNisPisPasep();
        String ctpsField = rgobject.getCtps();
        String certMilitarField = rgobject.getCertMiliar();
        String dniField = rgobject.getDni();
        String identidadeProfissionalField = rgobject.getIdProf();
        String cnsField = rgobject.getCns();
        String registroCivilField = rgobject.getRegCivil();
        String registroGeralField = rgobject.getRegGeral();  
    }

    public static void main(String[] args) {
        launch(args);
    }
}
