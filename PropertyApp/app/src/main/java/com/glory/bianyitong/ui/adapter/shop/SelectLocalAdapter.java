package com.glory.bianyitong.ui.adapter.shop;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryMyLocal;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */

public class SelectLocalAdapter extends BaseSectionQuickAdapter<ItemMenu<ResponseQueryMyLocal.ListAreaBean>,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SelectLocalAdapter(int layoutResId, int sectionHeadResId, List<ItemMenu<ResponseQueryMyLocal.ListAreaBean>> data) {
        super(layoutResId, sectionHeadResId, data);

    }

    @Override
    protected void convertHead(BaseViewHolder helper, ItemMenu<ResponseQueryMyLocal.ListAreaBean> item) {
        helper.setText(R.id.local_title_txt,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryMyLocal.ListAreaBean> item) {
        helper.setText(R.id.local_content_txt,item.getData().getArea_Name());

    }
}
