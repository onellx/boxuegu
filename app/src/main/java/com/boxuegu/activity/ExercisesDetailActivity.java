package com.boxuegu.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.boxuegu.activity.adapter.ExercisesDetailAdapter;
import com.boxuegu.activity.bean.ExercisersBean;
import com.boxuegu.activity.utils.AnalysisUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/10/30.
 */

public class ExercisesDetailActivity extends AppCompatActivity{

    private TextView tv_main_title;
    private TextView tv_back;
    private RelativeLayout rl_title_bar;
    private ListView lv_list;
    private String title;
    private int id;
    private List<ExercisersBean> ebl;
    private ExercisesDetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 获取从习题界面传递过来的章节id
        id = getIntent().getIntExtra("id", 0);
        // 获取从习题界面传递过来的章节标题
        title = getIntent().getStringExtra("title");
        ebl = new ArrayList<ExercisersBean>();
        initData();
        init();
    }
    private void initData() {
        try {
            // 从xml文件中获取习题数据
            InputStream is = getResources().getAssets().open(
                    "chapter" + id + ".xml");
            ebl = AnalysisUtils.getExercisersInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 初始化控件
     */
    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_mian_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        lv_list = (ListView) findViewById(R.id.iv_list);
        TextView tv = new TextView(this);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setTextSize(16.0f);
        tv.setText("一、选择题");
        tv.setPadding(10, 15, 0, 0);
        //lv_list.addHeaderView(tv);
        tv_main_title.setText(title);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExercisesDetailActivity.this.finish();
            }
        });
        adapter = new ExercisesDetailAdapter(ExercisesDetailActivity.this,
                new ExercisesDetailAdapter.OnSelectListener() {
                    @Override
                    public void onSelectD(int position, ImageView iv_a,
                                          ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是4即D选项
                        if (ebl.get(position).getAnswer() != 4) {
                            ebl.get(position).setSelect(4);
                        } else {
                            ebl.get(position).setSelect(0);
                        }
                        switch (ebl.get(position).getAnswer()) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 3:
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                    @Override
                    public void onSelectC(int position, ImageView iv_a,
                                          ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是3即C选项
                        if (ebl.get(position).getAnswer() != 3) {
                            ebl.get(position).setSelect(3);
                        } else {
                            ebl.get(position).setSelect(0);
                        }
                        switch (ebl.get(position).getAnswer()) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 3:
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                    @Override
                    public void onSelectB(int position, ImageView iv_a,
                                          ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是2即B选项
                        if (ebl.get(position).getAnswer() != 2) {
                            ebl.get(position).setSelect(2);
                        } else {
                            ebl.get(position).setSelect(0);
                        }
                        switch (ebl.get(position).getAnswer()) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 3:
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                    @Override
                    public void onSelectA(int position, ImageView iv_a,
                                          ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是1即A选项
                        if (ebl.get(position).getAnswer() != 1) {
                            ebl.get(position).setSelect(1);
                        } else {
                            ebl.get(position).setSelect(0);
                        }
                        switch (ebl.get(position).getAnswer()) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 2:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 3:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                });
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }

}
