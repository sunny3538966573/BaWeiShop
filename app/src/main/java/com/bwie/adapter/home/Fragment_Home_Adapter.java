package com.bwie.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.home.homeshop.Commodity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Home_Adapter extends RecyclerView.Adapter<Fragment_Home_Adapter.ViewHolder> {
    private Context context;
    private List<Commodity> list;
    private int type;
    public static final int HOT_TYPE = 0;
    public static final int FASHION_TYPE = 1;
    public static final int LIVE_TYPE = 2;

    public Fragment_Home_Adapter(Context context, int type) {
        this.context = context;
        this.type = type;
        list = new ArrayList<>();
    }

    public void addAll(List<Commodity> commodityList) {
        if (commodityList !=null){
            list.addAll(commodityList);
        }
    }

    /**
     * 接口回调
     */
    public interface OnitemClickListener{
        void onItemClick(int position);
    }
    public OnitemClickListener mOnitemClickListener;
    public void setOnitemClickListener(OnitemClickListener onitemClickListener){
        mOnitemClickListener = onitemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (type ==HOT_TYPE){
            View view = View.inflate(context, R.layout.fragment_home_rxxp, null);
            return new ViewHolder(view);
        }else if (type ==FASHION_TYPE){
            View view = View.inflate(context, R.layout.fragment_home_mlss, null);
            return new ViewHolder(view);
        }else {
            View view = View.inflate(context, R.layout.fragment_home_pzsh, null);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.fragmentHomeSdvImage.setImageURI(Uri.parse(list.get(position).getMasterPic()));
        holder.fragmentHomeTxtTitle.setText(list.get(position).getCommodityName());
        holder.fragmentHomeTxtPrice.setText("¥:"+list.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int commodityId = list.get(position).getCommodityId();
                if (mOnitemClickListener !=null){
                    mOnitemClickListener.onItemClick(commodityId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView fragmentHomeSdvImage;
        private final TextView fragmentHomeTxtTitle;
        private final TextView fragmentHomeTxtPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            fragmentHomeSdvImage = itemView.findViewById(R.id.fragment_home_sdv_image);
            fragmentHomeTxtTitle = itemView.findViewById(R.id.fragment_home_txt_title);
            fragmentHomeTxtPrice = itemView.findViewById(R.id.fragment_home_txt_price);
        }
    }
}
