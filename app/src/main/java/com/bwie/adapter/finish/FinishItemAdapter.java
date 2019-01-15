package com.bwie.adapter.finish;


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
public class FinishItemAdapter extends RecyclerView.Adapter<FinishItemAdapter.ReceiverItemHolder> {
    private Context context;
    private List<AllOrder.DetailListBean> list;

    public FinishItemAdapter(Context context, List<AllOrder.DetailListBean> list) {
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
        holder.finish_item_pic.setImageURI(split[1]);
        holder.finish_item_title.setText(list.get(position).getCommodityName());
        holder.finish_item_price.setText("¥:" + list.get(position).getCommodityPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ReceiverItemHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView finish_item_pic;
        private final TextView finish_item_title;
        private final TextView finish_item_price;

        public ReceiverItemHolder(View itemView) {
            super(itemView);
            finish_item_pic = itemView.findViewById(R.id.finish_item_pic);
            finish_item_title = itemView.findViewById(R.id.finish_item_title);
            finish_item_price = itemView.findViewById(R.id.finish_item_price);
        }

    }

}
