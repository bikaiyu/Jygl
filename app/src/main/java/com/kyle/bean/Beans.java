package com.kyle.bean;

import java.security.Timestamp;

/**
 * Created by Administrator on 2015/3/24.
 */
public class Beans {
    private String adddate;
    private String address;
    private String des;
    private String icon;
    private String timestamp;
    private String title;
    private int video;
    private int id;

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Beans{" +
                "adddate='" + adddate + '\'' +
                ", address='" + address + '\'' +
                ", des='" + des + '\'' +
                ", icon='" + icon + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", id=" + id +
                '}';
    }
}
