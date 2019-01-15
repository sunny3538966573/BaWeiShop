package com.bwie.adapter.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.order.AllOrder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import io.reactivex.annotations.NonNull;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private List<AllOrder.DetailListBean> list;

    public ListAdapter(Context context, List<AllOrder.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.oder_recy_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //Uri uri = Uri.parse(list.get(i).getCommodityPic());
        /**
         * 加载图片
         */
        String commodityPic = list.get(i).getCommodityPic();
        String[] split = commodityPic.split("\\,");
        viewHolder.orderSdvImage.setImageURI(split[1]);
        //viewHolder.orderSdvImage.setImageResource(R.drawable.icon);
        //Glide.with(context).load(list.get(i).getCommodityPic()).into(viewHolder.orderSdvImage);
        viewHolder.oederTxtTitle.setText(list.get(i).getCommodityName());
        viewHolder.oederTxtPrice.setText("¥:" + list.get(i).getCommodityPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView orderSdvImage;
        private final TextView oederTxtTitle;
        private final TextView oederTxtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderSdvImage = itemView.findViewById(R.id.order_sdv_image);
            oederTxtTitle = itemView.findViewById(R.id.order_txt_title);
            oederTxtPrice = itemView.findViewById(R.id.order_txt_price);
        }
    }


}