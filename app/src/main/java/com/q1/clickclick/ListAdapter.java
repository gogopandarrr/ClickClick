package com.q1.clickclick;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alfo06-07 on 2018-03-09.
 */

public class ListAdapter extends BaseAdapter {


    ArrayList<Records> recordsArrayList;
    LayoutInflater inflater;


    public ListAdapter(ArrayList<Records> recordsArrayList, LayoutInflater inflater) {
        this.recordsArrayList = recordsArrayList;
        this.inflater = inflater;
    }




    @Override
    public int getCount() {
        return recordsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return recordsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

    if(view==null){

    view = inflater.inflate(R.layout.list_itemform, null);


    }

    Records records = recordsArrayList.get(position);

        ImageView icon = view.findViewById(R.id.iv_ranking);
        TextView tv_rank = view.findViewById(R.id.tv_rank);
        TextView tv_finalRecord = view.findViewById(R.id.tv_finalRecord);

        if(position==0){
            icon.setImageResource(R.drawable.ranking_winner);

        }else{
        icon.setImageResource(records.getIcon());}


        tv_rank.setText(position+1+"등");

        tv_finalRecord.setText(MainActivity.timeFormat(records.getTimeRecord()));






        return view;
    }





}
