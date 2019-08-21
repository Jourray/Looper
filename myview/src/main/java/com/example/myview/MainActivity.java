package com.example.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mMybt;
    private String url = "http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMybt = findViewById(R.id.mybt);
        mMybt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mybt:
                Volley.sendJsonResest(null, url, Bean.class, new IDataListener<Bean>() {
                    @Override
                    public void onSuccess(Bean o) {
                        List<Bean.DataBean> data = o.getData();
                        Log.d(TAG, "onSuccess: " + data.get(0).getFood_str());
                        Toast.makeText(MainActivity.this, data.get(0).getFood_str(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure() {
                        Log.d(TAG, "onFailure: ");
                    }
                });
                break;
        }
    }
}
