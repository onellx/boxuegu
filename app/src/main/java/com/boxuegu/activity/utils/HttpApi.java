package com.boxuegu.activity.utils;


/**
 * @ClassName HttpApi
 * @Author One_llx
 * @Date 2018/11/13 0013 下午 8:44
 * @Version 1.0
 */
public final class HttpApi {

    private HttpApi(){

    }
    //服务器地址
    private final static String HTTPURL="http://10.16.124.124:8087/";
    //登录请求地址
    public final  static String LOGIN=HTTPURL+"user/login";
    //注册请求地址
    public final static String REGISTER=HTTPURL+"user/register";
    //更新用户信息
    public final static String UPDATE=HTTPURL+"user/update";
    //获取课程信息
    public final static String COURSELIST=HTTPURL+"course/list";
    //更具课程id查询习题列表
    public final static String EXERCISELIST=HTTPURL+"exercise/getbycid";

}
