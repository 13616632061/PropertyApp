package com.glory.bianyitong.bean.entity;

/**
 * Created by lucy on 2017/7/3.
 */
public class FreshLeftInfo {

    public boolean flag=false;
    public String style;


    public FreshLeftInfo(boolean flag, String style) {
        this.flag = flag;
        this.style = style;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
