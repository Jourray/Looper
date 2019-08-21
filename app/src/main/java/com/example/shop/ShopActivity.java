package com.example.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.adapter.ShopVpter;
import com.example.shop.bean.DataBean;
import com.example.shop.bean.NumBean;
import com.example.shop.utils.DbUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private Toolbar mMytoobar;
    private XRecyclerView mMyxre;
    private ShopVpter shopVpter;
    /**
     * 全选
     */
    private CheckBox mCheckbox;
    /**
     * ￥0.0
     */
    private TextView mTvPrice;
    /**
     * 去结算
     */
    private TextView mTvPlaceOrder;
    private ArrayList<NumBean> num = new ArrayList<>();
    public static HashMap<Integer, Boolean> hashMap = new HashMap<>();
    private int a;
    private List<DataBean> dataBeans;
    private int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initData();
    }

    private void initData() {
        dataBeans = DbUtils.UeryAll();
        if (dataBeans != null) {
            shopVpter.addData(dataBeans);
        }

    }

    private void initView() {
        mMytoobar = findViewById(R.id.mytoobar);
        mMyxre = findViewById(R.id.myxre);
        mCheckbox = findViewById(R.id.checkbox);
        mTvPrice = findViewById(R.id.tv_price);
        mTvPlaceOrder = findViewById(R.id.tv_place_order);
        setSupportActionBar(mMytoobar);
        setTitle("购物车");
        mMyxre.setLayoutManager(new LinearLayoutManager(this));
        mMyxre.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        shopVpter = new ShopVpter(this);
        mMyxre.setAdapter(shopVpter);
        mCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckbox.isChecked()) {
                    for (int i = 0; i < dataBeans.size(); i++) {
                        hashMap.put(i, true);
                        a += dataBeans.get(i).getNum() * tag;
                        for (int j = 0; j < hashMap.size(); j++) {
                            Boolean aBoolean = hashMap.get(j);
                            if (!aBoolean) {
                                a -= dataBeans.get(i).getNum() * tag;
                            }
                        }
                    }
                    mTvPrice.setText(a + "");
                    shopVpter.AllCheck();
                } else {
                    for (int i = 0; i < dataBeans.size(); i++) {
                        hashMap.remove(i);
                    }
                    a = 0;
                    mTvPrice.setText(a + "");
                }
                shopVpter.AllCheck();
            }
        });
        shopVpter.setAllOnclick(new ShopVpter.AllOnclick() {
            @Override
            public void OnItem(View view, int position, ArrayList<NumBean> num, ArrayList<DataBean> list, DataBean dataBean) {
                int math = dataBean.getNum();
                CheckBox viewById = view.findViewById(R.id.checkbox);
                if (viewById.isChecked()) {
                    tag = num.get(position).getTag();
                    a += math * tag;
                    mTvPrice.setText(a + "");
                } else {
                    a -= math * tag;
                    mTvPrice.setText(a + "");
                }
            }
        });
    }
}
