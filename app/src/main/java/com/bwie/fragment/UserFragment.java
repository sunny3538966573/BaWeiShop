package com.bwie.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bwie.actvity.MyAddressActivity;
import com.bwie.baweishop.R;
import com.bwie.actvity.ExitActivity;
import com.bwie.bean.login.Login;
import com.bwie.greendao.DaoMaster;
import com.bwie.greendao.DaoSession;
import com.bwie.greendao.LoginDao;
import com.bwie.actvity.AddressActivity;
import com.bwie.actvity.CircletActivity;
import com.bwie.actvity.FootActivity;
import com.bwie.actvity.InformationActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UserFragment extends Fragment {
    @BindView(R.id.fragment_my_txt_name)
    TextView fragmentMyTxtName;
    @BindView(R.id.fragment_my_txt_information)
    TextView fragmentMyTxtInformation;
    @BindView(R.id.fragment_my_txt_circlt)
    TextView fragmentMyTxtCirclt;
    @BindView(R.id.fragment_my_txt_foot)
    TextView fragmentMyTxtFoot;
    @BindView(R.id.fragment_my_txt_wallet)
    TextView fragmentMyTxtWallet;
    @BindView(R.id.fragment_my_txt_address)
    TextView fragmentMyTxtAddress;
    @BindView(R.id.fragment_my_sdv_image)
    SimpleDraweeView fragmentMySdvImage;
    @BindView(R.id.tuichu_linear)
    LinearLayout tuichuLinear;
    Unbinder unbinder;
    private LoginDao loginDao;
    private String headPic;
    private String nickName;
    private PopupWindow window;
    private View parent;
    private View contentView;
    private Button btn_pop_camera;
    private Button btn_pop_photo;
    private Button btn_pop_cancel;
    private String path = Environment.getExternalStorageDirectory()
            + "/a.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DaoSession daoSession = DaoMaster.newDevSession(getActivity(), LoginDao.TABLENAME);
        loginDao = daoSession.getLoginDao();
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        parent = View.inflate(getActivity(), R.layout.fragment_user, null);
        unbinder = ButterKnife.bind(this, view);
        contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_change_icon, null);
        btn_pop_camera = contentView.findViewById(R.id.btn_pop_camera);
        btn_pop_photo = contentView.findViewById(R.id.btn_pop_photo);
        btn_pop_cancel = contentView.findViewById(R.id.btn_pop_cancel);
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //问题：不能控制控件里的控件
        window.setFocusable(true);//设置窗口可以获取焦点
        window.setTouchable(true);//设置窗口可以触摸
        //不能点击空白处，控件退出
        window.setOutsideTouchable(true);//设置窗口外部可以触摸
        window.setBackgroundDrawable(new BitmapDrawable());
        initData();//数据库
        operateData();
        return view;

    }

    @OnClick(R.id.tuichu_linear)
    public void skip(){
        Intent intent = new Intent(getActivity(), ExitActivity.class);
        startActivity(intent);
        /*Toast.makeText(getActivity(), "退出登录", Toast.LENGTH_SHORT).show();
        DaoSession daoSession = DaoMaster.newDevSession(this, LoginDao.TABLENAME);
        loginDao = daoSession.getLoginDao();
        loginDao.delete(LOGIN_USER);
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/

    }
    private void operateData() {
        /*
         * 相机
         */
        btn_pop_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCamera();
                window.dismiss();//chuang口关闭
            }
        });
        /*
         * 相册
         */
        btn_pop_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getphoto();
                window.dismiss();//串口关闭
            }
        });
        /*
         * 取消
         */
        btn_pop_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();//串口关闭
            }
        });
    }

    private void getphoto() {
        // 通过隐式跳转方式打开相册
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        // 设置图片的类型
        intent.setType("image/*");
        // 需要回传值
        startActivityForResult(intent, 999);
    }

    private void getCamera() {
        // 通过隐式跳转打开相机拍照的页面
        Intent intent = new Intent();
        // 指定动作...拍照的动作 CAPTURE...捕获
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        // -------保存到sd卡
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
        // 拍照的目的是拿到拍的图片
        startActivityForResult(intent, 1000);

    }


    private void initData() {
        List<Login> logins = loginDao.loadAll();
        for (int i = 0; i < logins.size(); i++) {
            headPic = logins.get(i).getHeadPic();
            nickName = logins.get(i).getNickName();
        }

        fragmentMySdvImage.setImageURI(Uri.parse(headPic));
        fragmentMyTxtName.setText(nickName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fragment_my_txt_information, R.id.fragment_my_txt_circlt, R.id.fragment_my_txt_foot,
            R.id.fragment_my_txt_wallet, R.id.fragment_my_txt_address, R.id.fragment_my_sdv_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_my_txt_information:
                Intent intent = new Intent(getActivity(), InformationActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_txt_circlt:
                Intent intent1 = new Intent(getActivity(), CircletActivity.class);
                startActivity(intent1);
                break;
            case R.id.fragment_my_txt_foot:
                Intent intent2 = new Intent(getActivity(), FootActivity.class);
                startActivity(intent2);
                break;
            case R.id.fragment_my_txt_wallet:
                Intent intent3 = new Intent(getActivity(), MyAddressActivity.class);
                startActivity(intent3);
                break;
            case R.id.fragment_my_txt_address:
                Intent intent4 = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent4);
                break;
            case R.id.fragment_my_sdv_image://点击头像，弹出相机相册，改变头像
                // window.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
                getphoto();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000 && resultCode == getActivity().RESULT_OK) {
            // 可以拿回数据
            //Bitmap bitmap = data.getParcelableExtra("data");
            //img_view.setImageBitmap(bitmap);
            fragmentMySdvImage.setImageURI(Uri.fromFile(new File(path)));
        }
        if (requestCode == 999 && resultCode == getActivity().RESULT_OK) {
            // 取出相册的图片
            Uri uri = data.getData();
            fragmentMySdvImage.setImageURI(uri);
        }

    }

}
