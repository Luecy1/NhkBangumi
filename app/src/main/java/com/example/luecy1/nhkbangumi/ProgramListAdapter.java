package com.example.luecy1.nhkbangumi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luecy1.nhkbangumi.entity.common.Program;
import com.example.luecy1.nhkbangumi.util.CommonUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by you on 2017/12/16.
 */

public class ProgramListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    List<Program> programList = null;

    public ProgramListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;

        // ListAdapterの更新
        notifyDataSetChanged();
    }

    public List<Program> getProgramList() {
        return programList;
    }

    @Override
    public int getCount() {

        if (programList == null) {
            return 0;
        } else {
            return programList.size();
        }

    }

    @Override
    public Object getItem(int position) {

        if (programList == null) {
            return null;
        } else {
            return programList.get(position);
        }

    }

    @Override
    public long getItemId(int position) {

        if (programList == null) {
            return 0L;
        } else {
            return (long) programList.get(position).hashCode();
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.activity_program_list_item, parent, false);

        TextView mainText = convertView.findViewById(R.id.entity_mainText);
        TextView subTitle = convertView.findViewById(R.id.list_item_subtitle);
        TextView programDate = convertView.findViewById(R.id.list_item_subtext);

        ImageView logoImage = convertView.findViewById(R.id.Logo_image);


        if (programList == null) {
            return null;
        }

        Program program = programList.get(position);

        mainText.setText(program.getTitle());

        subTitle.setText(program.getSubtitle());

        // 日付変換
        String startDateString = "";
        try {
            Date startDate = CommonUtils.string2Date(program.getStart_time());
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 ah:mm:ss", Locale.JAPAN);
            startDateString =  format.format(startDate).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        programDate.setText(startDateString);

        Picasso.with(context).load("https:" + program.getService().getLogo_l().getUrl()).into(logoImage);

        return convertView;
    }
}
