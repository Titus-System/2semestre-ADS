package com.titus_systems.idscan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String URL = "jdbc:mysql://localhost:3306/nome_do_banco";
    private String USER = "meu_usuario";
    private String PASSWORD = "minha_senha";
    private Connection connection;

    public DatabaseConnection(String url, String user, String password){
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

    public DatabaseConnection(){

    }

    public Connection getConnection() {
        if (connection == null){
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão bem sucedida!");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void  closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
