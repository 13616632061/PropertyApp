package com.glory.bianyitong.widght.shop;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.glory.bianyitong.R;

/**
 * 自定义组件：购买数量，带减少增加按钮
 * Created by hiwhitley on 2016/7/4.
 */
public class AmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int position=-1;
    public void setAmount(int amount) {
        this.amount = amount;
        etAmount.setText(this.amount + "");
        if (this.amount>=0){
            etAmount.setVisibility(VISIBLE);
            btnDecrease.setVisibility(VISIBLE);
        }
    }

    private int amount = 0; //购买数量
    private int goods_storage = 100; //商品库存

    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        etAmount.setVisibility(GONE);
        btnDecrease.setVisibility(GONE);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);
        etAmount.setFocusable(false);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        obtainStyledAttributes.recycle();

    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount >= 0) {
                amount--;
                etAmount.setText(amount + "");
                if (amount==0){
                    etAmount.setVisibility(GONE);
                    btnDecrease.setVisibility(GONE);
                }
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
                if (amount>=0){
                    etAmount.setVisibility(VISIBLE);
                    btnDecrease.setVisibility(VISIBLE);
                }
            }
        }

        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount,position);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }

//        if (mListener != null) {
//            mListener.onAmountChange(this, amount,position);
//        }
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount,int position);
    }


}
