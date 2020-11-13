package com.passion.beawareapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity( tableName = "news_table" )
public class News {

    @PrimaryKey( autoGenerate = true )
    private long id;

    private String title;
    private String content;

    @ColumnInfo( typeAffinity = ColumnInfo.BLOB )
    private byte[] img;


    public News(String title, String content, byte[] img ){
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public byte[] getImg() {
        return img;
    }

    public void setId(long id) {
        this.id = id;
    }





}