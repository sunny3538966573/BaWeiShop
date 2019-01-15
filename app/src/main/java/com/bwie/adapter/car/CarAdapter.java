package com.bwie.adapter.car;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.cart.Cart;
import com.bwie.bean.cart.CheckBoxs;
import com.bwie.view.MyAddSubView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private Context context;
    private List<Cart> list;
    private List<CheckBoxs> checkBoxes;

    public CarAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        checkBoxes = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.cart_recy_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
    holder.cartRecySdvImage.setImageURI(Uri.parse(list.get(position).getPic()));
    holder.cartRecyTxtTitle.setText(list.get(position).getCommodityName());
    holder.cartRecyTxtPrice.setText("¥:"+list.get(position).getPrice());
    holder.cartRecySubAddView.setNumber(list.get(position).getCount());
    //点击复选框
    holder.cartRecyCb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnCartListener !=null){
                boolean checked = holder.cartRecyCb.isChecked();
                checkBoxes.get(position).setCheck(checked);
                mOnCartListener.GoodsChange();
            }
        }
    });
    //加减器点击
    holder.cartRecySubAddView.setOnNumberChangeListener(new MyAddSubView.OnNumberChangeListener() {
        @Override
        public void onNumberChange(int num) {
            if (mOnCartListener !=null){
                mOnCartListener.onCartNumchanged(position,num);
            }
        }
    });
    }

    @Override
    public int getItemCount() {
        if (list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                checkBoxes.add(new CheckBoxs(false));
                if (checkBoxes.size() == list.size()){
                    break;
                }
            }
        }
        return list.size();
    }
    public void setData(List<Cart> result) {
        if (result !=null){
            list = result;
            notifyDataSetChanged();
        }
    }

    public void addAll(List<Cart> result) {
        if (result !=null){
            list.addAll(result);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cartRecyCb;
        private final SimpleDraweeView cartRecySdvImage;
        private final TextView cartRecyTxtTitle;
        private final TextView cartRecyTxtPrice;
        private final MyAddSubView cartRecySubAddView;

        public ViewHolder(View itemView) {
            super(itemView);
            cartRecyCb = itemView.findViewById(R.id.cart_recy_cb);
            cartRecySdvImage = itemView.findViewById(R.id.cart_recy_sdv_image);
            cartRecyTxtTitle = itemView.findViewById(R.id.cart_recy_txt_title);
            cartRecyTxtPrice = itemView.findViewById(R.id.cart_recy_txt_price);
            cartRecySubAddView = itemView.findViewById(R.id.cart_recy_subaddview);
        }
    }

    /**
     * 选中商品的数量
     * @return
     */
    public int setshopsum(){
        int shopsum = 0;
        for (int i = 0; i < list.size(); i++) {
          if (checkBoxes.get(i).isCheck() ==true){
              shopsum+=list.get(i).getCount();
          }
        }
        return shopsum;
    }

    /**
     * 计算商品总价
     * @return
     */
    public float setsumprice(){
       float sumprice = 0.0f;
        for (int i = 0; i < list.size(); i++) {
            if (checkBoxes.get(i).isCheck() ==true){
                int count = list.get(i).getCount();
                int price = list.get(i).getPrice();
                sumprice+=count*price;
            }
        }
        return sumprice;
    }

    /**
     * 改变商品数量
     * @param index
     * @param num
     */
    public void changeshopnum(int index,int num){
        list.get(index).setCount(num);
    }

    /**
     * 所有商品选中
     * @param b
     */
    public void setAllshopselected(boolean b){
        for (int i = 0; i < list.size(); i++) {
            checkBoxes.get(i).setCheck(b?true:false);
        }
    }

    /**
     * 判断是否所有所有商品都选中
     * @return
     */
    public boolean isAllshopselected(){
        for (int i = 0; i < list.size(); i++) {
            if (checkBoxes.get(i).isCheck() ==false){
                return false;
            }
        }
        return true;
    }

    /**
     * 是否所有商品都被选中
     * @return
     */
    public boolean isAllshopselecteds(){
        mOnCartListener.GoodsChange();
        for (int i = 0; i < list.size(); i++) {
            if (checkBoxes.get(i).isCheck() ==false){
                return false;
            }
        }
        return true;
    }


    /**
     * 定义接口回调
     */
    public interface OnCartListener{
        void GoodsChange();
        void onCartNumchanged(int index,int num);
    }
    public OnCartListener mOnCartListener;
    public void setOnCartListener(OnCartListener onCartListener){
        mOnCartListener = onCartListener;
    }
}
