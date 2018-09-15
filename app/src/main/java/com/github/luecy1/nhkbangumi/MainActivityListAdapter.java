package com.github.luecy1.nhkbangumi;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.luecy1.nhkbangumi.entity.common.Program;
import com.github.luecy1.nhkbangumi.entity.nowonair.NowOnAir;
import com.github.luecy1.nhkbangumi.util.CommonUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        // 現在の番組
        Program program = nowOnAirList.get(position).getPresent();

        mainText.setText(program.getTitle());
        subTitle.setText(program.getSubtitle());

        // 日付変換
        String startDateString = "";
        try {
            Date startDate = CommonUtils.INSTANCE.string2Date(program.getStart_time());
            startDateString =  CommonUtils.INSTANCE.date2String(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        programDate.setText(startDateString);

        ConstraintLayout presentLayout = convertView.findViewById(R.id.present_program_layout);
        setClickProgram(presentLayout, program);

        Picasso.with(context).load("https:" + program.getService().getLogo_l().getUrl()).into(logoImage);

        // 前の番組
        TextView prevTitle = convertView.findViewById(R.id.previous_title);
        TextView prevSubTitle = convertView.findViewById(R.id.previous_subtitle);
        TextView prevDate = convertView.findViewById(R.id.previous_date);

        final Program prevProgram = nowOnAirList.get(position).getPrevious();

        if (prevProgram != null) {
            prevTitle.setText(prevProgram.getTitle());
            prevSubTitle.setText(prevProgram.getSubtitle());

            String prevDateStr = "";
            try {
                Date startDate = CommonUtils.INSTANCE.string2Date(prevProgram.getStart_time());
                prevDateStr =  CommonUtils.INSTANCE.date2String(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            prevDate.setText(prevDateStr);

            ConstraintLayout prevLayout = convertView.findViewById(R.id.previos_program_layout);
            setClickProgram(prevLayout, prevProgram);
        }

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
                Date startDate = CommonUtils.INSTANCE.string2Date(followProgram.getStart_time());
                followDateStr =  CommonUtils.INSTANCE.date2String(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            followDate.setText(followDateStr);

            ConstraintLayout followLayout = convertView.findViewById(R.id.following_program_layout);
            setClickProgram(followLayout, followProgram);
        }

        return convertView;
    }

    private void setClickProgram(final View view, final Program program) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (program == null) {
                    return;
                }
                String programId = program.getId();
                String service   = program.getService().getId();
                String area      = program.getArea().getId();
                Intent detailIntent = new Intent(context , ProgramDetailActivity.class);

                detailIntent.putExtra("id",programId);
                detailIntent.putExtra("service", service);
                detailIntent.putExtra("area", area);
                context.startActivity(detailIntent);

                return;
            }
        });

    }
}
