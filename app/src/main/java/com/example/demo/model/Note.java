package com.example.demo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject{

    @PrimaryKey
    private Long mId;
    private String mType;
    private String mTextField1;
    private String mTextField2;
    private String mFilePath;
    private String mMimeType;

    public Note() {  }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

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

    public String getTextField2() {
        return mTextField2;
    }

    public void setTextField2(String textField2) {
        mTextField2 = textField2;
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
        return String.format("%s %s", getTextField1(), getTextField2());
    }

}
