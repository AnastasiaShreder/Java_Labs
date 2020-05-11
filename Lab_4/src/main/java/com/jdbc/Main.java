package com.jdbc;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.sql.*;

public class Main {
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
            Database_Implementation database = new Database_Implementation(connection, num);
            boolean execution = true;
            while (execution) {
               // scanner.next()
                     System.out.println("Введите команду");
                     String command = scanner.next();
                     switch (command) {
                         case "/add": {
                             try {
                                 String title = scanner.next();
                                 double price = scanner.nextDouble();
                                 if (price <= 0) {
                                     System.out.println("Введите положительное число");
                                     break;
                                 }
                                 database.addItem(title, price);
                                 break;
                             } catch (InputMismatchException e) {
                                 System.out.println("Некорректный ввод");
                             }
                         }
                         case "/delete": {
                             String title = scanner.next();
                             database.deleteItem(title);
                             break;
                         }
                         case "/show_all": {
                             database.showItems();
                             break;
                         }
                         case "/price": {
                             String title = scanner.next();
                             database.showPrice(title);
                             break;
                         }
                         case "/change_price": {
                             String title = scanner.next();
                             double newPrice = scanner.nextDouble();
                             if (newPrice <= 0) {
                                 System.out.println("Введите положительное число");
                                 break;
                             }
                             database.changePrice(title, newPrice);
                             break;
                         }
                         case "/filter_by_price": {
                             double fromPrice = scanner.nextDouble();
                             double toPrice = scanner.nextDouble();
                             if (fromPrice > toPrice) {
                                 System.out.println("Неверно заданный диапазон. Первое число должно быть меньше второго");
                                 break;
                             }
                             if ((fromPrice < 0) || (toPrice < 0)) {
                                 System.out.println("Цены не могут быть отрицательными");
                                 break;
                             }
                             database.filter(fromPrice, toPrice);
                             break;
                         }
                         case "/off": {
                             execution = false;
                             System.out.println("Завершение работы программы...");
                             break;
                         }
                         default: {
                             System.out.println("Неверная команда!");
                             scanner.nextLine();
                             break;
                         }
                     }
            }
        }
        catch(SQLException ex) {
            System.out.println("Не удается установить соединение");
            ex.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("Не удается закрыть соединение");
            e.printStackTrace();
        }

    }
}

