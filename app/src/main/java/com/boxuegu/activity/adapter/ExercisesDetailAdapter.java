package com.boxuegu.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boxuegu.activity.R;
import com.boxuegu.activity.bean.ExercisersBean;
import com.boxuegu.activity.utils.AnalysisUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/10/23.
 */

public class ExercisesDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisersBean> ebl;
    private OnSelectListener onSelectListener;

    public ExercisesDetailAdapter(Context mContext, OnSelectListener onSelectListener) {
        this.mContext = mContext;
        this.onSelectListener = onSelectListener;
    }

    public void setData(List<ExercisersBean> ebl) {
        this.ebl = ebl;
        notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        return ebl==null?0:ebl.size();
    }

    @Override
    public ExercisersBean getItem(int position) {
        return ebl==null?null:ebl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private ArrayList<String> selectedPosition=new ArrayList<String>();
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.exercises_detail_list_item, null);
            vh.subject = (TextView) convertView.findViewById(R.id.tv_subject);
            vh.tv_a = (TextView) convertView.findViewById(R.id.tv_a);
            vh.tv_b = (TextView) convertView.findViewById(R.id.tv_b);
            vh.tv_c = (TextView) convertView.findViewById(R.id.tv_c);
            vh.tv_d = (TextView) convertView.findViewById(R.id.tv_d);
            vh.iv_a = (ImageView) convertView.findViewById(R.id.iv_a);
            vh.iv_b = (ImageView) convertView.findViewById(R.id.iv_b);
            vh.iv_c = (ImageView) convertView.findViewById(R.id.iv_c);
            vh.iv_d = (ImageView) convertView.findViewById(R.id.iv_d);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final ExercisersBean bean = getItem(position);
        if (bean != null) {
            vh.subject.setText(bean.getSubject());
            vh.tv_a.setText(bean.getA());
            vh.tv_b.setText(bean.getB());
            vh.tv_c.setText(bean.getC());
            vh.tv_d.setText(bean.getD());
        }
        if (!selectedPosition.contains("" + position)) {
            vh.iv_a.setImageResource(R.drawable.exercises_a);
            vh.iv_b.setImageResource(R.drawable.exercises_b);
            vh.iv_c.setImageResource(R.drawable.exercises_c);
            vh.iv_d.setImageResource(R.drawable.exercises_d);
            AnalysisUtils.setABCDEnable(true, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
        } else {
            AnalysisUtils.setABCDEnable(false, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
            switch (bean.getSelect()) {
                case 0:
                    //用户所选项是正确的
                    if (bean.getAnswer() == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 2) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 1:
                    //用户所选项A是错误的
                    vh.iv_a.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 2) {
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer()== 4) {
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2:
                    //用户所选项B是错误的
                    vh.iv_b.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 3:
                    //用户所选项C是错误的
                    vh.iv_c.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 2) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 4:
                    //用户所选项D是错误的
                    vh.iv_d.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                    } else if (bean.getAnswer() == 2) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                    } else if (bean.getAnswer() == 3) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                default:
                    break;
            }
        }
        //当用户点击A选项的点击事件
        vh.iv_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断selectedPosition中是否包含此时点击的positon
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectA(position, vh.iv_a, vh.iv_b, vh.iv_c,
                        vh.iv_d);
            }
        });
        //当用户点击B选项的点击事件
        vh.iv_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectB(position, vh.iv_a, vh.iv_b, vh.iv_c,
                        vh.iv_d);
            }
        });
        //当用户点击C选项的点击事件
        vh.iv_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectC(position, vh.iv_a, vh.iv_b, vh.iv_c,
                        vh.iv_d);
            }
        });
        //当用户点击D选项的点击事件
        vh.iv_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectD(position, vh.iv_a, vh.iv_b, vh.iv_c,
                        vh.iv_d);
            }
        });
        return convertView;
    }
    class  ViewHolder{
        public TextView subject,tv_a,tv_b,tv_c,tv_d;
        public ImageView iv_a,iv_b,iv_c,iv_d;
    }
    public interface OnSelectListener{
        void onSelectA(int position,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
        void onSelectB(int position,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
        void onSelectC(int position,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
        void onSelectD(int position,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
    }
}
