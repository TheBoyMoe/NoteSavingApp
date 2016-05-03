package com.example.demo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject{

    @PrimaryKey
    private Long mId;
    private String mTitle;
    private String mDescription;
    private String mImagePath;
    private String mVideoPath;
    private String mAudioPath;

    public Note() {  }

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

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public String getVideoPath() {
        return mVideoPath;
    }

    public void setVideoPath(String videoPath) {
        mVideoPath = videoPath;
    }

    public String getAudioPath() {
        return mAudioPath;
    }

    public void setAudioPath(String audioPath) {
        mAudioPath = audioPath;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
