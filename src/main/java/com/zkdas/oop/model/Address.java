package com.zkdas.oop.model;

import com.zkdas.oop.service.limitedStrinds.LimitedSting;
import com.zkdas.oop.service.limitedStrinds.PostIndex;

public class Address {
    private PostIndex _index;// почтовый индекс, целое шестизначное число.
    private LimitedSting _country; // страна/регион, строка, не более 50 символов.
    private LimitedSting _city; // город (населенный пункт), строка, не более 50 символов.
    private LimitedSting _street; // улица, строка, не более 100 символов.
    private LimitedSting _building;// номер дома, строка, не более 10 символов.
    private LimitedSting _apartment;// номер квартиры/помещения, не более 10 символов.
}
