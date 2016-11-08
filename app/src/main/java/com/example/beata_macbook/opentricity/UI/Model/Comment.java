package com.example.beata_macbook.opentricity.UI.Model;

/**
 * Created by alazelewska on 08.11.16.
 */

public class Comment {

    private final String text;

    private final String id;

    public Comment(final String id, final String text) {
        this.id = id;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

}
