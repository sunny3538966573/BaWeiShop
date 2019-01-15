package com.bwie.adapter.money;

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
 * date 2019/1/10
 * Describe:适配器
 */
public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.MoneyHolder> {
    private Context context;
    private List<AllOrder> list;

    public MoneyAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void addAll(List<AllOrder> orderList) {
        if (orderList !=null){
            list.addAll(orderList);
        }
    }

    @NonNull
    @Override
    public MoneyAdapter.MoneyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.money_layout, null);
        MoneyHolder moneyHolder = new MoneyHolder(inflate);
        return moneyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyAdapter.MoneyHolder holder, int position) {
        holder.money_num.setText("共"+list.get(0).getDetailList().size()+"件商品，需支付"+list.get(position).getPayAmount());
        holder.money_recy.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        //设置子条目
        List<AllOrder.DetailListBean> detailList = list.get(0).getDetailList();
        MoneyItemAdapter moneyItemAdapter = new MoneyItemAdapter(context, detailList);
        holder.money_recy.setAdapter(moneyItemAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MoneyHolder extends RecyclerView.ViewHolder{

        private final RecyclerView money_recy;
        private final TextView money_num;

        public MoneyHolder(View itemView) {
            super(itemView);
            money_recy = itemView.findViewById(R.id.money_recy);
            money_num = itemView.findViewById(R.id.money_num);
        }
    }
}
