package com.example.shop.api;

import com.example.shop.bean.Bean;
import com.example.shop.bean.DataBean;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/199:11
 * desc   :
 * package: Shop:
 */
public interface Api {

    String baseurl = "http://www.qubaobei.com/";

    @GET("ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
    Observable<Bean> getData();
}
