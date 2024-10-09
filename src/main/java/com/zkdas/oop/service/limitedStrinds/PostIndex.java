package com.zkdas.oop.service.limitedStrinds;

public class PostIndex {
    /**
     * Класс для хранения почтово индекса
     */
    private String postIndex; // может начинаться с нуля int использовать не корректно

    /**
     * Конструктор PostIndex
     */
    public PostIndex() {
    }

    /**
     * Конструктор PostIndex
     * @param postIndex int почтовый индекс
     */
    PostIndex(String postIndex) throws Exception {
        setPostIndex(postIndex);
    }

    public void setPostIndex(String postIndex) throws Exception {
        if (postIndex.length() != 6) {
            throw new Exception("Post index out of bounds");
        }
        Integer.parseInt(postIndex); // проверка, что содержит лиш числа
        this.postIndex = postIndex;
    }
    public String getPostIndex() {
        return postIndex;
    }
    @Override
    public String toString() {
        return String.valueOf(postIndex);
    }

}
