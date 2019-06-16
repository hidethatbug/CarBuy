package com.bawei.carbuydome1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bawei.carbuydome1.adapter.XrecyclerAdapter;
import com.bawei.carbuydome1.contract.ShopContract;
import com.bawei.carbuydome1.entry.JsonBean;
import com.bawei.carbuydome1.entry.ShopBean;
import com.bawei.carbuydome1.presenter.ShopPresenterImpl;
import com.bawei.carbuydome1.utile.App;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity implements ShopContract.ShopView {
    private static final String TAG = "MainActivity";
    private ShopContract.ShopPresenter presenter;
    private Unbinder bind;
    @BindView(R.id.xrecycler_home)
    XRecyclerView xRecyclerView;
    private XrecyclerAdapter xrecyclerAdapter;
    @BindView(R.id.text_price)
    TextView text_price;
    @BindView(R.id.check_all)
    CheckBox check_all;
    @BindView(R.id.but_buy)
    Button btn_buy;
    private double aDouble=0;
Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        presenter = new ShopPresenterImpl();
        presenter.attach(this);
        presenter.getid("15605649738176366", "6366");
        EventBus.getDefault().register(this);

    }

    @Subscribe(sticky = true)
    public void getAddOrJian(String s){
        aDouble=0;
        getAllPrice();
    }
    private void initView() {
        bind = ButterKnife.bind(this);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<JsonBean> list = App.getDaoSession().queryBuilder(JsonBean.class).list();
        Log.i(TAG, "initView: " + list.size());

    }

    @Override
    public void getData(Object o) {
        List<ShopBean.Result> result = ((ShopBean) o).result;
        xrecyclerAdapter = new XrecyclerAdapter(MainActivity.this, result);
        xRecyclerView.setAdapter(xrecyclerAdapter);
        xrecyclerAdapter.notifyDataSetChanged();





    }

    /**
     * 计算总价的方法
     * 通过对是否选中判断来进行总价的计算
     */
    private void getAllPrice() {
        for (ShopBean.Result result : xrecyclerAdapter.getResult()) {

            for (ShopBean.Result.Shop shop : result.shoppingCartList) {
                if (shop.cleck){
                    int num = shop.num;
                    String price = shop.price;
                    int i = Integer.parseInt(price);
                    aDouble+=i*num;
                }

            }
        }
        text_price.setText("￥"+aDouble+"");
    }

    /**
     * 对于全选的逻辑
     * @param view
     */
    @OnClick(R.id.check_all)
    public void setCheck_all(View view) {
        if (check_all.isChecked()){
            aDouble=0;
            for (ShopBean.Result result : xrecyclerAdapter.getResult()) {
            result.yi=true;
                for (ShopBean.Result.Shop shop : result.shoppingCartList) {
                    shop.cleck=true;
                }
            }
            getAllPrice();
        }else {
            for (ShopBean.Result result : xrecyclerAdapter.getResult()) {
                result.yi=false;
                for (ShopBean.Result.Shop shop : result.shoppingCartList) {
                    shop.cleck=false;
                }
            }
            aDouble=0;
            text_price.setText("￥"+aDouble+"");
        }

        xrecyclerAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        /**
         * 解绑
         */
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
            bind = null;
        }
        presenter.detach();
    }
}
