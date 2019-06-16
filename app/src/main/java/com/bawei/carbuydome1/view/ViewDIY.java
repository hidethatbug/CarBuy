package com.bawei.carbuydome1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bawei.carbuydome1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:程金柱
 * Date:2019/6/15 11:53
 * Description：
 */

public class ViewDIY extends RelativeLayout {
    private EditText edt_num;
    private EditText btu_add;
    private EditText btu_jian;

    public ViewDIY(Context context) {
        this(context, null);
    }

    public ViewDIY(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewDIY(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate =  LayoutInflater.from(context).inflate(R.layout.viewdiy,this,true);
//         View.inflate(context, R.layout.viewdiy, this);
        edt_num = inflate.findViewById(R.id.edt_num);
        btu_add = inflate.findViewById(R.id.btu_add);
        btu_jian = inflate.findViewById(R.id.btu_jian);
        setting();
    }

    private void setting() {
        btu_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = edt_num.getText().toString().trim();
                int i = Integer.parseInt(trim);
                i++;
                edt_num.setText("" + i);
            }
        });
        btu_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = edt_num.getText().toString().trim();
                int i = Integer.parseInt(trim);
                i--;
                if (i == 1) {
                    i = 1;
                }
                edt_num.setText("" + i);
            }
        });

    }


}
