package com.zkdas.oop;

import com.zkdas.oop.model.Customer.Address;
import com.zkdas.oop.model.Customer.Events.AddressChanged;
import com.zkdas.oop.model.Customer.Events.IAddressEventListener;
import com.zkdas.oop.model.Item.Events.IItemEventListener;
import com.zkdas.oop.model.Item.Events.ItemEvent;
import com.zkdas.oop.model.Item.Item;
import com.zkdas.oop.service.dataFactory.CustomerFactory;
import com.zkdas.oop.service.dataFactory.ItemFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ItemListener implements IItemEventListener {
    private int event_count = 0;


    /**
     * Метод для обработки события
     *
     * @param event событие
     */
    @Override
    public <T extends ItemEvent> void processEvent(T event) {
        System.out.println(event.getClass().getName());
        event_count++;
    }

    public int getEvent_count() {
        return event_count;
    }
}

public class EventTest {
    private int address_event_count;
    @Test
    public void testAddressEvent() throws Exception {
        address_event_count = 0;
        Address address = CustomerFactory.createAddress();

        address.addListener(new IAddressEventListener() {
            @Override
            public void processEvent(AddressChanged event) {
                System.out.println(event.get_filed_name());
                address_event_count++;
            }
        });

        address.setCountry("Россия");
        address.setCity("Томск");
        address.setStreet("пер Ямской");
        address.setBuilding("нет");
        address.setApartment("1");
        address.setPostIndex("654321");

        assertEquals(address_event_count, 6);
    }

    @Test
    public void testItemEvent() throws Exception {
        Item item = ItemFactory.createItem();

        ItemListener itemListener = new ItemListener();
        item.addEventListener(Item.EventType.CostChanged, itemListener);
        item.addEventListener(Item.EventType.InfoChanged, itemListener);
        item.addEventListener(Item.EventType.NameChanged, itemListener);

        item.setName("лимон данный самой жизнью");
        item.setInfo("Взрывной лимон");
        item.setCost(2);

        assertEquals(itemListener.getEvent_count(), 3);
    }
}
