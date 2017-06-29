package com.glory.bianyitong.bean.entity.request;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 * 投诉提交
 */

public class RequestSuggest {
    private int complaintsTypeID;//投诉人类型ID

    public RequestSuggest(int complaintsTypeID, String cmplaintsPhone, String complaintsContent) {
        this.complaintsTypeID = complaintsTypeID;
        this.cmplaintsPhone = cmplaintsPhone;
        this.complaintsContent = complaintsContent;
    }

    private String cmplaintsPhone;//投诉人电话号码
    private String complaintsContent;//String	投诉内容
    private List<ListComplaintsPic> listComplaintsPic;//实体对象集合	投诉图片集合
    public static  class  ListComplaintsPic{
        private int complaintsID;//	投诉ID
        private String picturePath	;//投诉图片

        public ListComplaintsPic(int complaintsID, String picturePath) {
            this.complaintsID = complaintsID;
            this.picturePath = picturePath;
        }
    }

    public int getComplaintsTypeID() {
        return complaintsTypeID;
    }

    public void setComplaintsTypeID(int complaintsTypeID) {
        this.complaintsTypeID = complaintsTypeID;
    }

    public String getCmplaintsPhone() {
        return cmplaintsPhone;
    }

    public void setCmplaintsPhone(String cmplaintsPhone) {
        this.cmplaintsPhone = cmplaintsPhone;
    }

    public String getComplaintsContent() {
        return complaintsContent;
    }

    public void setComplaintsContent(String complaintsContent) {
        this.complaintsContent = complaintsContent;
    }

    public List<ListComplaintsPic> getListComplaintsPic() {
        return listComplaintsPic;
    }

    public void setListComplaintsPic(List<ListComplaintsPic> listComplaintsPic) {
        this.listComplaintsPic = listComplaintsPic;
    }
}
