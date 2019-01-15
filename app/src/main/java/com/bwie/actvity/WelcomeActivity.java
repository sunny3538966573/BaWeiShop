package com.bwie.actvity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.bean.login.Login;
import com.bwie.greendao.DaoMaster;
import com.bwie.greendao.DaoSession;
import com.bwie.greendao.LoginDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.btn_goto_main)
    TextView btn_goto_main;
    @BindView(R.id.txt_time)
    TextView txtTime;

    int time = 5;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                if (time == 0){
                    List<Login> logins = dao.loadAll();
                    for (int i = 0; i < logins.size(); i++) {
                        int statues = logins.get(i).getStatues();
                        if (statues == 1){
                            startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                            finish();
                            return;
                        }
                    }
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    finish();
                    return;
                }else{
                    time--;
                    txtTime.setText(time+"s");
                    handler.sendEmptyMessageDelayed(1,1000);
                }
            }
        }
    };
    private LoginDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //查询
        DaoSession daoSession = DaoMaster.newDevSession(WelcomeActivity.this, LoginDao.TABLENAME);
        dao = daoSession.getLoginDao();
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Login> loginList = dao.loadAll();
                for (int i = 0; i < loginList.size(); i++) {
                    int statues = loginList.get(i).getStatues();
                    if (statues == 1){
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                        finish();
                        return;
                    }
                }
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                finish();
            }
        });
        //发送线程
        handler.sendEmptyMessageDelayed(1,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }
    @OnClick(R.id.btn_goto_main)
    public void onViewClicked() {
        List<Login> loginList = dao.loadAll();
        for (int i = 0; i < loginList.size(); i++) {
            int statues = loginList.get(i).getStatues();
            if (statues == 1){
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
                return;
            }
        }
        startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
        finish();
    }
}
