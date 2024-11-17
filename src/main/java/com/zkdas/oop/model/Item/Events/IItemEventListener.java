package com.zkdas.oop.model.Item.Events;

/**
 * Интерфейс для слушателя события
 */
public interface IItemEventListener {
    /**
     * Метод для обработки события
     * @param event событие
     */
    <T extends  ItemEvent> void processEvent(T event);
}
