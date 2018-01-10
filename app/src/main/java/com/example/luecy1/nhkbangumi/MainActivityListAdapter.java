package com.example.luecy1.nhkbangumi;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luecy1.nhkbangumi.entity.common.Program;
import com.example.luecy1.nhkbangumi.entity.nowonair.NowOnAir;
import com.example.luecy1.nhkbangumi.util.CommonUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by you on 2018/01/08.
 */

public class MainActivityListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    List<NowOnAir> nowOnAirList = new ArrayList<>();

    public MainActivityListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addProgramList(List<NowOnAir> nowOnAirList) {
        if (nowOnAirList != null) {
            this.nowOnAirList.addAll(nowOnAirList);

            // ListAdapterの更新
            notifyDataSetChanged();
        }
    }

    public List<NowOnAir> getNowOnAirList() {
        return nowOnAirList;
    }

    @Override
    public int getCount() {

        if (nowOnAirList == null) {
            return 0;
        } else {
            return nowOnAirList.size();
        }

    }

    @Override
    public Object getItem(int position) {

        if (nowOnAirList == null) {
            return null;
        } else {
            return nowOnAirList.get(position);
        }

    }

    @Override
    public long getItemId(int position) {

        if (nowOnAirList == null) {
            return 0L;
        } else {
            return (long) nowOnAirList.get(position).hashCode();
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.activity_main_list_item, parent, false);

        TextView mainText = convertView.findViewById(R.id.present_title);
        TextView subTitle = convertView.findViewById(R.id.present_subtitle);
        TextView programDate = convertView.findViewById(R.id.present_date);

        ImageView logoImage = convertView.findViewById(R.id.logo_image);


        if (nowOnAirList == null) {
            return null;
        }

        Program program = nowOnAirList.get(position).getPresent();

        mainText.setText(program.getTitle());
        subTitle.setText(program.getSubtitle());

        // 日付変換
        String startDateString = "";
        try {
            Date startDate = CommonUtils.string2Date(program.getStart_time());
            startDateString =  CommonUtils.date2String(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        programDate.setText(startDateString);

        Picasso.with(context).load("https:" + program.getService().getLogo_l().getUrl()).into(logoImage);

        // 前の番組
        TextView prevTitle = convertView.findViewById(R.id.previous_title);
        TextView prevSubTitle = convertView.findViewById(R.id.previous_subtitle);
        TextView prevDate = convertView.findViewById(R.id.previous_date);

        Program prevProgram = nowOnAirList.get(position).getPrevious();

        if (prevProgram != null) {
            prevTitle.setText(prevProgram.getTitle());
            prevSubTitle.setText(prevProgram.getSubtitle());

            String prevDateStr = "";
            try {
                Date startDate = CommonUtils.string2Date(prevProgram.getStart_time());
                prevDateStr =  CommonUtils.date2String(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            prevDate.setText(prevDateStr);
        }

        ConstraintLayout prevLayout = convertView.findViewById(R.id.previos_program_layout);
        prevLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "前へをタップ",Toast.LENGTH_SHORT).show();
                return;
            }
        });


        // 次の番組
        TextView followTitle = convertView.findViewById(R.id.following_title);
        TextView followSubTitle = convertView.findViewById(R.id.following_subtitle);
        TextView followDate = convertView.findViewById(R.id.following_date);

        Program followProgram = nowOnAirList.get(position).getFollowing();

        if (followProgram != null) {
            followTitle.setText(followProgram.getTitle());
            followSubTitle.setText(followProgram.getSubtitle());

            String followDateStr = "";
            try {
                Date startDate = CommonUtils.string2Date(followProgram.getStart_time());
                followDateStr =  CommonUtils.date2String(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            followDate.setText(followDateStr);
        }

        return convertView;
    }
}
