package com.bawei.carbuydome1.presenter;

import com.bawei.carbuydome1.contract.ShopContract;
import com.bawei.carbuydome1.model.ShopModelImpl;
import com.bawei.carbuydome1.net.DataCallBack;

/**
 * Author:程金柱
 * Date:2019/6/15 9:34
 * Description：
 */

public class ShopPresenterImpl implements ShopContract.ShopPresenter {
    private ShopContract.ShopModel shopModel;
    private ShopContract.ShopView shopView;
    @Override
    public void getid(String sid, String uid) {
        shopModel.getData(uid, sid, new DataCallBack() {
            @Override
            public void success(Object o) {
                shopView.getData(o);
            }

            @Override
            public void error(String error) {

            }
        });
    }

    @Override
    public void attach(ShopContract.ShopView shopView) {
            this.shopView=shopView;
            shopModel=new ShopModelImpl();
    }

    @Override
    public void detach() {
        if (shopModel!=null){
            shopModel=null;
        }
        if (shopView!=null){
            shopView=null;
        }
        System.gc();
    }
}
