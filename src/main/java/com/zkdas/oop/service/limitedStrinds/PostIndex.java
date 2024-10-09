package com.zkdas.oop.service.limitedStrinds;

public class PostIndex {
    /**
     * Класс для хранения почтово индекса
     */
    private int postIndex;

    /**
     * Конструктор PostIndex
     */
    public PostIndex() {
    }

    /**
     * Конструктор PostIndex
     * @param postIndex int почтовый индекс
     */
    PostIndex(int postIndex) throws Exception {
        setPostIndex(postIndex);
    }

    public void setPostIndex(int postIndex) throws Exception {
        if (100_000 > postIndex || postIndex >= 1_000_000) {
            throw new Exception("Post index out of bounds");
        }
        this.postIndex = postIndex;
    }
    public int getPostIndex() {
        return postIndex;
    }
    @Override
    public String toString() {
        return String.valueOf(postIndex);
    }

}
