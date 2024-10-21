package com.zkdas.oop.controller;

import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Order;
import com.zkdas.oop.model.OrderStatus;
import com.zkdas.oop.model.Store;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class OrdersTabController {
    @FXML
    private TextField IdField;
    @FXML
    private TextField CreatedField;
    @FXML
    private Label AmountLabel;
    @FXML
    private ChoiceBox<OrderStatus> StatusChoiceBox;
    @FXML
    private ListView<ItemForList> cart_listView;
    @FXML
    private TableView<Order> order_tableView;

    public void initialize() {
        StatusChoiceBox.getItems().addAll(OrderStatus.values());

        // Создаем колонки с фиксированной шириной
        TableColumn<Order, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        idColumn.setMinWidth(50);
        idColumn.setMaxWidth(50);

        TableColumn<Order, Date> createdColumn = new TableColumn<>("Создано");
        createdColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDate()));
        createdColumn.setMinWidth(200);
        createdColumn.setMaxWidth(200);

        TableColumn<Order, OrderStatus> statusColumn = new TableColumn<>("Статус");
        statusColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getOrderStatus()));
        createdColumn.setMinWidth(100);
        statusColumn.setMaxWidth(100);

        TableColumn<Order, String> customerColumn = new TableColumn<>("Полное имя клиента");
        customerColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        customerColumn.setMinWidth(200);
        customerColumn.setMaxWidth(200);

        // Добавляем колонки в TableView
        order_tableView.getColumns().addAll(idColumn, createdColumn, statusColumn, customerColumn);

        // задаю список заказов
        Store store = new Store();
        order_tableView.setItems(store.getOrders());

    }
}
