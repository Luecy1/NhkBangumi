package com.github.luecy1.nhkbangumi.entity.common

class Program {

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
    var start_time: String = ""

    /**
     * 放送終了日時（YYYY-MM-DDTHH:mm:ssZ形式）
     */
    var end_time: String = ""

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

}
