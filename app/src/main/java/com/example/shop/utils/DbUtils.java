package com.example.shop.utils;

import com.example.shop.myapp.Myapplication;
import com.example.shop.bean.DataBean;

import java.util.List;

import client.example.text.dao.DaoSession;
import client.example.text.dao.DataBeanDao;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/1910:08
 * desc   :
 * package: Shop:
 */
public class DbUtils {
    public  static void Insert(DataBean dataBean) {
        DaoSession daoSession = Myapplication.getDaoSession();
        if (UeryOne(dataBean.getId()) == null) {
            daoSession.insert(dataBean);
        }
    }

    public static DataBean UeryOne(String id) {
        DaoSession daoSession = Myapplication.getDaoSession();
        return daoSession.queryBuilder(DataBean.class)
                .where(DataBeanDao.Properties.Id.eq(id))
                .build()
                .unique();

    }

    public static List<DataBean> UeryAll() {
        DaoSession daoSession = Myapplication.getDaoSession();
        return daoSession.loadAll(DataBean.class);
    }
}
