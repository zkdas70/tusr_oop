package com.zkdas.oop.model;

import com.zkdas.oop.service.IdGenerator;
import com.zkdas.oop.service.limitedStrinds.LimitedFloat;
import com.zkdas.oop.service.limitedStrinds.LimitedSting;
import com.zkdas.oop.service.ValueValidator;
/// <summary>
///  класс модель дагнных Customer
/// </summary>
public class Customer
{

    private static IdGenerator _idGenerator = new IdGenerator(); // генератор id принажаший не обьектт а классу
    private int _id;
    protected LimitedSting _fulname;
    private LimitedSting _address;

    public Customer(String fulname, String address) throws Exception
    {
        _id = _idGenerator.get_next_id();

        ValueValidator.AssertStringOnLength(fulname, 200, "name");
        ValueValidator.AssertStringOnLength(address, 500, "info");

        _fulname = new LimitedSting(200, fulname);
        _address = new LimitedSting(500, address);
    }

    public int getId(){
        return _id;
    }
    public String getFulname(){
        return _fulname.get_data();
    }
    public String getAddress(){
        return _address.get_data();
    }
    public void setFulname(String fulname) throws Exception {
        _fulname.setData(fulname);
    }
    public void setAddress(String address) throws Exception {
        _address.setData(address);
    }
}
