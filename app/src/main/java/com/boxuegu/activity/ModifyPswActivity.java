package com.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boxuegu.activity.bean.UserBean;
import com.boxuegu.activity.utils.AnalysisUtils;
import com.boxuegu.activity.utils.HttpApi;
import com.boxuegu.activity.utils.JsonUtil;
import com.boxuegu.activity.utils.MD5Utils;
import com.boxuegu.activity.utils.OkHttpUtil;

public class ModifyPswActivity extends AppCompatActivity {

    private TextView tv_main_title;
    private TextView tv_back;
    private EditText et_original_psw,et_new_psw,et_new_psw_again;
    private Button btn_save;
    private String originalPsw,newPsw,newPswAgain;
    private String userName;
    private UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        userName= AnalysisUtils.readLpginUserName(this);
    }

    private void init(){
        tv_main_title= (TextView) findViewById(R.id.tv_mian_title);
        tv_main_title.setText("修改密码");
        tv_back= (TextView) findViewById(R.id.tv_back);
        et_original_psw= (EditText) findViewById(R.id.et_original_psw);
        et_new_psw= (EditText) findViewById(R.id.et_new_psw);
        et_new_psw_again= (EditText) findViewById(R.id.et_new_psw_again);
        btn_save= (Button) findViewById(R.id.btn_save);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyPswActivity.this.finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(originalPsw)){
                    Toast.makeText(ModifyPswActivity.this,"请输入原始密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if(!MD5Utils.md5(originalPsw).equals(readPsw())){
                    Toast.makeText(ModifyPswActivity.this,"输入的密码与原始密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                } else if (MD5Utils.md5(newPsw).equals(readPsw())) {
                    Toast.makeText(ModifyPswActivity.this,"输入的密新码与原始密码不能一致",Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(newPsw)) {
                    Toast.makeText(ModifyPswActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(newPswAgain)){
                    Toast.makeText(ModifyPswActivity.this,"请再次输入新密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if (!newPsw.equals(newPswAgain)){
                    Toast.makeText(ModifyPswActivity.this,"两次输入新密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    boolean falg=modifyPsw(newPsw);
                    if (falg){
                        Toast.makeText(ModifyPswActivity.this,"新密码设置成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ModifyPswActivity.this,LoginActivity.class);
                        startActivity(intent);
                        SettingActivity.instance.finish();
                        ModifyPswActivity.this.finish();
                    }else {
                        Toast.makeText(ModifyPswActivity.this,"新密码设置失败",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    private void getEditString(){
        originalPsw=et_original_psw.getText().toString().trim();
        newPsw=et_new_psw.getText().toString().trim();
        newPswAgain=et_new_psw_again.getText().toString().trim();
    }

    private boolean modifyPsw(String newPsw){
        String md5Psw=MD5Utils.md5(newPsw);
        userBean.setPasswd(md5Psw);
        String result=OkHttpUtil.postSyncRequest(HttpApi.UPDATE,JsonUtil.objectToJson(userBean));
        result=JsonUtil.jsonToPojo(result,String.class);
        if (result.equals("true")){
            return true;
        }
        return false;

       /* SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();*/
    }

    private String readPsw(){
        String result= OkHttpUtil.postRequest(HttpApi.LOGIN, JsonUtil.objectToJson(userName));
        userBean=JsonUtil.jsonToPojo(result,UserBean.class);
       /* SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sp.getString(userName,"");*/
       if (userBean==null){
           return "";
       }
        return userBean.getPasswd();
    }

}
