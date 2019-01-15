package com.bwie.adapter.common;

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
 * date 2019/1/13
 * Describe:
 */
public class CommonItemAdapter extends RecyclerView.Adapter<CommonItemAdapter.CommonItemHolder> {
    private Context context;
    private List<AllOrder.DetailListBean> list;

    public CommonItemAdapter(Context context, List<AllOrder.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CommonItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.common_item_layout, null);
        CommonItemHolder commonItemHolder = new CommonItemHolder(view);
        return commonItemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonItemHolder holder, int position) {
        String commodityPic = list.get(position).getCommodityPic();
        String[] split = commodityPic.split("\\,");
        holder.common_item_sd.setImageURI(split[1]);
        holder.common_item_title.setText(list.get(position).getCommodityName());
        holder.common_item_price.setText("¥:"+list.get(position).getCommodityPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CommonItemHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView common_item_sd;
        private final TextView common_item_title;
        private final TextView common_item_price;

        public CommonItemHolder(View itemView) {
            super(itemView);
            common_item_sd = itemView.findViewById(R.id.common_item_sd);
            common_item_title = itemView.findViewById(R.id.common_item_title);
            common_item_price = itemView.findViewById(R.id.common_item_price);
        }
    }
}
