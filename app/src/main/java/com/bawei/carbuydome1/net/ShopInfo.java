package com.bawei.carbuydome1.net;

import com.bawei.carbuydome1.entry.ShopBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Author:程金柱
 * Date:2019/6/15 8:51
 * Description：
 */

public interface ShopInfo {
    @GET
    Observable<ShopBean> getShop(@Url String url, @Header("userId")String uid, @Header("sessionId")String sid);
}
