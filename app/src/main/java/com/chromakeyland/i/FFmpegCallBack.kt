package com.chromakeyland.i



interface FFmpegCallBack {
    fun process(logMessage: String){}
    fun success(){}
    fun cancel(){}
    fun failed(){}
}