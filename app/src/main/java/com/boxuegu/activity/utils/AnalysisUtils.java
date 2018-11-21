package com.boxuegu.activity.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;

import com.boxuegu.activity.bean.CourseBean;
import com.boxuegu.activity.bean.ExercisersBean;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/13 0013.
 */

public class AnalysisUtils {
    /**
     * 从SharePreference中读取登录用户名
     *
     * @param context
     * @return
     */
    public static String readLpginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String  userName=sp.getString("loginUserName","");
        return userName;
    }

    /**
     * 解析每章的习题
     * @param is
     * @return
     * @throws Exception
     */
    public static List<ExercisersBean> getExercisersInfos(InputStream is)throws Exception{
        XmlPullParser parser= Xml.newPullParser();
        parser.setInput(is,"utf-8");
        List<ExercisersBean> exercisersInfos=null;
        ExercisersBean exercisersBean=null;
        int type=parser.getEventType();
        while (type!=XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())){
                        exercisersInfos=new ArrayList<ExercisersBean>();
                    }else if ("exercises".equals(parser.getName())){
                        exercisersBean=new ExercisersBean();
                        String ids=parser.getAttributeValue(0);
                        exercisersBean.setSubjectId(Integer.parseInt(ids));
                    }else if ("subject".equals(parser.getName())){
                        String subject=parser.nextText();
                        exercisersBean.setSubject(subject);
                    }else if ("a".equals(parser.getName())){
                        String a=parser.nextText();
                        exercisersBean.setA(a);
                    }else if ("b".equals(parser.getName())){
                        String b=parser.nextText();
                        exercisersBean.setB(b);
                    }else if ("c".equals(parser.getName())){
                        String c=parser.nextText();
                        exercisersBean.setC(c);
                    }else if ("d".equals(parser.getName())){
                        String a=parser.nextText();
                        exercisersBean.setC(a);
                    }else if ("answer".equals(parser.getName())){
                        String answer=parser.nextText();
                        exercisersBean.setAnswer(Integer.parseInt(answer));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("exercises".equals(parser.getName())){
                        exercisersInfos.add(exercisersBean);
                        exercisersBean=null;
                    }
                    break;
            }
            type=parser.next();
        }
        return exercisersInfos;



    }
    /**
     * 设置A、B、C、D选项是否可点击
     */
    public static void setABCDEnable(boolean value, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d){
        iv_a.setEnabled(value);
        iv_b.setEnabled(value);
        iv_c.setEnabled(value);
        iv_d.setEnabled(value);
    }

    /**
     * 解析每章的课程视频信息
     */
    public static List<List<CourseBean>> getCourseInfos(InputStream is) throws Exception {
        XmlPullParser parser=Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<List<CourseBean>> courseInfos=null;
        List<CourseBean> courseList=null;
        CourseBean courseInfo=null;
        int count=0;
        int type=parser.getEventType();
        while (type!=XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if("infos".equals(parser.getName())){
                        courseInfos=new ArrayList<List<CourseBean>>();
                        courseList=new ArrayList<CourseBean>();
                    }else if("course".equals(parser.getName())){
                        courseInfo=new CourseBean();
                        String ids=parser.getAttributeValue(0);
                        courseInfo.setId(Integer.parseInt(ids));
                    }else if("imgtitle".equals(parser.getName())){
                        String imgtitle=parser.nextText();
                        courseInfo.setImgTitle(imgtitle);
                    }else if("title".equals(parser.getName())){
                        String title=parser.nextText();
                        courseInfo.setTitle(title);
                    }else if("intro".equals(parser.getName())){
                        String intro=parser.nextText();
                        courseInfo.setIntro(intro);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("course".equals(parser.getName())){
                        count++;
                        courseList.add(courseInfo);
                        if(count%2==0){// 课程界面每两个数据是一组放在List集合中
                            courseInfos.add(courseList);
                            courseList=null;
                            courseList=new ArrayList<CourseBean>();
                        }
                        courseInfo=null;
                    }
                    break;
            }
            type=parser.next();
        }
        return courseInfos;
    }
    /**
     * 从SharedPreferences中读取登录用户名
     */
    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName=sp.getString("loginUserName", "");
        return userName;
    }
}
