package com.bawei.carbuydome1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.carbuydome1.R;
import com.bawei.carbuydome1.entry.ShopBean;
import com.bawei.carbuydome1.utile.App;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.constraint.Constraints.TAG;

/**
 * Author:程金柱
 * Date:2019/6/15 10:40
 * Description：二级类表的适配器
 */

public class RecyclerAdapter extends XRecyclerView.Adapter<RecyclerAdapter.MyHodel> {
    private Context context;
    private List<ShopBean.Result.Shop> shoppingCartList;

    public RecyclerAdapter(Context context, List<ShopBean.Result.Shop> shoppingCartList) {
        this.context = context;
        this.shoppingCartList = shoppingCartList;
    }

    @NonNull
    @Override
    public MyHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new MyHodel(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHodel myHodel, int i) {

//对er级列表设置值
        ShopBean.Result.Shop shop = shoppingCartList.get(i);
        myHodel.text_name_item.setText(shop.commodityName);
        RequestOptions options = new RequestOptions()
                .error(R.drawable.loading_11)
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(context).load(shop.pic).apply(options).into(myHodel.img_item);
        myHodel.text_price_item.setText("￥" + shop.price);
        int num = shop.num;
//        Log.i(TAG, "onBindViewHolder: "+num);
        myHodel.edit_num_item.setText(shop.num + "");
        myHodel.check_item.setChecked(shop.cleck);
        onClick(myHodel, i);


    }

    /**
     * 点击事件
     * 利用eventbus返回值、在main界面接收对于总价进行处理
     * @param myHodel
     * @param i
     */
    private void onClick(final MyHodel myHodel, final int i) {
        myHodel.check_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = myHodel.check_item.isChecked();
                shoppingCartList.get(i).cleck = checked;
                EventBus.getDefault().postSticky("ss");

            }
        });
        myHodel.btn_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = shoppingCartList.get(i).num;
                num++;
                shoppingCartList.get(i).num = num;
                myHodel.edit_num_item.setText("" + shoppingCartList.get(i).num);
                EventBus.getDefault().postSticky("sdsad");
                notifyDataSetChanged();
            }

        });
        myHodel.btn_jian_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = shoppingCartList.get(i).num;
                num--;
                if (num <= 1) {
                    num = 1;
                }
                shoppingCartList.get(i).num = num;
                myHodel.edit_num_item.setText("" + shoppingCartList.get(i).num);
                EventBus.getDefault().postSticky("sdsad");
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        if (shoppingCartList != null && shoppingCartList.size() < 1) {
            return 0;
        }
        return shoppingCartList.size();
    }

    public class MyHodel extends XRecyclerView.ViewHolder {
        @BindView(R.id.check_item)
        public CheckBox check_item;
        @BindView(R.id.edit_num_item)
        public EditText edit_num_item;
        @BindView(R.id.img_item)
        public ImageView img_item;
        @BindView(R.id.text_name_item)
        public TextView text_name_item;
        @BindView(R.id.text_price_item)
        public TextView text_price_item;
        @BindView(R.id.btn_add_item)
        public Button btn_add_item;
        @BindView(R.id.btn_jian_item)
        public Button btn_jian_item;

        public MyHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
