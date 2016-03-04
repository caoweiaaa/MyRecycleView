package com.chs.myrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chs.myrecycleview.R;
import com.chs.myrecycleview.widget.TimeLine;

import java.util.List;

/**
 * 作者：chs on 2016/2/2 14:43
 * 邮箱：657083984@qq.com
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mData;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public MyAdapter(Context mContext,List<String> list) {
        this.mContext = mContext;
        this.mData = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        MyViewHolder holder = new MyViewHolder(view,viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_text.setText(mData.get(position));
        if(onItemClickListener!=null){
           holder.ll_main.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onItemClickListener.onItemClick(holder.itemView,position);
               }
           });
        }
    }

    @Override
    public int getItemViewType(int position) {
       if(position==0){
            return TimeLine.START;
        }else if(mData.size()-1==0){
           return TimeLine.SPECIAL;
       }else if(position==mData.size()-1){
           return TimeLine.END;
       }else {
           return TimeLine.NORMAL;
       }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_text;
        LinearLayout ll_main;
        TimeLine time_line;
        public MyViewHolder(View itemView,int type) {
            super(itemView);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
            ll_main = (LinearLayout) itemView.findViewById(R.id.ll_main);
            time_line = (TimeLine) itemView.findViewById(R.id.time_line);
            if(type == TimeLine.SPECIAL){
                time_line.setBeginLine(null);
                time_line.setEndLine(null);
            }else if(type == TimeLine.START){
                time_line.setBeginLine(null);
            }else if(type==TimeLine.END){
                time_line.setEndLine(null);
            }else {

            }
        }
    }
}
