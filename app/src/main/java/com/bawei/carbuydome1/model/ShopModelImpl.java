package com.bawei.carbuydome1.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.bawei.carbuydome1.contract.ShopContract;
import com.bawei.carbuydome1.entry.ShopBean;
import com.bawei.carbuydome1.net.DataCallBack;
import com.bawei.carbuydome1.net.HttpUtile;
import com.bawei.carbuydome1.net.ShopInfo;
import com.bawei.carbuydome1.utile.App;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

/**
 * Author:程金柱
 * Date:2019/6/15 9:12
 * Description：
 */

public class ShopModelImpl implements ShopContract.ShopModel {
    @SuppressLint("CheckResult")
    @Override
    public void getData(String uid, String sid, final DataCallBack dataCallBack) {
        ShopInfo data = HttpUtile.getInstance().getData(ShopInfo.class);
        data.getShop("",uid, sid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ShopBean>() {
            @Override
            public void accept(ShopBean shopBean) throws Exception {

                Log.i(TAG, "accept: "+shopBean.result.get(0).shoppingCartList.get(0).num);
                for (int i = 0; i < shopBean.result.size(); i++) {
                    ShopBean.Result result = shopBean.result.get(i);
                    for (int i1 = 0; i1 < result.shoppingCartList.size(); i1++) {
                        ShopBean.Result.Shop shop = result.shoppingCartList.get(i1);
                        shop.num=1;

                    }

                }
                dataCallBack.success(shopBean);
                String s = new Gson().toJson(shopBean);
                App.getDaoSession().insert(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

    }
}
