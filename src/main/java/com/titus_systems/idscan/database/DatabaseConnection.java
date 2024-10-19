package com.titus_systems.idscan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection { //Conexão com o banco de dados MySQL 
    private String URL = "jdbc:mysql://127.0.0.1:3306/";
    private String USER = "pedro"; // Digite seu usuário MySQL
    private String PASSWORD = "remendoneural"; // Digite sua senha do MySQL 
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

    public Connection getConnectionToDatabase(String dbName) {
        String fullUrl = URL + dbName;
        try {
            return DriverManager.getConnection(fullUrl, USER, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
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
 


    // Verificar a existência do banco de dados
    public boolean checkIfDatabaseExists(String dbName) throws SQLException {
        this.connection = this.getConnectionToDatabase(dbName);
        String query = "SHOW DATABASES LIKE '" + dbName + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            return rs.next();  // Retorna true se o banco de dados existir
        }
    }


    // Criar o banco de dados caso esse não exista
    public void createDatabase(String dbName) throws SQLException {
        String query = "CREATE DATABASE IF NOT EXISTS " + dbName;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Banco de dados criado: " + dbName);
        }
    }

    //Criar a tabela caso não exista
    public void createTableIfNotExists() throws SQLException {
        System.out.println("inicia criação da tabela");
        String createTableQuery = "CREATE TABLE IF NOT EXISTS RG (" + 
            "idRG int primary key auto_increment not null," +
            "naturalidade varchar(45)," +
            "nomePai varchar(100)," +
            "nomeMae varchar(100)," +
            "nome varchar(100)," +
            "cpf varchar(45)," +
            "cnh varchar(45)," + 
            "dataNascimento varchar(45)," +
            "fatorRh varchar(45)," +
            "orgaoExpedidor varchar(100)," +
            "estado varchar(45)," +
            "nisPisPasep varchar(100)," +
            "ctps varchar(45)," +
            "tEleitor varchar(45)," +
            "dataExpedicao varchar(45)," +
            "certMilitar varchar(45)," +
            "via varchar(45)," +
            "identidadeProfissional varchar(45)," +
            "uf varchar(45)," +
            "registroCivil varchar(45)," +
            "registroGeral varchar(45)" +
            ");";
        connection = getConnectionToDatabase("idScan");
        try(Statement stmt = connection.createStatement()){
            stmt.executeUpdate(createTableQuery);
            System.out.println("Tabela 'RG' criada ou já existe.");
        }
    }

    public void setURL(String dbName){
        this.URL = URL + dbName;
    }
}