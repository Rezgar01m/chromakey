package com.chromakeyland.i

import com.google.android.exoplayer2.SimpleExoPlayer
import net.kibotu.fastexoplayerseeker.SeekPositionEmitter
import net.kibotu.fastexoplayerseeker.seekWhenReady

class Fst {
    val s=SeekPositionEmitter()
    fun set(sm:SimpleExoPlayer){

        sm.seekWhenReady(s)

    }
    fun seek(l:Long){
        s.seekFast(l)
    }
}