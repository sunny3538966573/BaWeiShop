package com.bwie.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.home.Querys;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class Query_Goods_Adapter extends RecyclerView.Adapter<Query_Goods_Adapter.ViewHolder> {
    private Context context;
    private List<Querys.ResultBean> list;

    public Query_Goods_Adapter(Context context, List<Querys.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 创建接口回调，点击进入详情页面
     */
    public interface OnItemQueryClickListener{
        void OnItemClick(View itemview,int position);
    }
    public OnItemQueryClickListener mOnItemQueryClickListener;

    public void setOnItemQueryClickListener(OnItemQueryClickListener onItemQueryClickListener){
        mOnItemQueryClickListener = onItemQueryClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.query_goods_recy, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getMasterPic());
        holder.queryGoodsSdvImage.setImageURI(uri);
        holder.queryGoodsTxtTitle.setText(list.get(position).getCommodityName());
        holder.queryGoodsTxtPrice.setText("￥："+list.get(position).getPrice());
        holder.queryGoodsTxtNum.setText("已售"+list.get(position).getSaleNum()+"件");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemQueryClickListener !=null){
                    mOnItemQueryClickListener.OnItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView queryGoodsSdvImage;
        private final TextView queryGoodsTxtTitle;
        private final TextView queryGoodsTxtPrice;
        private final TextView queryGoodsTxtNum;

        public ViewHolder(View itemView) {
            super(itemView);
            queryGoodsSdvImage = itemView.findViewById(R.id.query_goods_sdv_image);
            queryGoodsTxtTitle = itemView.findViewById(R.id.query_goods_txt_title);
            queryGoodsTxtPrice = itemView.findViewById(R.id.query_goods_txt_price);
            queryGoodsTxtNum = itemView.findViewById(R.id.query_goods_txt_num);
        }
    }
}
