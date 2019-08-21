package com.example.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.shop.MainActivity;
import com.example.shop.R;
import com.example.shop.ShopActivity;
import com.example.shop.bean.DataBean;
import com.example.shop.bean.NumBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/1910:45
 * desc   :
 * package: Shop:
 */
public class ShopVpter extends XRecyclerView.Adapter<ShopVpter.ViewHolder> {
    private Context context;
    private ArrayList<DataBean> list = new ArrayList<>();
    private ArrayList<NumBean> num = new ArrayList<>();

    public ShopVpter(Context context) {
        this.context = context;
    }

    public void AllCheck() {
        notifyDataSetChanged();
    }

    public void addData(List<DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_show_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.price.setText(list.get(i).getNum() + "");
        viewHolder.food.setText(list.get(i).getFood_str());
        Glide.with(context).load(list.get(i).getPic()).into(viewHolder.url);
        for (int j = 0; j < list.size(); j++) {
            num.add(new NumBean(1, false));
        }
        viewHolder.tv_num.setText(num.get(i).getTag() + "");
        final Integer integer = new Integer(i);
        if (ShopActivity.hashMap.containsKey(integer)) {
            viewHolder.checkbox.setChecked(true);
        } else {
            viewHolder.checkbox.setChecked(false);
        }
        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.checkbox.isChecked()) {
                    ShopActivity.hashMap.put(integer, true);

                } else {
                    ShopActivity.hashMap.remove(integer);
                }
                allOnclick.OnItem(v, i, num, list, list.get(i));
                notifyDataSetChanged();
            }
        });
        viewHolder.tv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num.get(i).getTag() == 1) {
                    return;
                } else {
                    int tag = num.get(i).getTag();
                    tag -= 1;
                    viewHolder.tv_num.setText(tag + "");
                    num.get(i).setTag(tag);
                    notifyDataSetChanged();
                }
            }
        });
        viewHolder.tv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = num.get(i).getTag();
                tag += 1;
                viewHolder.tv_num.setText(tag + "");
                num.get(i).setTag(tag);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView food, price, tv_reduce, tv_plus, tv_num;
        ImageView url;
        CheckBox checkbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
            url = itemView.findViewById(R.id.image);
            tv_plus = itemView.findViewById(R.id.tv_plus);
            tv_reduce = itemView.findViewById(R.id.tv_reduce);
            tv_num = itemView.findViewById(R.id.tv_num);
            checkbox = itemView.findViewById(R.id.checkbox);

        }
    }

    public AllOnclick allOnclick;

    public void setAllOnclick(AllOnclick allOnclick) {
        this.allOnclick = allOnclick;
    }

    public interface AllOnclick {
        void OnItem(View view, int position, ArrayList<NumBean> num, ArrayList<DataBean> list, DataBean dataBean);
    }
}
