package com.example.j.spotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import java.sql.Time
import java.util.concurrent.TimeUnit

/**
 * Created by Romeo on 12/17/2017.
 */
class CustomAdapter(val songList:ArrayList<Song>,context:Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
var mContext = context
var allMusicList:ArrayList<String> = ArrayList()
var oldPos=0

    companion object {
        val MUSICLIST = "musiclist"
        val MUSICTEMPOS = "pos"

    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.activity_song,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val song: Song = songList[position]
        var myDuration = toMandS(songList[position].duration.toLong())




        var prevHolder = holder
        holder?.title?.text = song.title
        holder?.album?.text =song.album
        holder?.cardView?.setOnClickListener (object: View.OnClickListener{
            override fun onClick(v: View) {
                for (i in 0 until songList.size) {
                    allMusicList.add(songList[i].mPath)

                }


                Log.i("allmusiclist", allMusicList.toString())

                MainActivity.currentPos = position


                if (MainActivity.myPlayMusic.playingORnot.equals(1)) {
                    MainActivity.myPlayMusic.stopSong()
                }




                MainActivity.myPlayMusic.playSong(position, allMusicList)

                deselect(prevHolder)
                holder.title.setTextColor(Color.parseColor("#1DB853"))


                MainActivity.songTitle?.setText(holder.title.text)

                oldPos = position


            }
        })

    }



    fun toMandS(mills:Long):String {
        var duration = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(mills),
                TimeUnit.MILLISECONDS.toSeconds(mills) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(mills))
        )
        return duration
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        val title = itemView.findViewById<TextView>(R.id.title_TV)
        val album = itemView.findViewById<TextView>(R.id.album_TV)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)




    }
    fun deselect(pholder: ViewHolder?) {

        val ssong: Song = songList[oldPos]

        pholder?.title?.setTextColor(Color.parseColor("#FFFFFF"))
    }
}