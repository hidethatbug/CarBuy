package com.bawei.carbuydome1.utile;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.carbuydome1.gen.DaoMaster;
import com.bawei.carbuydome1.gen.DaoSession;

/**
 * Author:程金柱
 * Date:2019/6/15 16:29
 * Description：对于greendao的全局处理
 */

public class App extends Application {
    private static DaoSession daoSession;

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "my.db");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();

    }
}
