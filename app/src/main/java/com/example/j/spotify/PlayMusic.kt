package com.example.j.spotify

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

/**
 * Created by Romeo on 1/22/2018.
 */
class PlayMusic: Service(){
    var playingORnot = 0
    var nMediaPlayer:MediaPlayer?=null
    var currentPos:Int = 0
    var musicDatalist:ArrayList<String> = ArrayList()
    var mediaPlayer:MediaPlayer?=null
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        currentPos= intent!!.getIntExtra(CustomAdapter.MUSICTEMPOS,0)
        musicDatalist = intent.getStringArrayListExtra(CustomAdapter.MUSICLIST)

        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setDataSource(musicDatalist[currentPos])
        mediaPlayer!!.prepare()
        mediaPlayer!!.setOnPreparedListener{
        mediaPlayer!!.start()



        }
        return super.onStartCommand(intent, flags, startId)

    }

    fun playSong(nPosition:Int,musicList:ArrayList<String>) {


        nMediaPlayer = MediaPlayer()
        nMediaPlayer!!.setDataSource(musicList[nPosition])
        nMediaPlayer!!.prepare()
        nMediaPlayer!!.setOnPreparedListener{
            nMediaPlayer!!.start()
        playingORnot=1




    }

        }
    fun stopSong(){
        nMediaPlayer!!.stop()
}
    fun pauseSong(){
        nMediaPlayer!!.pause()


    }
    fun resumeSong(){
        nMediaPlayer!!.start()


    }
    fun getDuration():Int{
        return nMediaPlayer!!.duration

    }

    }


