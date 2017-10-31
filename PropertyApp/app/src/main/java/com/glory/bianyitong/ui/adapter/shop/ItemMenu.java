package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ItemMenu<T> extends SectionEntity<T> implements Serializable{


    private T data;

    public ItemMenu(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ItemMenu(T data) {
        super(data);
        this.data=data;
    }


    public T getData() {
        return data;
    }
}
