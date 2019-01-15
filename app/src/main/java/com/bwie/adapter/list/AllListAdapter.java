package com.bwie.adapter.list;

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

public class AllListAdapter extends RecyclerView.Adapter<AllListAdapter.ViewHolder> {
    private Context context;
    private List<AllOrder> list;
    private ListAdapter orderAdapter;

    public AllListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.allorder_recy_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.allOrderTxtSum.setText("共"+list.get(0).getDetailList().size()+"件商品，需支付"+list.get(position).getPayAmount());
        //布局管理器
        holder.allOrderRecyItem.setLayoutManager(new LinearLayoutManager(context));
        //设置子条目
        List<AllOrder.DetailListBean> detailList = list.get(0).getDetailList();
        orderAdapter = new ListAdapter(context,detailList);
        holder.allOrderRecyItem.setAdapter(orderAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void AddAll(List<AllOrder> orderList) {
        if (orderList!=null){
            list.addAll(orderList);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView allOrderRecyItem;
        private final TextView allOrderTxtSum;
        public ViewHolder(View itemView) {
            super(itemView);

            allOrderRecyItem = itemView.findViewById(R.id.allorder_recy_item);
            allOrderTxtSum = itemView.findViewById(R.id.allorder_txt_sum);

        }
    }
}
