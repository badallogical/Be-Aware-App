package com.passion.beawareapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.android.material.internal.ParcelableSparseBooleanArray;

import java.net.URL;

public class News implements Parcelable {

    private String title;
    private String desc;
    private String url_to_src;
    private String url_to_img;
    private String content;

    public News(){

    }

    public News(String title , String desc, String url_to_src, String url_to_img, String content ){
        this.title = title;
        this.desc = desc;
        this.url_to_src = url_to_src;
        this.url_to_img = url_to_img;
        this.content = content;
    }

    protected News(Parcel in) {
        title = in.readString();
        desc = in.readString();
        url_to_src = in.readString();
        url_to_img = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(url_to_src);
        dest.writeString(url_to_img);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getContent() {
        return content;
    }

    public String getUrl_to_src() {
        return url_to_src;
    }

    public String getUtl_to_img() {
        return url_to_img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl_to_src( String url_to_src) {
        this.url_to_src = url_to_src;
    }

    public void setUtl_to_img( String utl_to_img) {
        this.url_to_img = utl_to_img;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public boolean validate() {
        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(url_to_img) || TextUtils.isEmpty(desc)){
            return false;
        }

        return true;
    }
}

