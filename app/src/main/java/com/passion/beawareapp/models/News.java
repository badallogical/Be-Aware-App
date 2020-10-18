package com.passion.beawareapp.models;

import java.net.URL;

public class News {

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
}

