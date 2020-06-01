package com.jdbc;

import com.jdbc.exception.ItemException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Controller {
    @FXML
    private TableView<Item> table;
    @FXML
    private TextArea info;
    @FXML
    private TextField add_title;
    @FXML
    private TextField add_price;
    @FXML
    private TextField delete_title;
    @FXML
    private TextField change_price;
    @FXML
    private TextField change_title;
    @FXML
    private TextField show_price;
    @FXML
    private TextField from;
    @FXML
    private TextField to;

    @FXML
    public void initialize() {
        add_title.setPromptText("Название товара");
        add_price.setPromptText("Цена");
        delete_title.setPromptText("Название товара");
        change_price.setPromptText("Новая цена");
        show_price.setPromptText("Название товара");
        change_title.setPromptText("Название товара");
        from.setPromptText("От");
        to.setPromptText("До");

        table.getColumns().clear();
        TableColumn<Item, Integer> firstColumn = new TableColumn<>("id");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Item, Integer> secondColumn = new TableColumn<>("prodid");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("prodid"));
        TableColumn<Item, String> thirdColumn = new TableColumn<>("title");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Item, Double> fourthColumn = new TableColumn<>("cost");
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        this.table.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn);
        //showAll();
    }

    @FXML
    private void showAll() {
        ObservableList<Item> observableList = FXCollections.observableList(Main.DbImplement.getList());
        table.setItems(observableList);
    }

    @FXML
    public void delete() {
        try {
            if (!delete_title.getText().equals("")) {
                Main.DbImplement.deleteItem(delete_title.getText());
                showAll();
            } else {
                info.setText("Некорректный ввод!");
            }
            delete_title.clear();
        } catch (ItemException er) {
            info.setText("Такого товара не сущетсвует");
        }
    }

    @FXML
    public void add() {
        try {
            if (!add_title.getText().equals("") && Double.parseDouble(add_price.getText()) > 0) {
                Main.DbImplement.addItem(add_title.getText(), Double.parseDouble(add_price.getText()));
                showAll();
            } else {
                info.setText("Некорректный ввод!");
            }
            add_title.clear();
            add_price.clear();
        } catch (ItemException er) {
            info.setText("Такой товар уже существует");
        } catch (NullPointerException | NumberFormatException e) {
            info.setText("Неверный формат цены!");
        }
    }

    @FXML
    public void price() {
        if (!show_price.getText().equals("")) {
            Item item = Main.DbImplement.getItem(show_price.getText());
            if (item != null) {
                info.clear();
                ObservableList<Item> observableList = FXCollections.observableArrayList();
                observableList.add(item);
                table.setItems(observableList);
            }
        } else {
            info.setText("Неверный формат цены!");
        }
        show_price.clear();
    }

    @FXML
    public void filter() {
        try {
            if ((Double.parseDouble(from.getText()) < Double.parseDouble(to.getText()))
                    && Double.parseDouble(from.getText()) >= 0 && Double.parseDouble(to.getText()) >= 0)
            {
                info.clear();
                double min = Double.parseDouble(from.getText());
                double max = Double.parseDouble(to.getText());
                ObservableList<Item> observableList = FXCollections.observableList(Main.DbImplement.getList(min, max));
                table.setItems(observableList);
            }
            else {
                info.setText("Неверный формат цены!");
            }
            from.clear();
            to.clear();
        } catch (NullPointerException | NumberFormatException e) {
            info.setText("Неверный формат цены!");
        }
    }

    @FXML
    public void change() {
        try {
            if (!change_title.getText().equals("") && Double.parseDouble(change_price.getText()) > 0) {
                Main.DbImplement.changePrice(change_title.getText(), Double.parseDouble(change_price.getText()));
                Item item = Main.DbImplement.getItem(change_title.getText());
                ObservableList<Item> observableList = FXCollections.observableArrayList();
                observableList.add(item);
                table.setItems(observableList);
            } else {
                info.setText("Некорректный ввод!");
            }
            change_price.clear();
            change_title.clear();
        } catch (NullPointerException | NumberFormatException e) {
            info.setText("Неверный формат цены!");
        }
    }

    @FXML
    public void off() {
        Platform.exit();
    }
}
