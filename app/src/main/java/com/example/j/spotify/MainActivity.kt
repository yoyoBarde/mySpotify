package com.example.j.spotify

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.Toolbar
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
       supportActionBar?.setCustomView(R.layout.toolbar)
       supportActionBar?.setTitle("Top Hits Philippines")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false  )
        val mySong = ArrayList<Song>()

        mySong.add(Song("Titibo-Tibo","Moira Dela Torre.Himig Handog 2017"))
        mySong.add(Song("Havana","Camila Cabello, Young Thug.Havana"))
        mySong.add(Song("Young Dumb & Broke","Khalid.Anerucan Teen"))
        mySong.add(Song("What Lovers DO (feat. SZA)","Maroon 5, SZA.Red Pill Blues (Deluxe)"))
        mySong.add(Song("Perfect","Ed Sheeran.+(Deluxe)"))
        mySong.add(Song("Super Far","Lany.Lany"))
        mySong.add(Song("Too Good At Goodbyes","Sam Smith"))

        var adapter = CustomAdapter(mySong)
        recyclerView.adapter = adapter


    }
}
