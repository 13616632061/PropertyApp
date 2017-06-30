package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/30.
 * 举报
 */

public class RequestReport {
    private int reportType;//	int	举报类型：1近邻2近邻评论3新闻评论
    private int reportID;//	int	举报人ID
    private String aesPublisherID;//	int	被举报人ID（加密）

    public RequestReport(int reportType, int reportID, String aesPublisherID) {
        this.reportType = reportType;
        this.reportID = reportID;
        this.aesPublisherID = aesPublisherID;
    }

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getAesPublisherID() {
        return aesPublisherID;
    }

    public void setAesPublisherID(String aesPublisherID) {
        this.aesPublisherID = aesPublisherID;
    }
}
