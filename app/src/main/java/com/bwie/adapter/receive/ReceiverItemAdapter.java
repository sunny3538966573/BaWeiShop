package com.bwie.adapter.receive;


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
 * date 2019/1/11
 * Describe:
 */
public class ReceiverItemAdapter extends RecyclerView.Adapter<ReceiverItemAdapter.ReceiverItemHolder> {
    private Context context;
    private List<AllOrder.DetailListBean> list;

    public ReceiverItemAdapter(Context context, List<AllOrder.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ReceiverItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.receive_item_layout, null);
        ReceiverItemHolder receiverItemHolder = new ReceiverItemHolder(view);
        return receiverItemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiverItemHolder holder, int position) {
        String commodityPic = list.get(position).getCommodityPic();
        String[] split = commodityPic.split("\\,");
        holder.receive_item_pic.setImageURI(split[1]);
        holder.receive_item_title.setText(list.get(position).getCommodityName());
        holder.receive_item_price.setText("¥:" + list.get(position).getCommodityPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ReceiverItemHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView receive_item_pic;
        private final TextView receive_item_title;
        private final TextView receive_item_price;

        public ReceiverItemHolder(View itemView) {
            super(itemView);
            receive_item_pic = itemView.findViewById(R.id.receive_item_pic);
            receive_item_title = itemView.findViewById(R.id.receive_item_title);
            receive_item_price = itemView.findViewById(R.id.receive_item_price);
        }

    }

}
