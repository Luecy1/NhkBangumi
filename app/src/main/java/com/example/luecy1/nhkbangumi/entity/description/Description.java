package com.example.luecy1.nhkbangumi.entity.description;

import com.example.luecy1.nhkbangumi.entity.common.Area;
import com.example.luecy1.nhkbangumi.entity.common.Extra;
import com.example.luecy1.nhkbangumi.entity.common.Logo;
import com.example.luecy1.nhkbangumi.entity.common.Service;

import java.util.List;

public class Description {

    private String id;
    private String event_id;
    private String start_time;
    private String end_time;
    private Area area;
    private Service service;
    private String title;
    private String subtitle;
    private String content;
    private String act;
    private List<String> genres = null;
    private Logo program_logo;
    private String program_url;
    private String episode_url;
    private List<String> hashtags = null;
    private Extra extras;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Logo getProgram_logo() {
        return program_logo;
    }

    public void setProgram_logo(Logo program_logo) {
        this.program_logo = program_logo;
    }

    public String getProgram_url() {
        return program_url;
    }

    public void setProgram_url(String program_url) {
        this.program_url = program_url;
    }

    public String getEpisode_url() {
        return episode_url;
    }

    public void setEpisode_url(String episode_url) {
        this.episode_url = episode_url;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public Extra getExtras() {
        return extras;
    }

    public void setExtras(Extra extras) {
        this.extras = extras;
    }
}
