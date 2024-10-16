package com.titus_systems.idscan;

import com.titus_systems.idscan.database.DatabaseConnection;
import com.titus_systems.idscan.gui.Main;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando a aplicação...");
        
        // Criar instância da classe DatabaseConnection
        DatabaseConnection dbConnection = new DatabaseConnection();
        
        // Estabelecer conexão com o MySQL
        Connection connection = dbConnection.getConnection();
        
        try {
            // Verificar se o banco de dados existe, caso contrário, criá-lo
            if (!dbConnection.checkIfDatabaseExists("idScan")) {
                dbConnection.createDatabase("idScan");
            }
            // Criar a tabela 'RG' se ela não existir
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
