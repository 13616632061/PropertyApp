package com.glory.bianyitong.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by lucy on 2017/9/12.
 */
public class ShopcartInfo<T> extends SectionEntity<T> {
    private T data;
    private boolean isMore;
    public ShopcartInfo(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }
    public ShopcartInfo(T data) {
        super(data);
        this.data=data;
    }


    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
    public T getData() {
        return data;
    }
}
