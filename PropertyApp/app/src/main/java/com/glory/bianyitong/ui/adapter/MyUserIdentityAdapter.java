package com.glory.bianyitong.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.MyUserIdentityInfo;

import java.util.List;

/**
 * Created by lucy on 2018/1/4.
 */
public class MyUserIdentityAdapter extends BaseQuickAdapter<MyUserIdentityInfo.ListUserIdentityBean,BaseViewHolder> {
    public MyUserIdentityAdapter(@LayoutRes int layoutResId, @Nullable List<MyUserIdentityInfo.ListUserIdentityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyUserIdentityInfo.ListUserIdentityBean item) {
        CheckBox checkBox=  helper.getView(R.id.check);
        checkBox.setText(item.getUserIDentityName());
        if (item.isCheck()){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }
        helper.addOnClickListener(R.id.check);
    }


}
