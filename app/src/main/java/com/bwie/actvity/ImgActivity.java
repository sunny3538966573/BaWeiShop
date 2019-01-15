package com.bwie.actvity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.baweishop.R;
import com.bwie.bean.login.Login;
import com.bwie.greendao.DaoMaster;
import com.bwie.greendao.DaoSession;
import com.bwie.greendao.LoginDao;

import java.util.List;

public class ImgActivity extends AppCompatActivity {


    public final static int PHOTO = 0;// 相册选取
    public final static int CAMERA = 1;// 拍照
    private static ImgActivity mForegroundActivity = null;


    private List<Login> logins;
    private LoginDao loginDao;
    public Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 得到登录的信息
        DaoSession daoSession = DaoMaster.newDevSession(this, LoginDao.TABLENAME);
        loginDao = daoSession.getLoginDao();
        logins = loginDao.loadAll();
        for (int i = 0; i < logins.size(); i++) {
            if (logins.get(i).getStatues() ==1) {
                login = logins.get(i);
                return;
            }
        }
        //
    }

    /**
     * 获取当前处于前台的activity
     */
    public static ImgActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 得到图片的路径
     *
     * @param fileName
     * @param requestCode
     * @param data
     * @return
     */

    public String getFilePath(String fileName, int requestCode, Intent data) {
        if (requestCode == CAMERA) {
            return fileName;
        } else if (requestCode == PHOTO) {
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor
                    .getString(actual_image_column_index);
            // 4.0以上平台会自动关闭cursor,所以加上版本判断,OK
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                actualimagecursor.close();
            return img_path;
        }
        return null;
    }

}

