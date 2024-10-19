package com.titus_systems.idscan;

import java.sql.SQLException;

import com.titus_systems.idscan.database.DatabaseConnection;
import com.titus_systems.idscan.gui.Main;



public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando a aplicação...");
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        
        try {
            if (!dbConnection.checkIfDatabaseExists("idScan")) {
                dbConnection.createDatabase("idScan");
            }
            dbConnection.createTableIfNotExists();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar a conexão ao final
            dbConnection.closeConnection();
        }
        
        // Iniciar a interface gráfica
        Main.main(args);
    }
}
