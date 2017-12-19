package com.example.j.spotify

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Romeo on 12/17/2017.
 */
class CustomAdapter(val songList:ArrayList<Song>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return songList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title_TV)
        val album = itemView.findViewById<TextView>(R.id.album_TV)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.activity_song,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val song: Song = songList[position]
        holder?.title?.text = song.title
        holder?.album?.text =song.album

        holder?.cardView?.setOnClickListener (object: View.OnClickListener{
            override fun onClick(v: View){

              /*  var i=0
                while(i<songList.size){

                    holder?.title?.text = songList[i].title
                    holder?.title.setTextColor(Color.parseColor("#FFFFFF"))
                    i++
                }*/

                holder?.title.setTextColor(Color.parseColor("#1DB853"))

                newInstance(holder?.title?.text.toString())
/*
                val bundle = Bundle()
                val myMessage = holder?.title.text.toString()
                bundle.putString("123", myMessage)
                val fragInfo = Fragment_Song()
                fragInfo.setArguments(bundle)
                transaction.replace(R.id.fragment_single, fragInfo)
                transaction.commit()

                */


            }

        })


    }

    fun newInstance(titless: String): Fragment_Song {
        val f = Fragment_Song()
        // Supply index input as an argument.
        val args = Bundle()
        args.putString("123",titless )
        f.setArguments(args)
        return f
    }
}