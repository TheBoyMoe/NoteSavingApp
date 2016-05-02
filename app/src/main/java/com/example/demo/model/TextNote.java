package com.example.demo.model;

import io.realm.RealmObject;

public class TextNote extends RealmObject{

    private String mTitle;
    private String mDescription;

    public TextNote() {  }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
