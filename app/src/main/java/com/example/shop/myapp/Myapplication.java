package com.example.shop.myapp;

import android.app.Application;
import android.content.Context;

import client.example.text.dao.DaoMaster;
import client.example.text.dao.DaoSession;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/199:38
 * desc   :
 * package: Shop:
 */
public class Myapplication extends Application {
    private static Myapplication myapplication;
    private static Context context;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        myapplication = this;
        context = getApplicationContext();
        daoSession = new DaoMaster(new DaoMaster.DevOpenHelper(this, "user.db").getWritableDatabase()).newSession();
    }

    public static Myapplication getInstance() {
        if (myapplication == null) {
            synchronized (Myapplication.class) {
                if (myapplication == null) {
                    myapplication = new Myapplication();
                }
            }
        }
        return myapplication;
    }

    public static Context getContext() {
        return context;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
