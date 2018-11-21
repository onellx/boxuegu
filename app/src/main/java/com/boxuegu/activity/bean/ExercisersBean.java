package com.boxuegu.activity.bean;

/**
 * Created by Administrator on 2018/9/13 0013.
 */

public class ExercisersBean {
    private int id;//每章习题ID
    private String title;//每张习题标题
    private String context;//每张习题的数目
    private int background;//每张习题前边的序号背景
    private int subjectId;//每道习题的ID
    private String subject;//每道习题的题干
    private String a;//每道题的A选项
    private String b;//每道题的B选项
    private String c;//每道题的C选项
    private  String d;//每道题的D选项
    private int answer;//每道题的正确选项



    /**
     * select 为0表示所选项是对的，1表示选中的A选项是错的，2表示选中的B选项是错的，
     * 3表示选中的C选项是错的，4表示选中的D选项是错的，
     */
    private int select;
    public void setSelect(int select) {
        this.select = select;
    }

    public int getSelect() {
        return select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
