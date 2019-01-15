package com.bwie.actvity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.baweishop.R;
import com.bwie.utils.core.IRequest;
import com.bwie.bean.login.ILogin;
import com.bwie.bean.login.Login;

import com.bwie.utils.login.BaseLoginActvity;
import com.bwie.utils.login.LoginOkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisteActivity extends BaseLoginActvity {

    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_yanzheng)
    EditText registerYanzheng;
    @BindView(R.id.register_huoqu)
    TextView registerHuoqu;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.register_eye_check)
    ImageView registerEyeCheck;
    @BindView(R.id.register_login)
    TextView registerLogin;
    @BindView(R.id.register_zhuce)
    Button registerZhuce;
    private String phone;
    private String password;


    @Override
    public void initData() {
        registerPassword.setInputType(129);

    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    public int getcontentview() {
        return R.layout.activity_registe;
    }

    @OnClick({R.id.register_eye_check, R.id.register_login, R.id.register_zhuce,R.id.register_huoqu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_huoqu:
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(this, "发送成功，请在手机上查收", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_eye_check:
                //判断密码类型
                if (registerPassword.getInputType() ==129){
                    registerPassword.setInputType(127);
                }else {
                    registerPassword.setInputType(129);
                }
                break;
            case R.id.register_login:
                Intent intent = new Intent(RegisteActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.register_zhuce:
                phone = registerPhone.getText().toString().trim();
                password = registerPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phone) && TextUtils.isEmpty(password)){
                    Toast.makeText(this, "手机号与密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    IRequest apiService = LoginOkHttpUtils.getInstance().create(IRequest.class);
                    apiService.getregister(phone,password)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ILogin<Login>>() {
                                @Override
                                public void accept(ILogin<Login> loginILogin) throws Exception {
                                    if (loginILogin.getStatus().equals("1001")){
                                        Toast.makeText(RegisteActivity.this, "该手机号已注册，不能重复注册!", Toast.LENGTH_SHORT).show();

                                    }else if (loginILogin.getStatus().equals("0000")){
                                        Toast.makeText(RegisteActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(RegisteActivity.this, "注册失败!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                break;
        }
    }
}
