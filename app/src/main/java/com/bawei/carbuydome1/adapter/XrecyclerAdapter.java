package com.bawei.carbuydome1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.carbuydome1.R;
import com.bawei.carbuydome1.entry.ShopBean;
import com.bawei.carbuydome1.utile.App;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:程金柱
 * Date:2019/6/15 10:40
 * Description：一级列表的适配器
 */

public class XrecyclerAdapter extends XRecyclerView.Adapter<XrecyclerAdapter.MyHodel> {
    private Context context;

    private  List<ShopBean.Result> result ;

    public XrecyclerAdapter(Context context, List<ShopBean.Result> result) {
        this.context = context;
        this.result = result;
    }

    /**
     * 向main提供数据
     * @return
     */
    public List<ShopBean.Result> getResult(){
        return result;
    }
    @NonNull
    @Override
    public MyHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_item, viewGroup, false);
        return new MyHodel(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHodel myHodel, int i) {
        ShopBean.Result resultA = result.get(i);
        List<ShopBean.Result.Shop> shoppingCartList = resultA.shoppingCartList;
        myHodel.title_name.setText(resultA.categoryName);
        myHodel.recyclerView_item.setLayoutManager(new LinearLayoutManager(context));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(context,shoppingCartList);
        myHodel.recyclerView_item.setAdapter(recyclerAdapter);
    }

    @Override
    public int getItemCount() {
        if (result != null && result.size() < 1) {
            return 0;
        }
        return result.size();
    }

    public class MyHodel extends XRecyclerView.ViewHolder {
        @BindView(R.id.recycler_item)
        public RecyclerView recyclerView_item;
        @BindView(R.id.title_name)
        public TextView title_name;

        public MyHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
