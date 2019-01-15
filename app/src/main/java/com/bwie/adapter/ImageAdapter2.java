package com.bwie.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.actvity.ImgActivity;
import com.bwie.baweishop.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${LinJiangtao}
 * on 2019/1/12
 */
public class ImageAdapter2 extends RecyclerView.Adapter<ImageAdapter2.MyHodler>{

    FragmentActivity content;

    public ImageAdapter2(FragmentActivity content) {
        this.content = content;
    }

    private List<Object> mList = new ArrayList<>();
    private int sign;//0:普通点击，1自定义

    public void addAll(List<Object> list) {
        mList.addAll(list);
    }

    public void setSign(int sign){
        this.sign = sign;
    }

    @NonNull
    @Override
    public MyHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.img_layout, null);
        return new MyHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodler myHodler, final int i) {
        if (mList.get(i) instanceof String) {
            String imageUrl = (String) mList.get(i);
            if (imageUrl.contains("http:")) {//加载http
                myHodler.image.setImageURI(Uri.parse(imageUrl));
            } else {//加载sd卡
                Uri uri = Uri.parse("file://" + imageUrl);
                myHodler.image.setImageURI(uri);
            }
        } else {//加载资源文件
            int id = (int) mList.get(i);
            Uri uri = Uri.parse("res://com.dingtao.rrmmp/" + id);
            myHodler.image.setImageURI(uri);
        }

        myHodler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sign == 1) {//自定义点击
                    if (i == 0) {
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        content.startActivityForResult(intent, ImgActivity.PHOTO);
                    } else {
                        //UIUtils.showToastSafe("点击了图片");
                    }
                }else{
                   // UIUtils.showToastSafe("点击了图片");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void add(Object image) {
        if (image!= null) {
            mList.add(image);
        }
    }
    public List<Object> getList() {
        return mList;
    }


    public class MyHodler extends RecyclerView.ViewHolder {
        SimpleDraweeView image;

        public MyHodler(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circle_image);
        }
    }
}
