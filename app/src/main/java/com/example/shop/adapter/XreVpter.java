package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shop.R;
import com.example.shop.ShopActivity;
import com.example.shop.bean.DataBean;
import com.example.shop.myapp.Myapplication;
import com.example.shop.utils.DbUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.ArrayList;
import java.util.List;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/198:48
 * desc   :
 * package: Shop:
 */
public class XreVpter extends XRecyclerView.Adapter<XreVpter.ViewHolder> {
    private Context context;
    private ArrayList<DataBean> list = new ArrayList();

    public XreVpter(Context context) {
        this.context = context;
    }

    public void addData(List<DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_viewholder_layolut, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.food_str.setText(list.get(i).getFood_str());
        viewHolder.price.setText("￥" + list.get(i).getNum() + "");
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(context).load(list.get(i).getPic()).apply(requestOptions).into(viewHolder.image);
        Bitmap bitmap = BitmapFactory.decodeResource(Myapplication.getContext().getResources(), R.mipmap.xiaoxing01);
        int height = bitmap.getHeight();
        ViewGroup.LayoutParams layoutParams = viewHolder.ratingBar.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        viewHolder.ratingBar.setLayoutParams(layoutParams);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbUtils.Insert(list.get(i));
                Toast.makeText(context, "添加购物车", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, ShopActivity.class);
                context.startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView food_str;
        TextView price;
        ImageView image;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_str = itemView.findViewById(R.id.food_str);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
