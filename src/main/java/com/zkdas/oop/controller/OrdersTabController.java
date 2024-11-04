package com.zkdas.oop.controller;

import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Order;
import com.zkdas.oop.model.OrderStatus;
import com.zkdas.oop.model.Store;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Date;

public class OrdersTabController {
    @FXML
    private TextField IdField;
    @FXML
    private TextField CreatedField;
    @FXML
    protected Label AmountLabel;
    @FXML
    private ChoiceBox<OrderStatus> StatusChoiceBox;
    @FXML
    protected ListView<ItemForList> cart_listView;
    @FXML
    private TableView<Order> order_tableView;
    @FXML
    private GridPane addressPane;
    private AddressController addressController;

    /**
     * Инициализация таблицы
     */
    protected void tableInitialize() {
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

        // Добавляем обработчик выбора строки
        order_tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                orderTableViewAction(newValue);
            }
        });
    }

    public void initialize() throws IOException {
        // установка выборов в ChoiceBox
        StatusChoiceBox.getItems().addAll(OrderStatus.values());

        // Инициализация таблицы
        tableInitialize();

        // загрузка виджета
        // указания пути к fxml файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/AddressControl.fxml"));
        Parent subview = loader.load();// загрузка данных из файла
        addressController = loader.getController(); // присвоение контролера
        // вставка subview
        addressPane.getChildren().add(subview);
    }

    /**
     * Обработчик выбора элемента в таблице
     * @param selectedOrder выбранный элемент
     */
    protected void orderTableViewAction(Order selectedOrder) {
        addressController.SetAddress(selectedOrder.getAddress()); // установка адреса
        IdField.setText(String.valueOf(selectedOrder.getId())); // установка id
        CreatedField.setText(String.valueOf(selectedOrder.getDate())); // установка даты
        StatusChoiceBox.getSelectionModel().select(selectedOrder.getOrderStatus()); // установка статуса заказа

        // установка списка заказов
        // cart_listView.getItems().clear(); // отчистка старого списка
        // cart_listView.getItems().addAll(selectedOrder.getItems());
        cart_listView.setItems(selectedOrder.getItems()); // почему-то не работает
        cart_listView.refresh();  // обновит список listView (автоматичесик не всегда вызывается)

        AmountLabel.setText(String.valueOf(selectedOrder.getPrise())); // установка цены
    }



    @FXML
    protected void StatusChoiceBoxAction(ActionEvent ignoredEvent) {
        Order selectionOrder = order_tableView.getSelectionModel().getSelectedItem();
        if (selectionOrder != null) {
            selectionOrder.setOrderStatus(StatusChoiceBox.getSelectionModel().getSelectedItem());

            // обновляем данные в таблице
            order_tableView.refresh();
        }
    }
}
