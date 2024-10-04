package com.zkdas.oop.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;


import java.io.IOException;

public class MainWindowController {
    @FXML
    private Tab containerTab1;
    @FXML
    private Tab containerTab2;

    public void initialize() throws IOException {
        loadItemsTab();
        loadCustomersTab();
    }


    private void loadItemsTab() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/ItemsTab.fxml"));
        Parent subview = loader.load();
        containerTab1.setContent(subview);

    }
    private void loadCustomersTab() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/CustomersTab.fxml"));
        Parent subview = loader.load();
        containerTab2.setContent(subview);
    }
}
