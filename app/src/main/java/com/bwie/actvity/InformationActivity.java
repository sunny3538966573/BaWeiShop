package com.bwie.actvity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.login.Login;
import com.bwie.greendao.DaoMaster;
import com.bwie.greendao.DaoSession;
import com.bwie.greendao.LoginDao;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InformationActivity extends AppCompatActivity {

    @BindView(R.id.information_icon)
    SimpleDraweeView icon;
    @BindView(R.id.information_name)
    TextView name;
    @BindView(R.id.information_pwd)
    TextView pwd;
    private LoginDao loginDao;
    private String headPic;
    private String nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaoSession daoSession = DaoMaster.newDevSession(InformationActivity.this, LoginDao.TABLENAME);
        loginDao = daoSession.getLoginDao();
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        initData();
    }
    @OnClick(R.id.information_icon)
    public void onViewClicked() {

    }
    private void initData() {
        List<Login> logins = loginDao.loadAll();
        for (int i = 0; i < logins.size(); i++) {
            headPic = logins.get(i).getHeadPic();
            nickName = logins.get(i).getNickName();
        }
        icon.setImageURI(Uri.parse(headPic));
        name.setText(nickName);
    }
}
