package com.example.beata_macbook.opentricity.UI.Model;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by alazelewska on 08.11.16.
 */

public class Comment {

    private final String text;

    private final String date;

    private final String id;

    public Comment(final String id, final HashMap<String, String> content) {
        this.id = id;
        this.text = content.get(CommentFields.TEXT.name());
        this.date = content.get(CommentFields.DATE.name());
    }

    public String getText() {
        return this.text;
    }

    public String getDate() {
        return this.date;
    }

    public String getId() {
        return this.id;
    }
}
