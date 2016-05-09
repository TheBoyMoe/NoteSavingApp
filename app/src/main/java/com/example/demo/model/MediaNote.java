package com.example.demo.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MediaNote extends RealmObject{

    @PrimaryKey
    private Long mId;
    private String mTextField1;
    private String mFilePath;
    private String mMimeType;


    public MediaNote() {}

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTextField1() {
        return mTextField1;
    }

    public void setTextField1(String textField1) {
        mTextField1 = textField1;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public void setFilePath(String filePath) {
        mFilePath = filePath;
    }

    public String getMimeType() {
        return mMimeType;
    }

    public void setMimeType(String mimeType) {
        mMimeType = mimeType;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getTextField1(), getFilePath());
    }
}
