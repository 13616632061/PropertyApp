package com.glory.bianyitong.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.glory.bianyitong.R;


/**
 * Created by lucy on 2017/9/6.
 */
public class DialogUtils {

    private static DialogUtils instance = null;

    public static DialogUtils getInstance() {
        if (instance == null) {
            instance = new DialogUtils();
        }
        return instance;
    }

    private DialogUtils(){}


    /**
     *
     * @param context
     * @param titleInfo 标题
     * @param callBack
     */
    public void showDialog(Context context, String titleInfo, final DialogUtils.DialogCallBack callBack) {
        AlertDialog.Builder alterDialog = new AlertDialog.Builder(context);
        alterDialog.setTitle(titleInfo);
        alterDialog.setIcon(R.drawable.jinggao);
        alterDialog.setCancelable(true);

        alterDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.exectEvent();
            }
        });
        alterDialog.show();
    }
    /**
     *
     * @param context
     * @param titleInfo 标题
     * @param isDouble  true是双按钮 false是单按钮
     * @param callBack
     */
    public void showDialog(Context context, String titleInfo,boolean isDouble, final DialogUtils.DialogCallBackTwo callBack) {
        AlertDialog.Builder alterDialog = new AlertDialog.Builder(context);
        alterDialog.setTitle(titleInfo);
        alterDialog.setIcon(R.drawable.jinggao);
        alterDialog.setCancelable(true);

        alterDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.exectEvent();
            }
        });

        alterDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.exectCancel();
            }
        });

        alterDialog.show();
    }

    public interface DialogCallBack {
        void exectEvent();
    }

    public interface DialogCallBackTwo {
        void exectEvent();
        void exectCancel();
    }
}
