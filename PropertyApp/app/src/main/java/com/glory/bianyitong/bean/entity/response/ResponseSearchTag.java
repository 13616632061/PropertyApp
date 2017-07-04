package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 * 查询热门搜索标签
 */

public class ResponseSearchTag extends BaseResponseBean {

    /**
     * listfresh : null
     * tags : ["海鲜","精选,蔬菜","精选,水果","肉类","蔬菜","水产","水果"]
     */

    private Object listfresh;
    private List<String> tags;

    public Object getListfresh() {
        return listfresh;
    }

    public void setListfresh(Object listfresh) {
        this.listfresh = listfresh;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
