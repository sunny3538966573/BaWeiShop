package com.bwie.utils.core;

import com.bwie.bean.Result;
import com.bwie.bean.address.AddMyAddress;
import com.bwie.bean.address.MyAddress;
import com.bwie.bean.cart.Cart;
import com.bwie.bean.circle.Circle;
import com.bwie.bean.circle.MyCircle;
import com.bwie.bean.foot.Foot;
import com.bwie.bean.home.Banners;
import com.bwie.bean.home.Details;
import com.bwie.bean.home.Querys;
import com.bwie.bean.home.homeshop.HomeList;
import com.bwie.bean.login.ILogin;
import com.bwie.bean.login.Login;
import com.bwie.bean.order.AllOrder;
import com.bwie.bean.order.ResultOrder;
import com.bwie.bean.wallet.Wallet;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface IRequest {
    /**
     * 登录
     * @param phone
     * @param pwd
     * @return
     */
    @POST("login")
    @FormUrlEncoded
    Observable<ILogin<Login>> getlogin(@Field("phone") String phone,@Field("pwd")String pwd);

    /**
     * 注册
     * @param phone
     * @param pwd
     * @return
     */
    @POST("register")
    @FormUrlEncoded
    Observable<ILogin<Login>> getregister(@Field("phone") String phone,@Field("pwd") String pwd);

    /**
     * 首页轮播
     * @return
     */
    @GET("commodity/v1/bannerShow")
    Observable<Result<List<Banners>>> getbanners();

    /**
     * 首页数据
     * @return
     */
    @GET("commodity/v1/commodityList")
    Observable<Result<HomeList>> gethomes();
    /**
     * 关键词查询数据
     * @return
     */
    @GET("commodity/v1/findCommodityByKeyword")
    Observable<Querys> getquery(@Query("keyword") String keyword, @Query("page") String page, @Query("count") String count);

    /**
     * 商品详情
     * @return
     */
    @GET("commodity/v1/findCommodityDetailsById")
    Observable<Details> getdetails(@Header("userId")String userId, @Header("sessionId")String sessionId,
                                   @Query("commodityId")int commodityId);
    /**
     * 圈子数据
     * @return
     */
    @GET("circle/v1/findCircleList")
    Observable<Result<List<Circle>>> getcircle(@Header("userId")long userId,
                                               @Header("sessionId")String sessionId,
                                               @Query("page")int page,
                                               @Query("count") int count);

    /**
     * 我的钱包数据
     * @return
     */
    @GET("user/verify/v1/findUserWallet")
    Observable<Result<List<Wallet>>> getwallet(@Header("userId")long userId,
                                               @Header("sessionId")String sessionId,
                                               @Query("page")int page,
                                               @Query("count") int count);


    /**
     * 购物车数据
     * @return
     */
    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<Cart>>> getcart(@Header("userId")long userId,
                                           @Header("sessionId")String sessionId);


    /**
     * 订单数据
     * @return
     */
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<Result<List<AllOrder>>> getallorder(@Header("userId")long userId,
                                                        @Header("sessionId")String sessionId,
                                                        @Query("status") int status,
                                                        @Query("page")int page,
                                                        @Query("count")int count);


    /**
     * 我的圈子数据
     * @return
     */
    @GET("circle/verify/v1/findMyCircleById")
    Observable<Result<List<MyCircle>>> getmycircle(@Header("userId")long userId,
                                                   @Header("sessionId")String sessionId,
                                                   @Query("page")int page,
                                                   @Query("count") int count);

    /**
     * 我的足迹数据
     * @return
     */
    @GET("commodity/verify/v1/browseList")
    Observable<Result<List<Foot>>> getmyfoot(@Header("userId")long userId,
                                             @Header("sessionId")String sessionId,
                                             @Query("page")int page,
                                             @Query("count") int count);





    /**
     * 我的收货地址
     * @param userId
     * @param sessionId
     * @return
     */
    @GET("user/verify/v1/receiveAddressList")
    Observable<Result<List<MyAddress>>> getmyAddress(@Header("userId") long userId,
                                                     @Header("sessionId") String sessionId);

    /**
     * 新增收货地址
     * @return
     */
    @POST("user/verify/v1/addReceiveAddress")
    @FormUrlEncoded
    Observable<AddMyAddress> getaddress(@Header("userId") long userId,
                                        @Header("sessionId") String sessionId,
                                        @Field("realName") String realName,
                                        @Field("phone") String phone,
                                        @Field("address") String address,
                                        @Field("zipCode") String zipCode);

    // 发布圈子
    @POST("circle/verify/v1/releaseCircle")
    Observable<Result> sendCircle(@Header("userId") int id,
                                  @Header("sessionId") String iaa,
                                  @Body MultipartBody multipart);

}
