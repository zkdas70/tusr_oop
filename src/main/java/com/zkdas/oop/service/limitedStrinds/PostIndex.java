package com.zkdas.oop.service.limitedStrinds;

public class PostIndex {
    private int postIndex;

    PostIndex(int postIndex) throws Exception {
        setPostIndex(postIndex);
    }

    void setPostIndex(int postIndex) throws Exception {
        if (100_000 > postIndex || postIndex >= 1_000_000) {
            throw new Exception("Post index out of bounds");
        }
        this.postIndex = postIndex;
    }
    int getPostIndex() {
        return postIndex;
    }
    @Override
    public String toString() {
        return String.valueOf(postIndex);
    }
}
