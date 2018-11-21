package com.boxuegu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boxuegu.activity.bean.UserBean;
import com.boxuegu.activity.utils.AnalysisUtils;
import com.boxuegu.activity.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_nickName,tv_signature,tv_user_name,tv_sex;
    private RelativeLayout rl_nickName,rl_sex,rl_signature,rl_title_bar;
    private String spUserName;
    private static final int CHANGE_NICKNAME=1;
    private static final int CHANGE_SIGNATURE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spUserName= AnalysisUtils.readLpginUserName(this);
        init();
        initData();
        setListener();
    }
    private void init(){
        tv_back= (TextView) findViewById(R.id.tv_back);
        tv_main_title= (TextView) findViewById(R.id.tv_mian_title);
        tv_main_title.setText("个人资料");
        rl_title_bar= (RelativeLayout) findViewById(R.id.title_bar);
         rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        rl_nickName= (RelativeLayout) findViewById(R.id.rl_nickName);
        rl_sex= (RelativeLayout) findViewById(R.id.rl_sex);
        rl_signature= (RelativeLayout) findViewById(R.id.rl_signature);
        tv_nickName= (TextView) findViewById(R.id.tv_nickName);
        tv_user_name= (TextView) findViewById(R.id.tv_user_name);
        tv_sex= (TextView) findViewById(R.id.tv_sex);
        tv_signature= (TextView) findViewById(R.id.tv_signature);
    }

    private void initData(){
        UserBean bean=null;
        bean= DBUtils.getInstance(this).getUserInfo(spUserName);
        if (bean==null) {
            bean=new UserBean();
            bean.setUsername(spUserName);
            bean.setNickname("问答精灵");
            bean.setSex("男");
            bean.setSignature("问答精灵");
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }

    private void setValue(UserBean bean){
        tv_nickName.setText(bean.getNickname());
        tv_user_name.setText(bean.getUsername());
        tv_sex.setText(bean.getSex());
        tv_signature.setText(bean.getSignature());
    }

    private void setListener(){
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickName:
                String name=tv_nickName.getText().toString().trim();
                Bundle bdName=new Bundle();
                bdName.putString("content",name);
                bdName.putString("title","昵称");
                bdName.putInt("flag",1);
                enterActivityForRestult(ChangeUserInfoActivity.class,CHANGE_NICKNAME,bdName);
                break;
            case R.id.rl_sex:
                String sex=tv_sex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                String signature=tv_signature.getText().toString().trim();
                Bundle dbSignature=new Bundle();
                dbSignature.putString("content",signature);
                dbSignature.putString("title","签名");
                dbSignature.putInt("flag",2);
                enterActivityForRestult(ChangeUserInfoActivity.class,CHANGE_SIGNATURE,dbSignature);
                break;
            default:
                break;
        }
    }

    private void sexDialog(String sex){
        int sexFlag=0;
        if ("男".equals(sex)){
            sexFlag=0;
        }else if ("女".equals(sex)){
            sexFlag=1;
        }
        final String items[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(UserInfoActivity.this,items[i],Toast.LENGTH_SHORT).show();
                setSex(items[i]);
            }
        });
        builder.create().show();
    }
    private void setSex(String sex){
        tv_sex.setText(sex);
        DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("sex",sex,spUserName);
    }

    public void enterActivityForRestult(Class<?> to,int requestCode,Bundle b ){
        Intent i=new Intent(this,to);
        i.putExtras(b);
        startActivityForResult(i,requestCode);
    }
    private String new_info;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHANGE_NICKNAME:
                if (data!=null){
                    new_info=data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tv_nickName.setText(new_info);
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("nickName",new_info,spUserName);
                }
                break;
            case CHANGE_SIGNATURE:
                if (data!=null){
                    new_info=data.getStringExtra("signature");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tv_signature.setText(new_info);
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("signature",new_info,spUserName);
                }
                break;
        }
    }
}
