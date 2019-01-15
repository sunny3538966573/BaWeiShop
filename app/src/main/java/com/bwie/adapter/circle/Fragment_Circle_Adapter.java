package com.bwie.adapter.circle;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.circle.Circle;
import com.bwie.utils.recyclerview.RecyclerGridView;
import com.bwie.utils.recyclerview.StringUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 *
 */
public class Fragment_Circle_Adapter extends RecyclerView.Adapter<Fragment_Circle_Adapter.ViewHolder> {
    public static final String FORMAT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private Context context;
    private List<Circle> list;

    public Fragment_Circle_Adapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fragment_circle_recy, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getHeadPic());
        holder.fragmentCircleSdvImage.setImageURI(uri);
        holder.fragmentCircleTxtName.setText(list.get(position).getNickName());
        //转换成日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME_PATTERN,Locale.getDefault());
        holder.fragmentCircleTxtDate.setText(dateFormat.format(list.get(position).getCreateTime()));
        holder.fragmentCircleTxtContent.setText(list.get(position).getContent());
        holder.fragmentCircleTxtZanNum.setText(""+list.get(position).getGreatNum());
        holder.fragmentCircleTxtZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Animation animation = AnimationUtils.loadAnimation(context, R.anim.add_score_anim);
                holder.fragmentCircleTxtZanNum.setVisibility(View.VISIBLE);
                holder.fragmentCircleTxtZanNum.startAnimation(animation);
                holder.fragmentCircleTxtZan.setBackgroundResource(R.drawable.zans);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        holder.fragmentCircleTxtZanNum.setVisibility(View.GONE);
                    }
                }, 1000);
            }
        });

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

    public void addAll(List<Circle> result) {
        if (result !=null){
            list.addAll(result);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView fragmentCircleSdvImage;
        private final TextView fragmentCircleTxtName;
        private final TextView fragmentCircleTxtDate;
        private final TextView fragmentCircleTxtContent;
        private final ImageAdapter imageAdapter;
        private final RecyclerGridView gridView;
        private final TextView fragmentCircleTxtZan;
        private final TextView fragmentCircleTxtZanNum;

        public ViewHolder(View itemView) {
            super(itemView);
            fragmentCircleSdvImage = itemView.findViewById(R.id.fragment_circle_sdv_image);
            fragmentCircleTxtName = itemView.findViewById(R.id.fragment_circle_txt_name);
            fragmentCircleTxtDate = itemView.findViewById(R.id.fragment_circle_txt_date);
            fragmentCircleTxtContent = itemView.findViewById(R.id.fragment_circle_txt_content);
            fragmentCircleTxtZan = itemView.findViewById(R.id.fragment_circle_txt_zan);
            fragmentCircleTxtZanNum = itemView.findViewById(R.id.fragment_circle_txt_zannum);
            gridView = itemView.findViewById(R.id.grid_view);
            imageAdapter = new ImageAdapter();
            int space = context.getResources().getDimensionPixelSize(R.dimen.dp_10);;//图片间距
            gridView.setHorizontalSpacing(space);
            gridView.setVerticalSpacing(space);
            gridView.setAdapter(imageAdapter);
        }
    }
}
