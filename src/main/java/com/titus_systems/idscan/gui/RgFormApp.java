package com.titus_systems.idscan.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class RgFormApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Criação dos Labels e TextFields para cada campo de dados do RG
        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();

        Label dataNascimentoLabel = new Label("Data de Nascimento:");
        DatePicker dataNascimentoPicker = new DatePicker();

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
        DatePicker dataExpedicaoPicker = new DatePicker();

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
        preencherCampos(nomeField, dataNascimentoPicker, naturalidadeField, cpfField, tEleitorField);

        // Botões para salvar ou cancelar
        Button saveButton = new Button("Salvar");
        Button cancelButton = new Button("Cancelar");

        // Ação para salvar os dados
        saveButton.setOnAction(e -> {
            // Coleta dos dados dos campos
            HashMap<String, String> rgData = new HashMap<>();
            rgData.put("Nome", nomeField.getText());
            rgData.put("Data de Nascimento", (dataNascimentoPicker.getValue() != null) ? dataNascimentoPicker.getValue().toString() : "");
            rgData.put("Naturalidade", naturalidadeField.getText());
            rgData.put("CPF", cpfField.getText());
            rgData.put("Nacionalidade", nacionalidadeField.getText());
            rgData.put("Nome do Pai", nomePaiField.getText());
            rgData.put("Nome da Mãe", nomeMaeField.getText());
            rgData.put("Número do RG", rgNumberField.getText());
            rgData.put("Órgão Expedidor", orgaoExpedidorField.getText());
            rgData.put("Observação", observacaoField.getText());
            rgData.put("Estado", estadoField.getText());
            rgData.put("Data de Expedição", (dataExpedicaoPicker.getValue() != null) ? dataExpedicaoPicker.getValue().toString() : "");
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
        cancelButton.setOnAction(e -> limparCampos(nomeField, dataNascimentoPicker, naturalidadeField, cpfField, tEleitorField));

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
        gridPane.add(tEleitorLabel, 0, 4);
        gridPane.add(tEleitorField, 1, 4);
        gridPane.add(saveButton, 0, 5);
        gridPane.add(cancelButton, 1, 5);

        // Configuração da cena e exibição
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setTitle("Formulário de RG");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Função para preencher campos com dados
    private void preencherCampos(TextField nomeField, DatePicker dataNascimentoPicker, TextField naturalidadeField, TextField cpfField, TextField tEleitorField) {
        nomeField.setText("KARINA RODRIGUES RIBEIRO");
        dataNascimentoPicker.setValue(LocalDate.parse("17/11/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        naturalidadeField.setText("CIDADE DE NASCIMENTO");
        cpfField.setText("nnnnnnnnn/nn");
        tEleitorField.setText("nn.nnn");
    }

    // Função para limpar campos
    private void limparCampos(TextField nomeField, DatePicker dataNascimentoPicker, TextField naturalidadeField, TextField cpfField, TextField tEleitorField) {
        nomeField.clear();
        dataNascimentoPicker.setValue(null);
        naturalidadeField.clear();
        cpfField.clear();
        tEleitorField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
