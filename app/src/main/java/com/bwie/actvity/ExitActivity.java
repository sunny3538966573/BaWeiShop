package com.bwie.actvity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bwie.baweishop.R;
import com.bwie.greendao.DaoMaster;
import com.bwie.greendao.DaoSession;
import com.bwie.greendao.LoginDao;
import com.bwie.utils.http.WDActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExitActivity extends WDActivity {


    @BindView(R.id.btn_tuichu)
    Button btnTuichu;
    @BindView(R.id.btn_restart_login)
    Button btnRestartLogin;
    private LoginDao loginDao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exit;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }



    @OnClick({R.id.btn_tuichu, R.id.btn_restart_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tuichu:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btn_restart_login:
                DaoSession daoSession = DaoMaster.newDevSession(this, LoginDao.TABLENAME);
                loginDao = daoSession.getLoginDao();
                loginDao.delete(LOGIN_USER);
                Intent intent = new Intent(this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;
        }
    }
}
