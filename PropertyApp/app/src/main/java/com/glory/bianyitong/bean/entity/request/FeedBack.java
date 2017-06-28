package com.glory.bianyitong.bean.entity.request;

import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.util.DateUtil;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/28.
 */

public class FeedBack  {
    private String feedbackContext;

    public String getFeedbackContext() {
        return feedbackContext;
    }

    public FeedBack(String feedbackContext) {
        this.feedbackContext = feedbackContext;
    }

    public void setFeedbackContext(String feedbackContext) {
        this.feedbackContext = feedbackContext;

    }

    public String getFeedbackDateTime() {
        return feedbackDateTime;
    }

    public void setFeedbackDateTime(String feedbackDateTime) {
        this.feedbackDateTime = feedbackDateTime;
    }

    private String feedbackDateTime= DateUtil.format(new Date(),DateUtil.DEFAULT_PATTERN);
}
