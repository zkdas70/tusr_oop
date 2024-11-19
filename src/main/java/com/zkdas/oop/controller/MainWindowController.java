package com.zkdas.oop.controller;

import com.zkdas.oop.service.dataFactory.CustomerFactory;
import com.zkdas.oop.service.dataFactory.ItemFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.*;
/**
 * Контролер Главного окна
 */
public class MainWindowController {
    @FXML
    private TabPane mainTabPlate;
    @FXML
    private Tab containerTabItems;
    @FXML
    private Tab containerTabCustomers;
    @FXML
    private Tab containerTabCarts;
    @FXML
    private Tab containerTabOrders;

    // контролеры вкладок
    private ItemsTabController ItemsTabController;
    private CustomersTabController CustomersTabController;
    private CartsTabController CartsTabController;
    private OrdersTabController OrdersTabController;
    /**
     * Инициализация контролера
     */
    public void initialize() throws IOException {
        loadItemsTab();
        loadCustomersTab();
        loadCartsTab();
        loadOrdersTab();
    }

    /**
     * Загрузка вкладки ItemsTab
     */
    private void loadItemsTab() throws IOException {
        // указания пути к fxml файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/ItemsTab.fxml"));
        Parent subview = loader.load();// загрузка данных из файла
        // вставка subview во вкладку
        containerTabItems.setContent(subview);
        // Получаю ссылку на контролер, который был связан с загруженным FXML-файлом.
        // Важно: контролер создастся только после FXMLLoader.load()
        ItemsTabController = loader.getController();

    }

    /**
     * Загрузка вкладки CustomersTab
     */
    private void loadCustomersTab() throws IOException {
        // указания пути к fxml файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/CustomersTab.fxml"));
        Parent subview = loader.load();// загрузка данных из файла

        // вставка subview во вкладку
        containerTabCustomers.setContent(subview);
        // получаю ссылку на контролер, который был связан с загруженным FXML-файлом.
        CustomersTabController = loader.getController();
    }
    /**
     * Загрузка вкладки CartsTab
     */
    private void loadCartsTab() throws IOException {
        // указания пути к fxml файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/CartsTab.fxml"));
        Parent subview = loader.load();// загрузка данных из файла

        // вставка subview во вкладку
        containerTabCarts.setContent(subview);
        // получаю ссылку на контролер, который был связан с загруженным FXML-файлом.
        CartsTabController = loader.getController();
    }
    /**
     * Загрузка вкладки OrdersTab
     */
    private void loadOrdersTab() throws IOException {
        // указания пути к fxml файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/OrdersTab.fxml"));
        Parent subview = loader.load();// загрузка данных из файла

        // вставка subview во вкладку
        containerTabOrders.setContent(subview);
        // получаю ссылку на контролер, который был связан с загруженным FXML-файлом.
        OrdersTabController = loader.getController();
    }

    /**
     * Обработчик нажатия на кнопку "тест"/"сгенерировать элемент"
     */
    @FXML
    private void menuGenerateElementClick(ActionEvent actionEvent) throws Exception {
        if (containerTabItems.isSelected()) {
            ItemsTabController.addItem(ItemFactory.createItem());
        } else if (containerTabCustomers.isSelected()) {
            CustomersTabController.addCustomer(CustomerFactory.createCustomer());
        }
    }
    /**
     * Обработчик нажатия на кнопку "тест"/"сгенерировать 10 элементов"
     */
    @FXML
    private void menuGenerateElementsClick(ActionEvent actionEvent) throws Exception {
        if (containerTabItems.isSelected()) {
            for (int i = 0; i < 10; i++) {
                ItemsTabController.addItem(ItemFactory.createItem());
            }
        } else if (containerTabCustomers.isSelected()) {
            for (int i = 0; i < 10; i++) {
                CustomersTabController.addCustomer(CustomerFactory.createCustomer());
            }
        }
    }
}
