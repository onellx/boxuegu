package com.boxuegu.activity.bean;


/**
 * Created by Administrator on 2018/9/13 0013.
 */

public class UserBean {
    private Integer uid;
    private String username;
    private String nickname;
    private String sex;
    private  String signature;
    private String passwd;
    private String validatename;

    public String getValidatename() {
        return validatename;
    }

    public void setValidatename(String validatename) {
        this.validatename = validatename;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
