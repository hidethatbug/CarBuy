package com.bawei.carbuydome1.contract;

import com.bawei.carbuydome1.net.DataCallBack;

/**
 * Author:程金柱
 * Date:2019/6/15 9:08
 * Description：
 */

public interface ShopContract {
    interface ShopModel{
        void getData(String uid, String sid, DataCallBack dataCallBack);
    }
    interface ShopView{
        void getData(Object o);
    }
    interface ShopPresenter{
        void getid(String sid,String uid);
        void attach(ShopView shopView);
        void detach();
    }
}
