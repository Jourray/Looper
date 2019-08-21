package com.example.myview;

import java.io.InputStream;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/2111:09
 * desc   : 封装相应
 * package: Shop:
 */
public interface IHttpListener {
    //接受上一个接口的结果

    void onSueecs(InputStream inputStream);

    void onFailure();

}
