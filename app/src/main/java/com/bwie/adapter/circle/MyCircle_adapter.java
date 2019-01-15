package com.bwie.adapter.circle;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.circle.MyCircle;
import com.bwie.utils.recyclerview.RecyclerGridView;
import com.bwie.utils.recyclerview.StringUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * date: 2019/1/5.
 * Created by Administrator
 * function:
 */
public class MyCircle_adapter extends RecyclerView.Adapter<MyCircle_adapter.ViewHolder> {
    public static final String FORMAT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private Context context;
    private List<MyCircle> list;

    public MyCircle_adapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.mycircle_recy_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myCircleSdvImage1.setImageURI(Uri.parse(list.get(position).getHeadPic()));
        holder.myCircleTxtName.setText(list.get(position).getNickName());
        //转换成日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME_PATTERN,Locale.getDefault());
        holder.myCircleTxtDate.setText(dateFormat.format(list.get(position).getCreateTime()));
        holder.myCircleTxtContent.setText(list.get(position).getContent());
        holder.myCircleTxtZannum.setText(""+list.get(position).getGreatNum());
        if (StringUtils.isEmpty(list.get(position).getImage())){
            holder.gridView.setVisibility(View.GONE);
        }else{
            holder.gridView.setVisibility(View.VISIBLE);
            String[] images = list.get(position).getImage().split(",");
            int colNum;//列数
            if (images.length == 1){
                colNum = 1;
            }else if (images.length == 2||images.length == 4){
                colNum = 2;
            }else {
                colNum = 3;
            }
            holder.gridView.setNumColumns(colNum);
            holder.imageAdapter.clear();
            holder.imageAdapter.addAll(Arrays.asList(images));
            holder.imageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<MyCircle> result) {
        if (result !=null){
            list.addAll(result);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView myCircleSdvImage1;
        private final TextView myCircleTxtName;
        private final TextView myCircleTxtDate;
        private final TextView myCircleTxtContent;
        private final TextView myCircleTxtZan;
        private final TextView myCircleTxtZannum;
        private final RecyclerGridView gridView;
        private final ImageAdapter imageAdapter;

        public ViewHolder(View itemView) {
            super(itemView);
            myCircleSdvImage1 = itemView.findViewById(R.id.mycircle_sdv_image);
            myCircleTxtName = itemView.findViewById(R.id.mycircle_txt_name);
            myCircleTxtDate = itemView.findViewById(R.id.mycircle_txt_date);
            myCircleTxtContent = itemView.findViewById(R.id.mycircle_txt_content);
            myCircleTxtZan = itemView.findViewById(R.id.mycircle_txt_zan);
            myCircleTxtZannum = itemView.findViewById(R.id.mycircle_txt_zannum);
            gridView = itemView.findViewById(R.id.grid_view);
            imageAdapter = new ImageAdapter();
            int space = context.getResources().getDimensionPixelSize(R.dimen.dp_10);;//图片间距
            gridView.setHorizontalSpacing(space);
            gridView.setVerticalSpacing(space);
            gridView.setAdapter(imageAdapter);
        }
    }
}
