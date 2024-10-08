package com.zkdas.oop.controller;

import com.zkdas.oop.service.dataFactory.CustomerFactory;
import com.zkdas.oop.service.dataFactory.ItemFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

import java.io.*;

public class MainWindowController {
    /**
     * Контролер Главного окна
     */
    @FXML
    private Tab containerTab1;
    @FXML
    private Tab containerTab2;

    private ItemsTabController controllerTab1;
    private CustomersTabController controllerTab2;

    public void initialize() throws IOException {
        loadItemsTab();
        loadCustomersTab();
    }


    private void loadItemsTab() throws IOException {
        // указания пути к fxml файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/ItemsTab.fxml"));
        Parent subview = loader.load();// загрузка данных из файла
        // вставка subview во вкладку
        containerTab1.setContent(subview);

        // Получаю ссылку на контролер, который был связан с загруженным FXML-файлом.
        // Важно: контролер создастся только после FXMLLoader.load()
        controllerTab1 = loader.getController();

    }

    private void loadCustomersTab() throws IOException {
        // указания пути к fxml файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/CustomersTab.fxml"));
        Parent subview = loader.load();// загрузка данных из файла

        // вставка subview во вкладку
        containerTab2.setContent(subview);

        // получаю ссылку на контролер, который был связан с загруженным FXML-файлом.
        controllerTab2 = loader.getController();
    }

    @FXML
    private void menuGenerateElementClick(ActionEvent actionEvent) throws Exception {
        if (containerTab1.isSelected()) {
            controllerTab1.addItem(ItemFactory.createItem());
        } else if (containerTab2.isSelected()) {
            controllerTab2.addCustomer(CustomerFactory.createCustomer());
        }
    }

    @FXML
    private void menuGenerateElementsClick(ActionEvent actionEvent) throws Exception {
        if (containerTab1.isSelected()) {
            for (int i = 0; i < 10; i++) {
                controllerTab1.addItem(ItemFactory.createItem());
            }
        } else if (containerTab2.isSelected()) {
            for (int i = 0; i < 10; i++) {
                controllerTab2.addCustomer(CustomerFactory.createCustomer());
            }
        }
    }
}
