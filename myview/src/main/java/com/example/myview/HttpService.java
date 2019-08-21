package com.example.myview;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/2111:07
 * desc   :
 * package: Shop:
 */
public interface HttpService {
    void setUrl(String url);

    void setReuestData(byte[] reuestData);

    void execute();

    //设置两个接口的链接
    void setHttpCallBack(IHttpListener iHttpListener);
}
