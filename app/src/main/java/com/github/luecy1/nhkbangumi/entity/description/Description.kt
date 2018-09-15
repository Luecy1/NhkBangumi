package com.github.luecy1.nhkbangumi.entity.description

import com.github.luecy1.nhkbangumi.entity.common.Area
import com.github.luecy1.nhkbangumi.entity.common.Extra
import com.github.luecy1.nhkbangumi.entity.common.Logo
import com.github.luecy1.nhkbangumi.entity.common.Service

class Description {

    /**
     * 番組ID
     */
    var id: String = ""

    /**
     * 番組イベントID
     */
    var event_id: String = ""

    /**
     * 放送開始日時（YYYY-MM-DDTHH:mm:ssZ形式）
     */
    var start_time: String? = null

    /**
     * 放送終了日時（YYYY-MM-DDTHH:mm:ssZ形式）
     */
    var end_time: String? = null

    /**
     * Areaオブジェクト
     */
    var area: Area? = null

    /**
     * Serviceオブジェクト
     */
    var service: Service? = null

    /**
     * 番組名
     */
    var title: String? = null

    /**
     * 番組内容
     */
    var subtitle: String? = null

    /**
     * 番組詳細
     */
    var content: String? = null

    /**
     * 出演者
     */
    var act: String? = null

    /**
     * 番組ジャンル
     */
    var genres: List<String>? = null

    /**
     * 番組ロゴ画像
     */
    var program_logo: Logo? = null

    /**
     * 番組サイトURL（番組単位）
     */
    var program_url: String? = null

    /**
     * 番組サイトURL（放送回単位）
     */
    var episode_url: String? = null

    /**
     * 番組に関連するハッシュタグ
     */
    var hashtags: List<String>? = null

    /**
     * 拡張情報（Extraオブジェクト）
     */
    var extras: Extra? = null
}
