package com.bwie.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.home.DetailsCommentsBeans;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;


public class DetailsRecyAdapter extends RecyclerView.Adapter<DetailsRecyAdapter.ViewHolder> {
    private Context mContext;
    private List<DetailsCommentsBeans.ResultBean> list;

    public DetailsRecyAdapter(Context mContext, List<DetailsCommentsBeans.ResultBean> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public int getItemViewType(int position) {
        String images = list.get(position).getImage().trim();
        String[] split = images.split(",");
        if (images.equals("url")) {
            return 0;
        } else if (split.length == 1) {
            return 1;
        } else if (split.length == 2) {
            return 2;
        } else if (split.length == 3) {
            return 3;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        if (i == 0) {
            view = View.inflate(mContext, R.layout.details_comments_item1, null);
        } else if (i == 1) {
            view = View.inflate(mContext, R.layout.details_comments_item2, null);
        } else if (i == 2) {
            view = View.inflate(mContext, R.layout.details_comments_item3, null);
        } else if (i == 3) {
            view = View.inflate(mContext, R.layout.details_comments_item4, null);
        }
        ViewHolder viewHolder = new ViewHolder(view, i);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String images = list.get(i).getImage().trim();
        String[] split = images.split(",");

        long createTime = (long) list.get(i).getCreateTime();
        java.sql.Date date010 = new java.sql.Date(createTime);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sd.format(date010);
        Log.e("WD", "createTime---" + format);
        viewHolder.details_comments_item_simpe.setImageURI(list.get(i).getHeadPic());

        viewHolder.details_comments_item_name.setText(list.get(i).getNickName());
        viewHolder.details_comments_item_date.setText(format + "");
        viewHolder.details_comments_item_data.setText(list.get(i).getContent());

        if (images.equals("url")) {
            return;
        } else if (split.length == 1) {
            viewHolder.details_comments_item_image_1.setImageURI(split[0]);
        } else if (split.length == 2) {
            viewHolder.details_comments_item_image_1.setImageURI(split[0]);
            viewHolder.details_comments_item_image_2.setImageURI(split[1]);
        } else if (split.length == 3) {
            viewHolder.details_comments_item_image_1.setImageURI(split[0]);
            viewHolder.details_comments_item_image_2.setImageURI(split[1]);
            viewHolder.details_comments_item_image_3.setImageURI(split[1]);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView details_comments_item_simpe, details_comments_item_image_1, details_comments_item_image_2, details_comments_item_image_3;
        TextView details_comments_item_name, details_comments_item_date, details_comments_item_data;

        public ViewHolder(@NonNull View itemView, int i) {
            super(itemView);
            if (i == 0) {
                details_comments_item_simpe = itemView.findViewById(R.id.details_comments_item_simpe);

                details_comments_item_name = itemView.findViewById(R.id.details_comments_item_name);
                details_comments_item_date = itemView.findViewById(R.id.details_comments_item_date);
                details_comments_item_data = itemView.findViewById(R.id.details_comments_item_data);
            } else if (i == 1) {
                details_comments_item_simpe = itemView.findViewById(R.id.details_comments_item_simpe);

                details_comments_item_name = itemView.findViewById(R.id.details_comments_item_name);
                details_comments_item_date = itemView.findViewById(R.id.details_comments_item_date);
                details_comments_item_data = itemView.findViewById(R.id.details_comments_item_data);

                details_comments_item_image_1 = itemView.findViewById(R.id.details_comments_item_image_1);
            } else if (i == 2) {
                details_comments_item_simpe = itemView.findViewById(R.id.details_comments_item_simpe);

                details_comments_item_name = itemView.findViewById(R.id.details_comments_item_name);
                details_comments_item_date = itemView.findViewById(R.id.details_comments_item_date);
                details_comments_item_data = itemView.findViewById(R.id.details_comments_item_data);

                details_comments_item_image_1 = itemView.findViewById(R.id.details_comments_item_image_1);
                details_comments_item_image_2 = itemView.findViewById(R.id.details_comments_item_image_2);
            } else if (i == 3) {
                details_comments_item_simpe = itemView.findViewById(R.id.details_comments_item_simpe);

                details_comments_item_name = itemView.findViewById(R.id.details_comments_item_name);
                details_comments_item_date = itemView.findViewById(R.id.details_comments_item_date);
                details_comments_item_data = itemView.findViewById(R.id.details_comments_item_data);

                details_comments_item_image_1 = itemView.findViewById(R.id.details_comments_item_image_1);
                details_comments_item_image_2 = itemView.findViewById(R.id.details_comments_item_image_2);
                details_comments_item_image_3 = itemView.findViewById(R.id.details_comments_item_image_3);
            }
        }
    }
}
