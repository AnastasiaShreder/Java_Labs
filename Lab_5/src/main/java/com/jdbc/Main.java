package com.jdbc;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
    static Database_Implementation DbImplement;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res.fxml"));
        primaryStage.setTitle("Shop");
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    ;

    //Application.launch();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число товаров");
        scanner.useLocale(Locale.US);
        int num = scanner.nextInt();
        try {
            if (num <= 0) {
                System.out.println("Число товаров не может быть отрицательным");
            }
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод");
        }

        try (Database_Connection connection = Database_Connection.getInstance()) {
            DbImplement = new Database_Implementation(connection, num);
            Application.launch();
        } catch (SQLException ex) {
            System.out.println("Не удается установить соединение");
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println("Не удается закрыть соединение");
            e.printStackTrace();
        }
    }
}

