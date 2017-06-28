package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 * 分享信息
 */

public class ResponseShare extends BaseResponseBean {

    private List<ListSettingBean> listSetting;

    public List<ListSettingBean> getListSetting() {
        return listSetting;
    }

    public void setListSetting(List<ListSettingBean> listSetting) {
        this.listSetting = listSetting;
    }

    public static class ListSettingBean {
        /**
         * settingID : 15
         * settingKey : APPShareUrl
         * settingValue : http://byt.bytsz.com.cn/public/share.html
         * settingRemark : 分享页面
         * settingPermissions : false
         * isEnable : true
         */

        private int settingID;
        private String settingKey;
        private String settingValue;
        private String settingRemark;
        private boolean settingPermissions;
        private boolean isEnable;

        public int getSettingID() {
            return settingID;
        }

        public void setSettingID(int settingID) {
            this.settingID = settingID;
        }

        public String getSettingKey() {
            return settingKey;
        }

        public void setSettingKey(String settingKey) {
            this.settingKey = settingKey;
        }

        public String getSettingValue() {
            return settingValue;
        }

        public void setSettingValue(String settingValue) {
            this.settingValue = settingValue;
        }

        public String getSettingRemark() {
            return settingRemark;
        }

        public void setSettingRemark(String settingRemark) {
            this.settingRemark = settingRemark;
        }

        public boolean isSettingPermissions() {
            return settingPermissions;
        }

        public void setSettingPermissions(boolean settingPermissions) {
            this.settingPermissions = settingPermissions;
        }

        public boolean isIsEnable() {
            return isEnable;
        }

        public void setIsEnable(boolean isEnable) {
            this.isEnable = isEnable;
        }
    }
}
