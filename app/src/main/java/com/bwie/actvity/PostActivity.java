package com.bwie.actvity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.adapter.ImageAdapter2;
import com.bwie.base.BaseActivity;
import com.bwie.baweishop.R;
import com.bwie.bean.Result;
import com.bwie.presenter.PostPresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.recyclerview.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostActivity extends BaseActivity {


    @BindView(R.id.post_back)
    ImageView postBack;
    @BindView(R.id.post_plane)
    TextView postPlane;
    @BindView(R.id.post_edit)
    EditText postEdit;
    @BindView(R.id.post_recy)
    RecyclerView postRecy;
    private PostPresenter postPresenter;
    private ImageAdapter2 imageAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        postPresenter = new PostPresenter(new postCall());
        imageAdapter2 = new ImageAdapter2(this);
        imageAdapter2.setSign(1);
        imageAdapter2.add(R.drawable.pai);
        postRecy.setLayoutManager(new GridLayoutManager(this, 3));
        postRecy.setAdapter(imageAdapter2);
    }

    @OnClick({R.id.post_back, R.id.post_plane})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.post_back:
                finish();
                break;
            case R.id.post_plane:
                postPresenter.reqeust((int) login.getUserId(), login.getSessionId(), "1", postEdit.getText().toString().trim(), imageAdapter2.getList());
                break;
        }
    }
    class postCall implements DataCall<Result>{

        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(PostActivity.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(PostActivity.this, "发布失败！！！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
            String filePath = getFilePath(null,requestCode,data);
            if (!StringUtils.isEmpty(filePath)) {
                imageAdapter2.add(filePath);
                imageAdapter2.notifyDataSetChanged();
//                Bitmap bitmap = UIUtils.decodeFile(new File(filePath),200);
//                mImage.setImageBitmap(bitmap);
            }
        }

    }
}
