package com.titus_systems.idscan;


import com.titus_systems.idscan.database.DatabaseConnection;
import com.titus_systems.idscan.gui.Main;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando a aplicação...");
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.getConnection();
       
        // iniciar a interface gráfica
        Main.main(args);

    }
}
