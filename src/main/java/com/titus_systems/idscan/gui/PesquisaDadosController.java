package com.titus_systems.idscan.gui;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PesquisaDadosController {
    
    @FXML
    private Button uploadButton;

    @FXML
    private Button dataSearchButton;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoNascimento;

    @FXML
    private TextField campoNaturalidade;

    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoPai;

    @FXML
    private TextField campoMae;

    @FXML
    private TextField campoRG;

    @FXML
    private TextField campoExpedidor;

    @FXML
    private TextField campoEstado;

    @FXML
    private TextField campoExpedicao;

    @FXML
    private TextField campoVia;

    @FXML
    private TextField campoUF;

    @FXML
    private TextField campoCNH;
    
    @FXML
    private TextField campoRH;

    @FXML
    private TextField campoNIS;

    @FXML
    private TextField campoCTPS;

    @FXML
    private TextField campoEleitor;
    
    @FXML
    private TextField campoMilitar;

    @FXML
    private TextField campoProfissional;
    
    @FXML
    private TextField campoCivil;

    @FXML
    private void voltarParaUpload(){
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent uploadRoot = loader.load();
        Stage stage = (Stage) ((Node) uploadButton).getScene().getWindow(); 
        stage.getScene().setRoot(uploadRoot); // Substitui o conteúdo atual pelo layout de upload

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private HashMap<String, String> dadosPesquisados = new HashMap<>();

    @FXML
    public HashMap<String, String> pesquisarDados(){
        dadosPesquisados.put("nome", campoNome.getText());
        dadosPesquisados.put("dataNascimento", campoNascimento.getText());
        dadosPesquisados.put("naturalidade", campoNaturalidade.getText());
        dadosPesquisados.put("cpf", campoCPF.getText());
        dadosPesquisados.put("nomePai", campoPai.getText());
        dadosPesquisados.put("nomeMae", campoMae.getText());
        dadosPesquisados.put("registroGeral", campoRG.getText());
        dadosPesquisados.put("orgaoExpedidor", campoExpedidor.getText());
        dadosPesquisados.put("estado", campoEstado.getText());
        dadosPesquisados.put("dataExpedicao", campoExpedicao.getText());
        dadosPesquisados.put("via", campoVia.getText());
        dadosPesquisados.put("uf", campoUF.getText());
        dadosPesquisados.put("cnh", campoCNH.getText());
        dadosPesquisados.put("fatorRh", campoRH.getText());
        dadosPesquisados.put("nisPisPasep", campoNIS.getText());
        dadosPesquisados.put("ctps", campoCTPS.getText());
        dadosPesquisados.put("tEleitor", campoEleitor.getText());
        dadosPesquisados.put("certMiliar", campoMilitar.getText());
        dadosPesquisados.put("identidadeProfissional", campoProfissional.getText());
        dadosPesquisados.put("registroCivil", campoCivil.getText());

        return dadosPesquisados;
        // dadosPesquisados.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
    }

    public HashMap<String, String> getDadosPesquisados() {
        return dadosPesquisados;
    }

    public void execute() { // Método que ocorre após clicar no botão "Buscar"
    System.out.println("Iniciando busca...");
    HashMap<String, String> criterios = pesquisarDados();

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resultadosPesquisa.fxml"));
        Parent resultadosRoot = loader.load();

        // Obtém o controlador da próxima tela
        ResultadosPesquisaController resultadosController = loader.getController();
        resultadosController.buscarERenderizarResultados(criterios);

        Stage resultadosStage = new Stage();
        resultadosStage.setTitle("Resultados da Pesquisa");
        resultadosStage.setScene(new Scene(resultadosRoot)); 
        resultadosStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

