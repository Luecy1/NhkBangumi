package com.example.luecy1.nhkbangumi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luecy1.nhkbangumi.entity.ProgramList;

/**
 * Created by you on 2017/12/16.
 */

public class ProgramListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ProgramList programList = null;

    public ProgramListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setProgramList(ProgramList programList) {
        this.programList = programList;

        // ListAdapterの更新
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        if (programList == null) {
            return 0;
        } else {
            return programList.getList().getG1().size();
        }

    }

    @Override
    public Object getItem(int position) {

        if (programList == null) {
            return null;
        } else {
            return programList.getList().getG1().get(position);
        }

    }

    @Override
    public long getItemId(int position) {

        if (programList == null) {
            return 0L;
        } else {
            return (long) programList.getList().getG1().get(position).hashCode();
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.activity_program_list_entity, parent, false);

        TextView mainText = convertView.findViewById(R.id.entity_mainText);


        if (programList == null) {
            return null;
        }

        String title = programList.getList().getG1().get(position).getTitle();
        mainText.setText(title);

        return convertView;
    }
}
