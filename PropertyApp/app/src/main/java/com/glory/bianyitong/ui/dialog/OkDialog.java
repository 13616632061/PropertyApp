package com.glory.bianyitong.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;

/**
 * Created by lucy on 2016/11/28.
 */
public class OkDialog extends Dialog{
    private static OkDialog callPhoneDialog = null;
    private View.OnClickListener clickListener;
    private Context context = null;

    public OkDialog(Context context) {
        super(context);
        this.context = context;
    }

    public OkDialog(Context context, int theme) {
        super(context, theme);
    }


    public  OkDialog createDialog(final String phone, final OnDialogClickListener onDialogClickListener) {

        callPhoneDialog = new OkDialog(context, R.style.dialog);
        callPhoneDialog.setContentView(R.layout.dialog_ok_dialog);
        //                customProgressDialog.setCancelable(false);
        callPhoneDialog.setCanceledOnTouchOutside(false);
        callPhoneDialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
//		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView tvMsg = (TextView) callPhoneDialog.findViewById(R.id.tv_call_number);
        if (tvMsg != null) {
            tvMsg.setText(phone);
        }

        TextView yes = (TextView)callPhoneDialog.findViewById(R.id.tv_call_yes);
        TextView tv_no = (TextView)callPhoneDialog.findViewById(R.id.tv_call_cancel);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogClickListener.onClickOk(callPhoneDialog);
            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneDialog.dismiss();
                onDialogClickListener.onClickCancel();
            }
        });
        return callPhoneDialog;
    }



    public void setOnClickOKListener(View.OnClickListener clickOKListener){
            this.clickListener=clickOKListener;
    }
    /**
     * [Summary]
     * setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public OkDialog setMessage(final String strMessage) {
        TextView tvMsg = (TextView) callPhoneDialog.findViewById(R.id.tv_call_number);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }

        return callPhoneDialog;
    }


   public interface OnDialogClickListener{
        void onClickOk(OkDialog okDialog);
        void onClickCancel();
    }

}
