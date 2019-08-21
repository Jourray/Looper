package com.example.myview;


import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/2111:40
 * desc   :
 * package: Shop:
 */
public class JsonHttpListener<M> implements IHttpListener {
    //字节码

    Class<M> responseClass;
    IDataListener dataListener;
    //用于换线程
    Handler handler = new Handler(Looper.getMainLooper());

    public JsonHttpListener(Class<M> responseClass, IDataListener dataListener) {
        this.responseClass = responseClass;
        this.dataListener = dataListener;
    }

    @Override
    public void onSueecs(InputStream inputStream) {
        //获得相应结果，转换String
        try {
            String content = getContent(inputStream);
            //转换字符串为对象
            final M m = JSON.parseObject(content, responseClass);

            //返回结果调用层
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onSuccess(m);
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure() {
        //结构传到调用层
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (dataListener != null) {
                    dataListener.onFailure();
                }
            }
        });
    }

    private String getContent(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line + "\n");
        }
        return stringBuffer.toString();
    }
}
