package com.example.luecy1.nhkbangumi;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by you on 2017/12/27.
 */

public class Const {

    public final static String[] GENRE_CODE = new String[]{
            "0000",	//ニュース／報道(定時・総合)
            "0100",	//スポーツ(スポーツニュース)
            "0205",	//情報／ワイドショー(グルメ・料理)
            "0300",	//ドラマ(国内ドラマ)
            "0409",	//音楽(童謡・キッズ)
            "0502",	//バラエティ(トークバラエティ)
            "0600",	//映画(洋画)
            "0700",	//アニメ／特撮(国内アニメ)
            "0800",	//ドキュメンタリー／教養(社会・時事)
            "0903",	//劇場／公演(落語・演芸)
            "1000",	//趣味／教育(旅・釣り・アウトドア)
            "1100"	//福祉(高齢者)
    };

    public final static Map<String, String> GENRE_MAP_CODE = new LinkedHashMap<>();
    static {
        GENRE_MAP_CODE.put("0000","ニュース／報道(定時・総合)");
        GENRE_MAP_CODE.put("0100","スポーツ(スポーツニュース)");
        GENRE_MAP_CODE.put("0205","情報／ワイドショー(グルメ・料理)");
        GENRE_MAP_CODE.put("0300","ドラマ(国内ドラマ)");
        GENRE_MAP_CODE.put("0409","音楽(童謡・キッズ)");
        GENRE_MAP_CODE.put("0502","バラエティ(トークバラエティ)");
        GENRE_MAP_CODE.put("0600","映画(洋画)");
        GENRE_MAP_CODE.put("0700","アニメ／特撮(国内アニメ)");
        GENRE_MAP_CODE.put("0800","ドキュメンタリー／教養(社会・時事)");
        GENRE_MAP_CODE.put("0903","劇場／公演(落語・演芸)");
        GENRE_MAP_CODE.put("1000","趣味／教育(旅・釣り・アウトドア)");
        GENRE_MAP_CODE.put("1100","福祉(高齢者)");
    }

}
