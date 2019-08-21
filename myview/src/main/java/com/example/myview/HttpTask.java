package com.example.myview;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/2111:12
 * desc   :
 * package: Shop:
 */
public class HttpTask<T> implements Runnable {
    private IHttpListener iHttpListener;
    private HttpService httpService;

    @Override
    public void run() {
        httpService.execute();
    }

    public <T> HttpTask(T reuestInfo, String url, IHttpListener iHttpListener, HttpService httpService) {
        this.httpService = httpService;
        this.iHttpListener = iHttpListener;
        httpService.setUrl(url);
        httpService.setHttpCallBack(iHttpListener);
        if (reuestInfo != null) {
            //对象转换字符串
            String s = JSON.toJSONString(reuestInfo);
            try {
                httpService.setReuestData(s.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
