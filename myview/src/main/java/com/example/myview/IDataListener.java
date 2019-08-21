package com.example.myview;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/2111:46
 * desc   :
 * package: Shop:
 */
//回调
public interface IDataListener<M> {

    void onSuccess(M m);

    void onFailure();
}
