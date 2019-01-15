package com.bwie.actvity;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.baweishop.R;
import com.bwie.utils.core.IRequest;
import com.bwie.bean.login.ILogin;
import com.bwie.bean.login.Login;
import com.bwie.greendao.DaoMaster;
import com.bwie.greendao.DaoSession;
import com.bwie.greendao.LoginDao;

import com.bwie.utils.login.BaseLoginActvity;
import com.bwie.utils.login.LoginOkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseLoginActvity {


    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_eye_check)
    ImageView loginEyeCheck;
    @BindView(R.id.login_jizhu)
    CheckBox loginJizhu;
    @BindView(R.id.login_zhuce)
    TextView loginZhuce;
    @BindView(R.id.login_button)
    Button loginButton;
    private String username;
    private String password;
    private boolean showPassword = true;//显示密码

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
//        loginPassword.setInputType(129);

    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    /**
     * 初始化布局
     * @return
     */
    @Override
    public int getcontentview() {
        return R.layout.activity_login;

    }

    @OnClick({R.id.login_eye_check, R.id.login_jizhu, R.id.login_zhuce, R.id.login_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_eye_check:
//                //判断密码类型
//                if (loginPassword.getInputType() ==129){
//                    loginPassword.setInputType(127);
//                }else {
//                    loginPassword.setInputType(129);
//                }
                if (showPassword==true){
                    loginEyeCheck.setImageResource(R.drawable.yj);
                    HideReturnsTransformationMethod method1=HideReturnsTransformationMethod.getInstance();
                    loginPassword.setTransformationMethod(method1);
                    showPassword = !showPassword;
                }else {
                    loginEyeCheck.setImageResource(R.drawable.biyan);
                    TransformationMethod method=PasswordTransformationMethod.getInstance();
                    loginPassword.setTransformationMethod(method);
                    showPassword = !showPassword;
                }
                int index=loginPassword.getText().toString().length();
                loginPassword.setSelection(index);
                break;
            case R.id.login_jizhu:

                break;
            case R.id.login_zhuce:
                Intent intent = new Intent(LoginActivity.this,RegisteActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button:
                username = loginPhone.getText().toString().trim();
                password = loginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)&& TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();

                } else {
                    IRequest apiService = LoginOkHttpUtils.getInstance().create(IRequest.class);
                    apiService.getlogin(username,password)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ILogin<Login>>() {
                                @Override
                                public void accept(ILogin<Login> loginILogin) throws Exception {
                                    if (loginILogin.getStatus().equals("0000")){
                                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        DaoSession daoSession = DaoMaster.newDevSession(LoginActivity.this, LoginDao.TABLENAME);
                                        LoginDao loginDao = daoSession.getLoginDao();
                                        loginILogin.getResult().setStatues(1);
                                        loginDao.insertOrReplace(loginILogin.getResult());
                                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }

                break;
        }
    }
}