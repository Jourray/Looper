package com.example.myview;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/2111:33
 * desc   :
 * package: Shop:
 */
public class JsonHttpService implements HttpService {
    private String url;
    private byte[] reuestData;
    IHttpListener httpListener;
    private HttpURLConnection urlConnection;


    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setReuestData(byte[] reuestData) {
        this.reuestData = reuestData;
    }
    @Override
    public void execute() {
        //真实的网络操作
        try {
            urlConnection = (HttpURLConnection) new URL(this.url).openConnection();
            urlConnection.setConnectTimeout(6000);
            urlConnection.setUseCaches(false);
            urlConnection.setReadTimeout(3000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            while (reuestData != null) {
                bufferedOutputStream.write(reuestData);
            }
            bufferedOutputStream.flush();
            outputStream.close();
            bufferedOutputStream.close();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                httpListener.onSueecs(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            httpListener.onFailure();
        } finally {
            urlConnection.disconnect();
        }

    }

    @Override
    public void setHttpCallBack(IHttpListener iHttpListener) {
        this.httpListener = iHttpListener;
    }
}
