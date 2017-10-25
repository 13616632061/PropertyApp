package com.glory.bianyitong.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lucy on 2017/10/25.
 */
public class FormatNowDate {
    public String refFormatNowDate() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMM");
        String retStrFormatNowDate = sdFormatter.format(nowTime);

        SimpleDateFormat sdFormatter2 = new SimpleDateFormat("dd");
        String retStrFormatNowDate2 = sdFormatter2.format(nowTime);

        SimpleDateFormat sdFormatter3 = new SimpleDateFormat("yyyyMMddHHmmss");
        String retStrFormatNowDate3 = sdFormatter3.format(nowTime);


        String s = String.valueOf(System.currentTimeMillis());

        String pic=retStrFormatNowDate+"/"+retStrFormatNowDate2+"/"+retStrFormatNowDate3+s.substring(s.length()-4,s.length());

        return pic;
    }
}
