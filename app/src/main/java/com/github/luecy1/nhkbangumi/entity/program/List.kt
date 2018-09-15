package com.github.luecy1.nhkbangumi.entity.program

import com.github.luecy1.nhkbangumi.entity.common.Program

/**
 * http://api-portal.nhk.or.jp/doc-request
 */
class List {

    // tv
    var g1: kotlin.collections.List<Program>? = null
    var g2: kotlin.collections.List<Program>? = null
    var e1: kotlin.collections.List<Program>? = null
    var e2: kotlin.collections.List<Program>? = null
    var e3: kotlin.collections.List<Program>? = null
    var e4: kotlin.collections.List<Program>? = null
    var s1: kotlin.collections.List<Program>? = null
    var s2: kotlin.collections.List<Program>? = null
    var s3: kotlin.collections.List<Program>? = null
    var s4: kotlin.collections.List<Program>? = null

    // radio
    var r1: kotlin.collections.List<Program>? = null
    var r2: kotlin.collections.List<Program>? = null
    var r3: kotlin.collections.List<Program>? = null

    // netradio
    var n1: kotlin.collections.List<Program>? = null
    var n2: kotlin.collections.List<Program>? = null
    var n3: kotlin.collections.List<Program>? = null
}
