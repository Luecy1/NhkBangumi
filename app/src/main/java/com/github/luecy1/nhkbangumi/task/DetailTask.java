package com.github.luecy1.nhkbangumi.task;

import com.github.luecy1.nhkbangumi.Const;
import com.github.luecy1.nhkbangumi.entity.description.Description;
import com.github.luecy1.nhkbangumi.entity.description.DescriptionListRoot;
import com.github.luecy1.nhkbangumi.model.ProgramDetailModel;
import com.github.luecy1.nhkbangumi.util.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;

/**
 * Created by you on 2018/01/27.
 */

public class DetailTask {
    public ProgramDetailModel jsonToModel(Response<List<DescriptionListRoot>> response) {
        final DescriptionListRoot descriptionListRoot = response.body().get(0);

        if (descriptionListRoot == null || descriptionListRoot.getList() == null) {
            return null;
        }

        Description tmpDescription = null;
        if (descriptionListRoot.getList().getG1() != null) {
            tmpDescription = descriptionListRoot.getList().getG1().get(0);
        } else if (descriptionListRoot.getList().getG2() != null) {
            tmpDescription = descriptionListRoot.getList().getG2().get(0);
        } else if (descriptionListRoot.getList().getE1() != null) {
            tmpDescription = descriptionListRoot.getList().getE1().get(0);
        } else if (descriptionListRoot.getList().getE2() != null) {
            tmpDescription = descriptionListRoot.getList().getE2().get(0);
        } else if (descriptionListRoot.getList().getE3() != null) {
            tmpDescription = descriptionListRoot.getList().getE3().get(0);
        } else if (descriptionListRoot.getList().getE4() != null) {
            tmpDescription = descriptionListRoot.getList().getE4().get(0);
        } else if (descriptionListRoot.getList().getS1() != null) {
            tmpDescription = descriptionListRoot.getList().getS1().get(0);
        } else if (descriptionListRoot.getList().getS2() != null) {
            tmpDescription = descriptionListRoot.getList().getS2().get(0);
        } else if (descriptionListRoot.getList().getS3() != null) {
            tmpDescription = descriptionListRoot.getList().getS3().get(0);
        } else if (descriptionListRoot.getList().getS4() != null) {
            tmpDescription = descriptionListRoot.getList().getS4().get(0);
        }
        if (tmpDescription == null) {
            return null;
        }
        final Description description = tmpDescription;

        ProgramDetailModel model = new ProgramDetailModel();
        model.setTitle(description.getTitle());
        model.setSubtitle(description.getSubtitle());
        model.setContent(description.getContent());
        model.setArea(description.getArea().getName());
        model.setService(description.getService().getName());
        // 日付変換
        String startDateString = "";
        String endDateString = "";
        try {
            Date startDate = CommonUtils.string2Date(description.getStart_time());
            Date endDate   = CommonUtils.string2Date(description.getEnd_time());
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 ah:mm:ss", Locale.JAPAN);
            startDateString =  format.format(startDate).toString();
            endDateString   =  format.format(endDate).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.setTime(startDateString + "～\n" + endDateString);
        model.setAct(description.getAct());

        StringBuilder genreStrBuilder = new StringBuilder();
        for (String genre : description.getGenres()) {
            if (genreStrBuilder.length() != 0) {
                genreStrBuilder.append(",");
            }
            genreStrBuilder.append(Const.GENRE_MAP_CODE.get(genre));
        }
        model.setGenre(genreStrBuilder.toString());

        model.setProgramUrl("https:" + description.getProgram_url());
        model.setEpisodeUrl("https:" + description.getProgram_url());

        if (description.getExtras() != null) {
            model.setExtras(
                    description.getExtras().getOndemand_program().getTitle()
                            + description.getExtras().getOndemand_episode().getTitle());
        } else {
            model.setExtras("その他の情報なし");
        }

        return model;
    }
}
