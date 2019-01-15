package com.bwie.adapter.receive;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.order.AllOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1607c王晴
 * date 2019/1/11
 * Describe:
 */
public class ReceiverAdapter extends RecyclerView.Adapter<ReceiverAdapter.ReceiverHolder> {
    private Context context;
    private List<AllOrder> list;

    public ReceiverAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReceiverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.receive_layout, null);
        ReceiverHolder receiverHolder = new ReceiverHolder(view);
        return receiverHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiverHolder holder, int position) {
        holder.receive_number.setText("派件公司  "+list.get(0).getExpressCompName());
        holder.receive_order_id.setText("快递编号  "+list.get(0).getOrderId());
        holder.receive_adapter_recy.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        //设置子条目
        List<AllOrder.DetailListBean> detailList = list.get(0).getDetailList();
        ReceiverItemAdapter receiverItemAdapter = new ReceiverItemAdapter(context, detailList);
        holder.receive_adapter_recy.setAdapter(receiverItemAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<AllOrder> orderList) {
        if (orderList!=null){
            list.addAll(orderList);
        }
    }

    class ReceiverHolder extends RecyclerView.ViewHolder{

        private final RecyclerView receive_adapter_recy;
        private final TextView receive_number;
        private final TextView receive_order_id;

        public ReceiverHolder(View itemView) {
            super(itemView);
            receive_adapter_recy = itemView.findViewById(R.id.receive_adapter_recy);//recyclerview
            receive_number = itemView.findViewById(R.id.receive_number);//收获地址
            receive_order_id = itemView.findViewById(R.id.receive_order_id);
        }
    }
}
