package com.bwie.actvity;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.baweishop.R;
import com.bwie.bean.address.AddMyAddress;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDActivity;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAddressActivity extends WDActivity {

    @BindView(R.id.address_add_name)
    EditText addressAddName;
    @BindView(R.id.address_add_phone)
    EditText addressAddPhone;
    @BindView(R.id.address_add_district)
    TextView addressAddDistrict;
    @BindView(R.id.mine_address_add_deposit)
    EditText mineAddressAddDeposit;
    @BindView(R.id.address_add_postcode)
    EditText addressAddPostcode;
    @BindView(R.id.address_add_save)
    Button addressAddSave;
    private CityPicker mBuild;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView() {
        initSan();
        addressAddSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addressAddName.getText().toString().trim();
                String phone = addressAddPhone.getText().toString().trim();
                String xiangxi = addressAddDistrict.getText().toString().trim();
                String diqu = mineAddressAddDeposit.getText().toString().trim();
                String youzheng = addressAddPostcode.getText().toString().trim();


                String url = "http://172.17.8.100/small/user/verify/v1/addReceiveAddress";
                HashMap<String, String> map = new HashMap<>();
                map.put("realName",name);
                map.put("phone",phone);
                map.put("address",diqu+""+xiangxi);
                map.put("zipCode",youzheng);




            }
        });
    }

    private void initSan() {
        addressAddDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPiker();
                mBuild.show();

            }


        });
    }
    class AddressCall implements DataCall<List<AddMyAddress>>{

        @Override
        public void success(List<AddMyAddress> data) {

        }

        @Override
        public void fail(ApiException e) {

        }
    }
    private void initPiker() {
        mBuild = new CityPicker.Builder(this)
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#FFC0CB")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)//省份滚轮是否循环显示
                .cityCyclic(false)//城市滚轮是否循环显示
                .districtCyclic(false)//地区（县）滚轮是否循环显示
                .visibleItemsCount(7)//滚轮显示的item个数
                .itemPadding(10)//滚轮item间距
                .onlyShowProvinceAndCity(false)
                .build();
        mBuild.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... strings) {
                //省份
                String province = strings[0];
                String city = strings[1];
                String district = strings[2];
                String code = strings[3];
                addressAddDistrict.setText(province + city + district);
                Log.e("aaaaaaaaaaaaaa", addressAddDistrict.getText().toString());
            }

            @Override
            public void onCancel() {

            }
        });
    }


    @Override
    protected void destoryData() {

    }

    @OnClick(R.id.address_add_save)
    public void onViewClicked() {
//        if (TextUtils.isEmpty("addressAddName")){
//            Toast.makeText(this, "收件人不能为空", Toast.LENGTH_SHORT).show();
//        }
        Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
    }
}

