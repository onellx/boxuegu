package com.boxuegu.activity.utils;

/**
 * Created by Administrator on 2018/11/15 0015.
 */

public class OkHttpThreadUtil extends OkHttpUtil implements Runnable{

   private GetEnum getEnum;
   private PostEnum postEnum;
   private String url;
   private String jsonParams;
   private String responseReslut;

    public OkHttpThreadUtil(GetEnum getEnum, String url) {
        this.getEnum = getEnum;
        this.url = url;
    }

    public OkHttpThreadUtil(PostEnum postEnum, String url, String jsonParams) {
        this.postEnum = postEnum;
        this.url = url;
        this.jsonParams = jsonParams;
    }

    @Override
    public void run() {

        if (getEnum!=null){
            switch (getEnum){
                case GET:
                    responseReslut=OkHttpUtil.getRequest(url);
                    break;
                case GETSYNC:
                    responseReslut=OkHttpUtil.getSyncRequest(url);
                    break;
                default:
                    break;
            }
        }
        if (postEnum!=null){
            switch (postEnum){
                case POST:
                    responseReslut=OkHttpUtil.postRequest(url,jsonParams);
                    break;
                case POSTSYNC:
                    responseReslut=OkHttpUtil.postSyncRequest(url,jsonParams);
                    break;
                default:
                    break;
            }
        }
    }


}
