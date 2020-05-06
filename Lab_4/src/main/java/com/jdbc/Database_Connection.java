package com.jdbc;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.*;

public class Database_Connection implements AutoCloseable{

    private static Database_Connection instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "root";
    private String password = "tmnt";

    private Database_Connection() throws SQLException { //конструктор
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//Проверяем наличие JDBC драйвера для работы с БД
            this.connection = DriverManager.getConnection(url, username, password); //устанавливаем соединение с БД
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage()); //если вдруг драйвера не оказалось
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database_Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database_Connection();
        } else if (instance.getConnection().isClosed()) { //проверяем состояние соединения (был ли применен метод close())
            instance = new Database_Connection();
        }

        return instance;
    }

    @Override
    public void close() throws Exception {
        connection.close();               //try-with-resources
    }
}