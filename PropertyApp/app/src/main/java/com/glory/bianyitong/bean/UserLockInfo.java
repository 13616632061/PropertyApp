package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/23.
 */
public class UserLockInfo extends BaseResponseBean {


    private List<ListUserLockMappingBean> listUserLockMapping;

    public List<ListUserLockMappingBean> getListUserLockMapping() {
        return listUserLockMapping;
    }

    public void setListUserLockMapping(List<ListUserLockMappingBean> listUserLockMapping) {
        this.listUserLockMapping = listUserLockMapping;
    }

    public static class ListUserLockMappingBean {
        /**
         * userLockID : 339
         * userID : 1
         * communityID : 1
         * communityName : 西丽小区
         * lockID : 42
         * lockName : 荣耀大门
         * authorizationType : A
         * authorizationUserID : 66
         * authorizationUserPhone : 13510012206
         * authorizationDateTime : 2017-06-26T17:47:38
         * userIdentity : 1
         * userIdentityName : 业主
         * timeLimit : false
         * status : E
         * lockSort : 0
         * authorizationUserName :
         */
        private int userLockID;
        private int userID;
        private int communityID;
        private String communityName;
        private int lockID;
        private String lockName;
        private String authorizationType;
        private int authorizationUserID;
        private String authorizationUserPhone;
        private String authorizationDateTime;
        private int userIdentity;
        private String userIdentityName;
        private boolean timeLimit;
        private String status;
        private int lockSort;
        private String authorizationUserName;

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        private String startDate;
        private String endDate;

        public int getUserLockID() {
            return userLockID;
        }

        public void setUserLockID(int userLockID) {
            this.userLockID = userLockID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public int getLockID() {
            return lockID;
        }

        public void setLockID(int lockID) {
            this.lockID = lockID;
        }

        public String getLockName() {
            return lockName;
        }

        public void setLockName(String lockName) {
            this.lockName = lockName;
        }

        public String getAuthorizationType() {
            return authorizationType;
        }

        public void setAuthorizationType(String authorizationType) {
            this.authorizationType = authorizationType;
        }

        public int getAuthorizationUserID() {
            return authorizationUserID;
        }

        public void setAuthorizationUserID(int authorizationUserID) {
            this.authorizationUserID = authorizationUserID;
        }

        public String getAuthorizationUserPhone() {
            return authorizationUserPhone;
        }

        public void setAuthorizationUserPhone(String authorizationUserPhone) {
            this.authorizationUserPhone = authorizationUserPhone;
        }

        public String getAuthorizationDateTime() {
            return authorizationDateTime;
        }

        public void setAuthorizationDateTime(String authorizationDateTime) {
            this.authorizationDateTime = authorizationDateTime;
        }

        public int getUserIdentity() {
            return userIdentity;
        }

        public void setUserIdentity(int userIdentity) {
            this.userIdentity = userIdentity;
        }

        public String getUserIdentityName() {
            return userIdentityName;
        }

        public void setUserIdentityName(String userIdentityName) {
            this.userIdentityName = userIdentityName;
        }

        public boolean isTimeLimit() {
            return timeLimit;
        }

        public void setTimeLimit(boolean timeLimit) {
            this.timeLimit = timeLimit;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getLockSort() {
            return lockSort;
        }

        public void setLockSort(int lockSort) {
            this.lockSort = lockSort;
        }

        public String getAuthorizationUserName() {
            return authorizationUserName;
        }

        public void setAuthorizationUserName(String authorizationUserName) {
            this.authorizationUserName = authorizationUserName;
        }
    }
}
