package com.example.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.shop.adapter.XreVpter;
import com.example.shop.api.Api;
import com.example.shop.bean.Bean;
import com.example.shop.bean.DataBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private XRecyclerView mMyxre;
    private XreVpter xreVpter;
    private Toolbar mMytoobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(Api.baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = build.create(Api.class);
        api.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        List<DataBean> data = bean.getData();
                        xreVpter.addData(data);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView() {
        mMytoobar = findViewById(R.id.mytoobar);
        mMyxre = findViewById(R.id.myxre);
        setSupportActionBar(mMytoobar);
        mMyxre.setLayoutManager(new LinearLayoutManager(this));
        mMyxre.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        xreVpter = new XreVpter(this);
        mMyxre.setAdapter(xreVpter);

    }
}
