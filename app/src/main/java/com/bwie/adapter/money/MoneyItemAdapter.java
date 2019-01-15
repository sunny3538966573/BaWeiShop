package com.bwie.adapter.money;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.order.AllOrder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 1607c王晴
 * date 2019/1/10
 * Describe:
 */
public class MoneyItemAdapter extends RecyclerView.Adapter<MoneyItemAdapter.ViewHolder> {
    private Context context;
    private List<AllOrder.DetailListBean> list;

    public MoneyItemAdapter(Context context, List<AllOrder.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.money_item_layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String commodityPic = list.get(position).getCommodityPic();
        String[] split = commodityPic.split("\\,");
        holder.money_item_pic.setImageURI(split[1]);
        //viewHolder.orderSdvImage.setImageURI(split[1]);
        //viewHolder.orderSdvImage.setImageResource(R.drawable.icon);
        //Glide.with(context).load(list.get(i).getCommodityPic()).into(viewHolder.orderSdvImage);
        holder.money_item_title.setText(list.get(position).getCommodityName());
        holder.money_item_price.setText("¥:" + list.get(position).getCommodityPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView money_item_pic;
        private final TextView money_item_title;
        private final TextView money_item_price;

        public ViewHolder(View itemView) {
            super(itemView);
            money_item_pic = itemView.findViewById(R.id.money_item_pic);
            money_item_title = itemView.findViewById(R.id.money_item_title);
            money_item_price= itemView.findViewById(R.id.money_item_price);
        }
    }
}
