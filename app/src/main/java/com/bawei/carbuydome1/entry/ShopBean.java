package com.bawei.carbuydome1.entry;

import org.greenrobot.greendao.annotation.Entity;

import java.util.List;

/**
 * Author:程金柱
 * Date:2019/6/15 8:54
 * Description：
 */

public class ShopBean {
    public String message;
    public String status;
    public List<Result> result;
    public class Result{
        public boolean yi;
        public String categoryName;
        public List<Shop> shoppingCartList;
        public class Shop{
            public String commodityId;
            public String commodityName;
            public String count;
            public boolean cleck;
            public String pic;
            public String price;
            public int num=12;
        }
    }
}
