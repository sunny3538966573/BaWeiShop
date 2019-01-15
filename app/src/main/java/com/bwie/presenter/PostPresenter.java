package com.bwie.presenter;


import com.bwie.utils.RetrofitUtils;
import com.bwie.utils.core.IRequest;
import com.bwie.utils.http.DataCall;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by 1607c王晴
 * date 2019/1/14
 * Describe:
 */
public class PostPresenter extends BasePresenter{

    public PostPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = RetrofitUtils.getInstance().create(IRequest.class);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("commodityId",(String) args[2]);// 商品ID
        builder.addFormDataPart("content",(String)args[3]);//文字内容
        List<Object> list = (List<Object>) args[4];// 图片集合
        if (list .size()>1) {
            for (int i = 1; i < list.size(); i++) {
                File file = new File((String) list.get(i));
                builder.addFormDataPart("image",file.getName(),RequestBody.create(MediaType.parse("multipart/octet-stream"),file));
            }
        }
        return iRequest.sendCircle((int)args[0],(String)args[1],builder.build());
    }
}
