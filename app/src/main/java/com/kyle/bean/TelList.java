package com.kyle.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
public class TelList {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Tel> getNumber() {
        if(number==null){
            number = new ArrayList<>();
        }
        return number;
    }

    public void setNumber(List<Tel> number) {
        this.number = number;
    }

    private String title;
    private List<Tel> number;


}
