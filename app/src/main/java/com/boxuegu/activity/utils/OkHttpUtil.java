package com.boxuegu.activity.utils;

import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author One_llx
 * Created by One_llx on 2018/11/13 0013.
 */

public class OkHttpUtil {

    //接收结果
    private static String requestString=null;
    private final static OkHttpClient okHttpClient=new OkHttpClient();

    /**
     * get请求的开始部分
     * @param url
     * @return
     */
    protected static Call getRequestStart(String url){
        final Request request=new Request.Builder()
                .url(url)
                .build();
        Call call=okHttpClient.newCall(request);
        return call;
    }

    /**
     * 异步get请求处理结果
     * @param call
     * @return
     */
    protected static String getSyncResult(Call call){
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                requestString = String.valueOf(response.body().string());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return requestString;
    }
    /**
     * 同步Get处理结果
     * android 4.0以后就允许在主线程里面进行网络访问了，所以我们得自己开一个线程
     * @param call
     * @return
     */
    protected static String getRequestResult(final Call call){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response=call.execute();
                    requestString=String.valueOf(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            //让子线程执行完之后再执行主程
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return requestString;
    }

    /**
     * 异步Get请求方法
     * @param url 路径
     * @return 返回结果
     */
    public static String getSyncRequest(String url){
        Call call=getRequestStart(url);
        return getSyncResult(call);
    }


    /**
     * 同步Get
     * @param url
     * @return
     */
    public static String getRequest(String url){
       Call call=getRequestStart(url);
        return getRequestResult(call);
    }

    /**
     * post 请求开始部分
     * @param url 请求路径
     * @param jsonParams json数据
     * @return Call
     */
    protected static Call postRequestStart(String url, String jsonParams){
        RequestBody requestBody=FormBody.create(MediaType.parse("application/json;charset=utf-8"),jsonParams);
        final Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call=okHttpClient.newCall(request);
        return call;
    }

    /**
     * 异步post请求处理结果
     * @param call
     * @return
     */
    protected static String postSyncRequestResult(Call call){
        //标记子线程的数量
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                if (response!=null) {
//                    Log.i("MainActivity", "返回服务端数据:" + String.valueOf(response.body().string()));
//                }
                requestString=String.valueOf(response.body().string());
                countDownLatch.countDown();//通知  一个子线程已经执行完了
            }
        });
        try {
            //让子线程执行完之后再执行主线程
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return requestString;
    }

    /**
     * 同步post处理结果
     * @param call
     * @return
     */
    protected static String postRequestResult(final Call call){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response=call.execute();
                    requestString=String.valueOf(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return requestString;
    }
    /**
     * 异步post json请求方式
     * @param url 路径
     * @param jsonParams json数据
     * @return 结果
     */
    public static String postSyncRequest(String url, String jsonParams){
        Call call=postRequestStart(url,jsonParams);
        return postSyncRequestResult(call);
    }

    /**
     * 同步post json请求
     * @param url
     * @param jsonParams
     * @return
     */
    public static String postRequest(String url,String jsonParams){
        Call call=postRequestStart(url, jsonParams);
        return postRequestResult(call);
    }


}
