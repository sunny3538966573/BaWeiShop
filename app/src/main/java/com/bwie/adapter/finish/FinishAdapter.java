package com.bwie.adapter.finish;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.adapter.common.CommonItemAdapter;
import com.bwie.bean.order.AllOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1607c王晴
 * date 2019/1/13
 * Describe:
 */
public class FinishAdapter extends RecyclerView.Adapter<FinishAdapter.CommonHolder> {
    private Context context;
    private List<AllOrder> list;

    public FinishAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    @NonNull
    @Override
    public CommonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.common_layout, null);
        CommonHolder commonHolder = new CommonHolder(view);
        return commonHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonHolder holder, int position) {
holder.finish_adapter_orderid.setText("订单号  "+list.get(0).getOrderId());
        holder.finish_adapter_recy.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        //设置子条目
        List<AllOrder.DetailListBean> detailList = list.get(0).getDetailList();
        FinishItemAdapter finishItemAdapter = new FinishItemAdapter(context, detailList);
        holder.finish_adapter_recy.setAdapter(finishItemAdapter);

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

    class CommonHolder extends RecyclerView.ViewHolder{


        private final TextView finish_adapter_orderid;
        private final RecyclerView finish_adapter_recy;

        public CommonHolder(View itemView) {
            super(itemView);
            finish_adapter_orderid = itemView.findViewById(R.id.finish_adapter_orderid);
            finish_adapter_recy = itemView.findViewById(R.id.finish_adapter_recy);
        }
    }
}
