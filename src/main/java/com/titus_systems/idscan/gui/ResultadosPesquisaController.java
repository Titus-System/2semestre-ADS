package com.titus_systems.idscan.gui;

import com.titus_systems.idscan.database.DatabaseConnection;
import com.titus_systems.idscan.database.RG;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ResultadosPesquisaController {

    @FXML
    private ListView<RG> resultadosListView;

    @FXML
    private Label semResultadosLabel;

    // Método para exibir os resultados no ListView
    public void exibirResultados(List<RG> usuarios) {
        ObservableList<RG> resultados = FXCollections.observableArrayList(usuarios);
        resultadosListView.setItems(resultados);  

        resultadosListView.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(RG item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {

                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listCell.fxml"));
                        Region graphic = loader.load();
                        ListCellController cellController = loader.getController();
                        cellController.setData(item.getNome(), item.getCpf(), item.getRg());
                        setGraphic(graphic);

                    } catch (IOException e) {
                        e.printStackTrace();
                        setText("Erro ao carregar célula");
                    }
                }
            }
        });
    }


        // Método para buscar os registros com base nos critérios do usuário e carregar no ListView
        public void buscarERenderizarResultados(HashMap<String, String> criterios) {
            try (Connection con = new DatabaseConnection().getConnection()) {
            con.setCatalog("idscan");
            RG rg = new RG();
            List<RG> usuarios = rg.pullFromDataBase(con, criterios);
            if (!usuarios.isEmpty()) {
            exibirResultados(usuarios); // Renderiza os resultados encontrados
            } else {
            resultadosListView.setItems(FXCollections.observableArrayList());
            semResultadosLabel.setVisible(true);
            }
        } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erro ao buscar os resultados: " + e.getMessage());
        }
    }

    @FXML
    public void handleItemSelection(MouseEvent event) {
        System.out.println("Item selecionado!");
        // Pega o item selecionado
        RG selectedRG = resultadosListView.getSelectionModel().getSelectedItem();

        if (selectedRG != null) {
            // Abre a nova tela de formulário com os dados do RG selecionado
            try {
                Stage currentStage = (Stage) resultadosListView.getScene().getWindow();
                currentStage.close();
                RGConsulta rgFormApp = new RGConsulta(selectedRG);
                Stage newStage = new Stage();
                rgFormApp.start(newStage);  
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
