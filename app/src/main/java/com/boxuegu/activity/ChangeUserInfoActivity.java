package com.boxuegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeUserInfoActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_mian_title)
    TextView tvMianTitle;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    private int flag;
    private String title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_user_info);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }
    private void init(){
        title=getIntent().getStringExtra("title");
        content=getIntent().getStringExtra("content");
        flag=getIntent().getIntExtra("flag",0);
        tvMianTitle.setText("title");
        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvSave.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(content)){
            etContent.setText(content);
            etContent.setSelection(content.length());
        }
        contentListener();
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeUserInfoActivity.this.finish();
            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etContent.setText("");
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data=new Intent();
                String et_Content=etContent.getText().toString().trim();
                switch (flag){
                    case 1:
                        if (!TextUtils.isEmpty(et_Content)){
                            data.putExtra("nickName",et_Content);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        }else{
                            Toast.makeText(ChangeUserInfoActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(et_Content)){
                        data.putExtra("signature",et_Content);
                        setResult(RESULT_OK,data);
                        Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                        ChangeUserInfoActivity.this.finish();
                    }else{
                        Toast.makeText(ChangeUserInfoActivity.this,"签名不能为空",Toast.LENGTH_SHORT).show();
                    }
                        break;

                }
            }
        });
    }

    private void contentListener(){
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable=etContent.getText();
                int len=editable.length();
                if (len>0){
                    ivDelete.setVisibility(View.VISIBLE);
                }else {
                    ivDelete.setVisibility(View.GONE);
                }
                switch (flag){
                    case 1:
                        if (len>18){
                            int selEndIndex= Selection.getSelectionEnd(editable);
                            String str=editable.toString();
                            String newStr=str.substring(0,8);
                            etContent.setText(newStr);
                            editable=etContent.getText();
                            int newLen=editable.length();
                            if (selEndIndex>newLen) {
                                selEndIndex=editable.length();
                            }
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                    case 2:
                        if (len>16){
                            int selEndIndex= Selection.getSelectionEnd(editable);
                            String str=editable.toString();
                            String newStr=str.substring(0,6);
                            etContent.setText(newStr);
                            editable=etContent.getText();
                            int newLen=editable.length();
                            if (selEndIndex>newLen) {
                                selEndIndex=editable.length();
                            }
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
