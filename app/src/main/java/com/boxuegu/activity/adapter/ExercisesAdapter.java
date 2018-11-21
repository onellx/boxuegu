package com.boxuegu.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boxuegu.activity.ExercisesDetailActivity;
import com.boxuegu.activity.R;
import com.boxuegu.activity.bean.ExercisersBean;

import java.util.List;

/**
 * Created by pc on 2018/10/16.
 */

public class ExercisesAdapter extends BaseAdapter{
    private Context mContext;
    private List<ExercisersBean> ebl;

    public ExercisesAdapter(Context Context) {
        this.mContext = Context;
    }

    public void setData(List<ExercisersBean> ebl) {
        this.ebl = ebl;
        notifyDataSetChanged();
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.exercises_list_item,null);
            vh.title= (TextView) convertView.findViewById(R.id.tv_title);
            vh.content= (TextView) convertView.findViewById(R.id.tv_content);
            vh.order= (TextView) convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }
        final ExercisersBean bean=getItem(position);
        if (bean!=null){
            vh.order.setText((position+1)+"");
            vh.title.setText(bean.getTitle());
            vh.content.setText(bean.getContext());
            vh.order.setBackgroundResource(bean.getBackground());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean==null){
                    return;
                }
                Intent intent=new Intent(mContext,ExercisesDetailActivity.class);
                intent.putExtra("id",bean.getId());
                intent.putExtra("title",bean.getTitle());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView title,content;
        public TextView order;
    }
}
