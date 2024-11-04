package com.zkdas.oop.controller;

import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.PriorityOrder;
import com.zkdas.oop.model.PriorityOrderTime;
import com.zkdas.oop.model.Store;
import com.zkdas.oop.service.dataFactory.CustomerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import static com.zkdas.oop.service.EnumUtils.getRandomEnum;

public class PriorityOrdersTabController extends OrdersTabController {
    private PriorityOrder _order; // надо удалять и создавать новый при отчистке корзины
    @FXML
    private ChoiceBox<PriorityOrderTime> deliveryTime_ChoiceBox;

    @Override
    protected void tableInitialize() {
    }

    @Override
    public void initialize() throws IOException {
        super.initialize();
        deliveryTime_ChoiceBox.getItems().addAll(PriorityOrderTime.values());

        clear_btn_click();

        // установка обработчика на выбор категории в выпадавшем списке
        deliveryTime_ChoiceBox.addEventHandler(ActionEvent.ACTION, event -> {
            if (_order != null) {
                // установка в ордере значения время доставки
                _order.setDesiredDeliveryTime(deliveryTime_ChoiceBox.getValue());
            }
        });
    }

    @FXML
    @Override
    protected void StatusChoiceBoxAction(ActionEvent ignoredEvent) {

    }

    @FXML
    private void clear_btn_click() {
        _order = null; // мусорщик сожрет объект, если на него не осталось ссылок
        deliveryTime_ChoiceBox.setValue(getRandomEnum(PriorityOrderTime.class));

        try {
            _order = new PriorityOrder(CustomerFactory.createCustomer(), new Date(), deliveryTime_ChoiceBox.getValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        orderTableViewAction(_order);

        AmountLabel.setText(String.valueOf(_order.getPrise())); // установка цены
    }

    @FXML
    private void remove_btn_click(ActionEvent ignoredE) {
        ItemForList select_item = cart_listView.getSelectionModel().getSelectedItem();
        if (select_item != null) {
            _order.getItems().remove(select_item);
            AmountLabel.setText(String.valueOf(_order.getPrise())); // установка цены
        }
    }


    @FXML
    private void add_btn_click(ActionEvent ignoredE) {
        Random rand = new Random();

        Store store = new Store();
        _order.getItems().add(store.getItems().get(rand.nextInt(store.getItems().size())));

        AmountLabel.setText(String.valueOf(_order.getPrise())); // установка цены
    }


}
