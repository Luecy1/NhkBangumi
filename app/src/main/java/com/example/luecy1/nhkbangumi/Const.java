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
        GENRE_MAP_CODE.put("0000","定時・総合");
        GENRE_MAP_CODE.put("0001","天気");
        GENRE_MAP_CODE.put("0002","特集・ドキュメント");
        GENRE_MAP_CODE.put("0003","政治・国会");
        GENRE_MAP_CODE.put("0004","経済・市況");
        GENRE_MAP_CODE.put("0005","海外・国際");
        GENRE_MAP_CODE.put("0006","解説");
        GENRE_MAP_CODE.put("0007","討論・会談");
        GENRE_MAP_CODE.put("0008","報道特番");
        GENRE_MAP_CODE.put("0009","ローカル・地域");
        GENRE_MAP_CODE.put("0010","交通");
        GENRE_MAP_CODE.put("0015","その他");
        GENRE_MAP_CODE.put("0100","スポーツニュース");
        GENRE_MAP_CODE.put("0101","野球");
        GENRE_MAP_CODE.put("0102","サッカー");
        GENRE_MAP_CODE.put("0103","ゴルフ");
        GENRE_MAP_CODE.put("0104","その他の球技");
        GENRE_MAP_CODE.put("0105","相撲・格闘技");
        GENRE_MAP_CODE.put("0106","オリンピック・国際大会");
        GENRE_MAP_CODE.put("0107","マラソン・陸上・水泳");
        GENRE_MAP_CODE.put("0108","モータースポーツ");
        GENRE_MAP_CODE.put("0109","マリン・ウィンタースポーツ");
        GENRE_MAP_CODE.put("0110","競馬・公営競技");
        GENRE_MAP_CODE.put("0115","その他");
        GENRE_MAP_CODE.put("0200","芸能・ワイドショー");
        GENRE_MAP_CODE.put("0201","ファッション");
        GENRE_MAP_CODE.put("0202","暮らし・住まい");
        GENRE_MAP_CODE.put("0203","健康・医療");
        GENRE_MAP_CODE.put("0204","ショッピング・通販");
        GENRE_MAP_CODE.put("0205","グルメ・料理");
        GENRE_MAP_CODE.put("0206","イベント");
        GENRE_MAP_CODE.put("0207","番組紹介・お知らせ");
        GENRE_MAP_CODE.put("0215","その他");
        GENRE_MAP_CODE.put("0300","国内ドラマ");
        GENRE_MAP_CODE.put("0301","海外ドラマ");
        GENRE_MAP_CODE.put("0302","時代劇");
        GENRE_MAP_CODE.put("0315","その他");
        GENRE_MAP_CODE.put("0400","国内ロック・ポップス");
        GENRE_MAP_CODE.put("0401","海外ロック・ポップス");
        GENRE_MAP_CODE.put("0402","クラシック・オペラ");
        GENRE_MAP_CODE.put("0403","ジャズ・フュージョン");
        GENRE_MAP_CODE.put("0404","歌謡曲・演歌");
        GENRE_MAP_CODE.put("0405","ライブ・コンサート");
        GENRE_MAP_CODE.put("0406","ランキング・リクエスト");
        GENRE_MAP_CODE.put("0407","カラオケ・のと゛自慢");
        GENRE_MAP_CODE.put("0408","民謡・邦楽");
        GENRE_MAP_CODE.put("0409","童謡・キッズ");
        GENRE_MAP_CODE.put("0410","民族音楽・ワールドミュージック");
        GENRE_MAP_CODE.put("0415","その他");
        GENRE_MAP_CODE.put("0500","クイズ");
        GENRE_MAP_CODE.put("0501","ゲーム");
        GENRE_MAP_CODE.put("0502","トークバラエティ");
        GENRE_MAP_CODE.put("0503","お笑い・コメディ");
        GENRE_MAP_CODE.put("0504","音楽バラエティ");
        GENRE_MAP_CODE.put("0505","旅バラエティ");
        GENRE_MAP_CODE.put("0506","料理バラエティ");
        GENRE_MAP_CODE.put("0515","その他");
        GENRE_MAP_CODE.put("0600","洋画");
        GENRE_MAP_CODE.put("0601","邦画");
        GENRE_MAP_CODE.put("0602","アニメ");
        GENRE_MAP_CODE.put("0615","その他");
        GENRE_MAP_CODE.put("0700","国内アニメ");
        GENRE_MAP_CODE.put("0701","海外アニメ");
        GENRE_MAP_CODE.put("0702","特撮");
        GENRE_MAP_CODE.put("0715","その他");
        GENRE_MAP_CODE.put("0800","社会・時事");
        GENRE_MAP_CODE.put("0801","歴史・紀行");
        GENRE_MAP_CODE.put("0802","自然・動物・環境");
        GENRE_MAP_CODE.put("0803","宇宙・科学・医学");
        GENRE_MAP_CODE.put("0804","カルチャー・伝統文化");
        GENRE_MAP_CODE.put("0805","文学・文芸");
        GENRE_MAP_CODE.put("0806","スポーツ");
        GENRE_MAP_CODE.put("0807","ドキュメンタリー全般");
        GENRE_MAP_CODE.put("0808","インタビュー・討論");
        GENRE_MAP_CODE.put("0815","その他");
        GENRE_MAP_CODE.put("0900","現代劇・新劇");
        GENRE_MAP_CODE.put("0901","ミュージカル");
        GENRE_MAP_CODE.put("0902","ダンス・バレエ");
        GENRE_MAP_CODE.put("0903","落語・演芸");
        GENRE_MAP_CODE.put("0904","歌舞伎・古典");
        GENRE_MAP_CODE.put("0915","その他");
        GENRE_MAP_CODE.put("1000","旅・釣り・アウトドア");
        GENRE_MAP_CODE.put("1001","園芸・ペット・手芸");
        GENRE_MAP_CODE.put("1002","音楽・美術・工芸");
        GENRE_MAP_CODE.put("1003","囲碁・将棋");
        GENRE_MAP_CODE.put("1004","麻雀・パチンコ");
        GENRE_MAP_CODE.put("1005","車・オートバイ");
        GENRE_MAP_CODE.put("1006","コンピュータ・TVゲーム");
        GENRE_MAP_CODE.put("1007","会話・語学");
        GENRE_MAP_CODE.put("1008","幼児・小学生");
        GENRE_MAP_CODE.put("1009","中学生・高校生");
        GENRE_MAP_CODE.put("1010","大学生・受験");
        GENRE_MAP_CODE.put("1011","生涯教育・資格");
        GENRE_MAP_CODE.put("1012","教育問題");
        GENRE_MAP_CODE.put("1015","その他");
        GENRE_MAP_CODE.put("1100","高齢者");
        GENRE_MAP_CODE.put("1101","障害者");
        GENRE_MAP_CODE.put("1102","社会福祉");
        GENRE_MAP_CODE.put("1103","ボランティア");
        GENRE_MAP_CODE.put("1104","手話");
        GENRE_MAP_CODE.put("1105","文字(字幕)");
        GENRE_MAP_CODE.put("1106","音声解説");
        GENRE_MAP_CODE.put("1115","その他");
        GENRE_MAP_CODE.put("1400","BS/地上テﾞシﾞタル放送用番組付属情報");
        GENRE_MAP_CODE.put("1401","広帯域 CS テﾞシﾞタル放送用拡張");
        GENRE_MAP_CODE.put("1403","サーハﾞー型番組付属情報");
        GENRE_MAP_CODE.put("1404","IP 放送用番組付属情報");
        GENRE_MAP_CODE.put("1515","その他");
    }
}
