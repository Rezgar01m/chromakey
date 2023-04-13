package com.chromakeyland.i

inline class Duration(val millis:Long){
    val seconds:Float
    get() = millis/100F
}

data class PlaybackInfo(val position: Long, val window: Int)