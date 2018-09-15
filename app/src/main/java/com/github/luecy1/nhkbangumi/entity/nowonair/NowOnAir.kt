package com.github.luecy1.nhkbangumi.entity.nowonair

import com.github.luecy1.nhkbangumi.entity.common.Program

class NowOnAir {

    /**
     * 前に放送した番組
     */
    var previous: Program? = null

    /**
     * 現在放送中の番組
     */
    var present: Program? = null

    /**
     * 次に放送する番組
     */
    var following: Program? = null
}
