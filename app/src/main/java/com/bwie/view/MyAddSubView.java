package com.bwie.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.baweishop.R;

/**
 * date: 2019/1/4.
 * Created by Administrator
 * function:
 */
public class MyAddSubView extends LinearLayout implements View.OnClickListener {
    private int number = 6;
    private TextView sub_tv;
    private TextView product_number_tv;
    private TextView add_tv;

    public MyAddSubView(Context context) {
        this(context, null);
    }

    public MyAddSubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyAddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.cart_addsub_view, this);

        sub_tv = view.findViewById(R.id.txt_sub);
        product_number_tv = view.findViewById(R.id.txt_number);
        add_tv = view.findViewById(R.id.txt_add);


        sub_tv.setOnClickListener(this);
        add_tv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_sub:
                if (number > 1) {
                    --number;
                    product_number_tv.setText(number + "");
                    if (onNumberChangeListener != null) {
                        onNumberChangeListener.onNumberChange(number);
                    }
                } else {
                    Toast.makeText(getContext(), "商品数目不能小于0", Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.txt_add:
                ++number;
                product_number_tv.setText(number + "");
                if (onNumberChangeListener != null) {
                    onNumberChangeListener.onNumberChange(number);
                }
                break;
        }
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        product_number_tv.setText(number + "");
    }

    OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }

    public interface OnNumberChangeListener {
        void onNumberChange(int num);
    }
}