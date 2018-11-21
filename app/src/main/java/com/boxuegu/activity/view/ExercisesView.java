package com.boxuegu.activity.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.boxuegu.activity.R;
import com.boxuegu.activity.adapter.ExercisesAdapter;
import com.boxuegu.activity.bean.CourseBean;
import com.boxuegu.activity.bean.ExercisersBean;
import com.boxuegu.activity.utils.HttpApi;
import com.boxuegu.activity.utils.JsonUtil;
import com.boxuegu.activity.utils.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/10/17.
 */

public class ExercisesView {
    private ListView lv_list;
    private ExercisesAdapter adapter;
    private List<ExercisersBean> ebl;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;

    public ExercisesView(Activity Context) {
        this.mContext =Context;
        mInflater=LayoutInflater.from(mContext);
    }
    private void createView(){
        initView();
    }
    private void initView(){
        mCurrentView=mInflater.inflate(R.layout.main_view_exercises,null);
        lv_list= (ListView) mCurrentView.findViewById(R.id.lv_list);
        adapter=new ExercisesAdapter(mContext);
        initData();
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }
    private void initData(){
        ebl=new ArrayList<ExercisersBean>();
       /* String result=OkHttpUtil.getSyncRequest(HttpApi.COURSELIST);
        List<CourseBean> courseBeans= JsonUtil.jsonToList(result,CourseBean.class);
        for (int i=0;i<courseBeans.size();i++){
            String result2=OkHttpUtil.postSyncRequest(HttpApi.EXERCISELIST,JsonUtil.objectToJson(courseBeans.get(i).getCid()));
            List<ExercisersBean> exercisersBeans= JsonUtil.jsonToList(result2,ExercisersBean.class);
            ExercisersBean bean=new ExercisersBean();
            bean.setId(courseBeans.get(i).getId());
            bean.setTitle(courseBeans.get(i).getTitle());
            bean.setContext("共计"+exercisersBeans.size()+"题");
            switch (i%4){
                case 0:
                    bean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.setBackground(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.setBackground(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.setBackground(R.drawable.exercises_bg_4);
                    break;
            }
            ebl.add(bean);
        }*/

        for (int i=0;i<10;i++){
            ExercisersBean bean=new ExercisersBean();
            bean.setId(i+1);
            switch (i){
                case 0:
                    bean.setTitle("第1章 Android基础入门");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.setTitle("第2章 Android UI开发");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.setTitle("第3章 Activity");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.setTitle("第4章 数据存储");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.setTitle("第5章 SQLite数据库");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.setTitle("第6章 广播接受者");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.setTitle("第7章 服务");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.setTitle("第8章 内容提供者");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.setTitle("第9章 网络编程");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.setTitle("第10章 高级编程");
                    bean.setContext("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_2);
                    break;
                default:
                    break;
            }
            ebl.add(bean);
        }
    }
    public View getView(){
        if (mCurrentView==null){
            createView();
        }
        return  mCurrentView;
    }
    public void showView(){
        if (mCurrentView==null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}

