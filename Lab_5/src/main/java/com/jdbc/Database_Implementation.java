package com.jdbc;

import com.jdbc.exception.ItemException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database_Implementation {
    private final Database_Connection database;
    private int numOfProducts = 0; //prodid

    public Database_Implementation(Database_Connection data_base, int countOfProducts) {//конструктор класса
        this.database = data_base;
        try (Statement state = database.getConnection().createStatement()) { //получаем соединение с БД создаем statement
            try {
                state.executeUpdate(cleanTable("shop.goods")); //конструктор попытается очитить таблицу перед началом работы
            } catch (SQLSyntaxErrorException ex) {
                state.executeUpdate(createTable("shop.goods")); //если такой таблицы нет - создадим ее
            }
            for (int i = 1; i < countOfProducts + 1; ++i) {
                addItem("товар" + i, 1 + (int) (Math.random() * 100)); //после очистки или созданяи таблицы заполняем товарами,
            }                                                          //  число которых пользователь предоставил
        } catch (SQLException | ItemException ex) {
            ex.printStackTrace();   //выводит в System.err ошибки и исключения
        }
    }

    public void addItem(String title, double price) throws ItemException {
        //проверка, есть ли такой же товар
        String select_query = "SELECT title FROM shop.goods WHERE title=?";
        try (PreparedStatement state = database.getConnection().prepareStatement(select_query)) { //устанавливаем соединение и передаем запрос в метод для подстановки значений
            state.setString(1, title);   // подставляем вместо ? переданный пользователем title
            ResultSet results = state.executeQuery(); //результаты запроса помещаются в сущность ResultSet - набор результатов
            if (results.next()) {     //возвоащает true, если запрос выдал несколько строк (в данном случае если в таблице есть строка с таким же title)
                throw new ItemException() ;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String insert_query = "INSERT INTO shop.goods (prodid, title, cost) VALUES (?, ?, ?)";
        try (PreparedStatement statement = database.getConnection().prepareStatement(insert_query)) {
            numOfProducts++;
            statement.setInt(1, numOfProducts);
            statement.setString(2, title);
            statement.setDouble(3, price);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteItem(String title) throws ItemException{
        String select_query = "SELECT title FROM shop.goods WHERE title=?";
        try (PreparedStatement state = database.getConnection().prepareStatement(select_query)) {
            state.setString(1, title);   // подставляем вместо ? переданный пользователем title
            ResultSet results = state.executeQuery();
            if (!results.next()){
                throw new ItemException();
            }
        }
     catch (SQLException ex) {
        ex.printStackTrace();
    }
        String query = "DELETE FROM shop.goods WHERE title=?";
        try (PreparedStatement state = database.getConnection().prepareStatement(query)) {
            state.setString(1, title);

            state.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showItems() {
        String query = "SELECT * FROM shop.goods";
        try (Statement statement = database.getConnection().createStatement()) {
            ResultSet output = statement.executeQuery(query);
            while (output.next()) {
                String row = output.getInt("id") + " | " + output.getInt("prodid") + " | " + output.getString("title") + " | " + output.getDouble("cost");
                System.out.println(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showPrice(String title) {
        String query = "SELECT cost FROM shop.goods WHERE title=?";
        try (PreparedStatement state = database.getConnection().prepareStatement(query)) {
            state.setString(1, title);
            ResultSet result = state.executeQuery();
            if (result.next()){
                int price = result.getInt("cost");
                System.out.println(price);
            }
         else
            {
            System.out.println("Товар отсутствует");
        }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changePrice(String title, double newCost) {
        String query = "UPDATE shop.goods SET cost=? WHERE title=?";
        try (PreparedStatement state = database.getConnection().prepareStatement(query)) {
            state.setDouble(1, newCost);
            state.setString(2, title);
            state.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void filter(double fromPrice, double toPrice){
        String query = "SELECT * FROM shop.goods WHERE cost BETWEEN ? AND ?";
        try (PreparedStatement state = database.getConnection().prepareStatement(query)){
            state.setDouble(1, fromPrice);
            state.setDouble(2, toPrice);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                String row = result.getInt("id") + " | " + result.getInt("prodid") + " | " + result.getString("title") + " | " + result.getDouble("cost");
                System.out.println(row);
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String createTable(String name){
        String query = "CREATE TABLE " + name +
                       "(id INTEGER not NULL AUTO_INCREMENT, " +
                       "prodid INTEGER not NULL, " +
                       "title VARCHAR(50) not NULL, " +
                       "cost DOUBLE not NULL, " +
                       "PRIMARY KEY (id))";

        return query;
    }

    private String cleanTable(String name){
        String query = "TRUNCATE TABLE " + name;
        return query;
    }

    public List<Item> getList() {
        List<Item> items = new ArrayList<>();
        String query = "select * from shop.goods";
        try(PreparedStatement preparedStatement = database.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int prodid = resultSet.getInt(2);
                String title = resultSet.getString(3);
                double cost = resultSet.getDouble(4);
                items.add(new Item(id, prodid, title, cost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public  List<Item> getList(double min, double max) {
        List<Item> items = new ArrayList<>();
        String query = "select * from shop.goods where cost between ? AND ?";
        try(PreparedStatement preparedStatement = database.getConnection().prepareStatement(query)) {
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int prodid = resultSet.getInt(2);
                String title = resultSet.getString(3);
                double cost = resultSet.getDouble(4);
                items.add(new Item(id, prodid, title, cost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getItem (String title) {
        String query = "select * from shop.goods where title=?";
        try(PreparedStatement preparedStatement = database.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                int prodid = resultSet.getInt(2);
                double cost = resultSet.getDouble(4);
                return new Item(id, prodid, title, cost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
