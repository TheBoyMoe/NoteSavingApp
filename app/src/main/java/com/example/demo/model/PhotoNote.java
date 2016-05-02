package com.example.demo.model;

import io.realm.RealmObject;

public class PhotoNote extends RealmObject{

    private String mTitle;
    private String mDescription;
    private String mImagePath;

    public PhotoNote() {  }

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

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
