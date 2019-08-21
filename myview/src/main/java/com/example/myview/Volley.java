package com.example.myview;

import java.util.Date;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/2114:13
 * desc   :
 * package: Shop:
 */
public class Volley {

    public static <T, M> void sendJsonResest(T resestuInfo, String url, Class<M> response, IDataListener dataListener) {

        JsonHttpService jsonHttpService = new JsonHttpService();
        JsonHttpListener jsonHttpListener = new JsonHttpListener(response, dataListener);
        HttpTask<T> tHttpTask = new HttpTask<>(resestuInfo, url, jsonHttpListener, jsonHttpService);
        ThreadPoolManager.getInstance().execute(tHttpTask);
    }
}
