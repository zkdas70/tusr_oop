package com.zkdas.oop.model;

import com.zkdas.oop.service.IdGenerator;
import com.zkdas.oop.service.limitedStrinds.LimitedSting;
import com.zkdas.oop.service.Validators.ValueValidator;
/// <summary>
///  класс модель данных Customer
/// </summary>
public class Customer
{

    private static final IdGenerator _idGenerator = new IdGenerator(); // генератор id принажавший не объект, а классу
    private final int  _id;
    protected LimitedSting _fulname;
    private LimitedSting _address;

    public Customer(String fulname, String address) throws Exception
    {
        _id = _idGenerator.get_next_id();

        setFulname(fulname);
        setAddress(address);
    }

    public <T extends Customer> Customer(T customer) throws Exception {
        this(customer.getFulname(), customer.getAddress());
    }

    public int getId(){
        return _id;
    }
    public String getFulname(){
        return _fulname.toString();
    }
    public String getAddress(){
        return _address.toString();
    }
    public void setFulname(String fulname) throws Exception {
        ValueValidator.AssertStringOnLength(fulname, 200, "name");

        _fulname = new LimitedSting(200, fulname);
    }
    public void setAddress(String address) throws Exception {
        ValueValidator.AssertStringOnLength(address, 500, "address");

        _address = new LimitedSting(500, address);
    }
}
