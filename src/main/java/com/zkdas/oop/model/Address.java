package com.zkdas.oop.model;

import com.zkdas.oop.service.limitedStrinds.LimitedSting;
import com.zkdas.oop.service.limitedStrinds.PostIndex;

public class Address implements Cloneable {
    /**
     * Класс модель данных Address
     */
    private final PostIndex _index = new PostIndex();// почтовый индекс, целое шестизначное число.
    private final LimitedSting _country = new LimitedSting(50); // страна/регион, строка, не более 50 символов.
    private final LimitedSting _city = new LimitedSting(50); // город (населенный пункт), строка, не более 50 символов.
    private final LimitedSting _street = new LimitedSting(100); // улица, строка, не более 100 символов.
    private final LimitedSting _building = new LimitedSting(10);// номер дома, строка, не более 10 символов.
    private final LimitedSting _apartment = new LimitedSting(10);// номер квартиры/помещения, не более 10 символов.

    @Override
    public Address clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Конструктор класса Address
     * @param index int почтовый индекс, целое шестизначное число
     * @param country String страна/регион, строка, не более 50 символов
     * @param citi String город (населенный пункт), строка, не более 50 символов
     * @param street String лица, строка, не более 100 символов
     * @param building String номер дома, строка, не более 10 символов
     * @param apartment String номер квартиры/помещения, не более 10 символов
     */
    public Address(String index, String country, String citi, String street, String building, String apartment) throws Exception {
        _index.setPostIndex(index);
        _country.setData(country);
        _city.setData(citi);
        _street.setData(street);
        _building.setData(building);
        _apartment.setData(apartment);
    }

    /**
     * Конструктор по умолчанию
     */
    public Address() throws Exception {
    }

    ;

    /**
     * Копирующей конструктор
     * @param address Класс унаследованный от ад Address
     */
    public <T extends Address> Address(T address) throws Exception {
        this(address.getPostIndex(), address.getCountry(), address.getCity(),
                address.getStreet(), address.getBuilding(), address.getApartment());
    }

    public void setPostIndex(String index) throws Exception {
        _index.setPostIndex(index);
    }

    public void setCountry(String country) throws Exception {
        _country.setData(country);
    }

    public void setCity(String city) throws Exception {
        _city.setData(city);
    }

    public void setStreet(String street) throws Exception {
        _street.setData(street);
    }

    public void setBuilding(String building) throws Exception {
        _building.setData(building);
    }

    public void setApartment(String apartment) throws Exception {
        _apartment.setData(apartment);
    }

    public String getPostIndex() {
        return _index.getPostIndex();
    }

    public String getCountry() {
        return _country.toString();
    }

    public String getCity() {
        return _city.toString();
    }

    public String getStreet() {
        return _street.toString();
    }

    public String getBuilding() {
        return _building.toString();
    }

    public String getApartment() {
        return _apartment.toString();
    }

    public int getCountryLen() {
        return _country.getLength();
    }

    public int getCityLen() {
        return _city.getLength();
    }

    public int getStreetLen() {
        return _street.getLength();
    }

    public int getBuildingLen() {
        return _building.getLength();
    }

    public int getApartmentLen() {
        return _apartment.getLength();
    }
}
