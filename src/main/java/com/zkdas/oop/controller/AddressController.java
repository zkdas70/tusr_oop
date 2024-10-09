package com.zkdas.oop.controller;

import com.zkdas.oop.model.Address;
import com.zkdas.oop.service.Validators.DataRequiredValidator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class AddressController {
    /**
     * Контролер виджета адрес
     */
    @FXML
    private TextField PostField;
    @FXML
    private TextField CountryField;
    @FXML
    private TextField CityField;
    @FXML
    private TextField StreetField;
    @FXML
    private TextField BuildingField;
    @FXML
    private TextField ApartmentField;

    /**
     * Считает данные из полей
     * @param validator класс DataRequiredValidator для отслеживания ошибок
     * @return класс Address заполненный данными из полей
     */
    public Address getAddressFromFields(DataRequiredValidator validator) throws Exception {
        Address address = new Address();

        validator.validatePostField(PostField);
        validator.validateField(CountryField, address.getCountryLen());
        validator.validateField(CityField, address.getCityLen());
        validator.validateField(StreetField, address.getStreetLen());
        validator.validateField(BuildingField, address.getBuildingLen());
        validator.validateField(ApartmentField, address.getApartmentLen());

        if (validator.IsNotErrors()) {
            address.setPostIndex(PostField.getText());
            address.setCountry(CountryField.getText());
            address.setCity(CityField.getText());
            address.setStreet(StreetField.getText());
            address.setBuilding(BuildingField.getText());
            address.setApartment(ApartmentField.getText());
        }

        return address;
    }

    /**
     * Установит данные в поля
     * @param address класс Address заполненный данными
     */
    public void SetAddress(Address address) {
        PostField.setText(String.valueOf(address.getPostIndex()));
        CountryField.setText(address.getCountry());
        CityField.setText(address.getCity());
        StreetField.setText(address.getStreet());
        BuildingField.setText(address.getBuilding());
        ApartmentField.setText(address.getApartment());
    }
}
