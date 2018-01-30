package com.example.j.spotify

import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_fragment__song.*
import kotlinx.android.synthetic.main.toolbar.view.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    companion object {
        var songTitle:TextView?=null
        var myPlayMusic = PlayMusic()
        var currentPos = 0
        val PERMISSION_REQUEST_CODE = 12
        var title = "empty"
        var duration:Int?=null
        var myseekBar:SeekBar?=null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        duration = 5;
        songTitle = findViewById<TextView>(R.id.fragment_TV_title)


        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.toolbar)
        supportActionBar?.setTitle("Top Hits Philippines")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE)

        } else {
            val mySong = ArrayList<Song>()
            var songCursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null, null, null, null)
            while (songCursor != null && songCursor.moveToNext()) {
                var songName = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                var songDuration = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                var media_path = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                var album = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))

                mySong.add(Song(songName, album, songDuration, media_path))

            }

            var adapter = CustomAdapter(mySong, applicationContext)
            recyclerView.adapter = adapter


        }
        var playRes = R.drawable.ic_play
        var pauseRes = R.drawable.ic_pause

        var pauseORplay = findViewById<ImageView>(R.id.playPause_btn)
        var currentButtonHolder = R.drawable.ic_pause
        playPause_btn.setOnClickListener({

            if (currentButtonHolder.equals(pauseRes)) {
                pauseORplay.setImageResource(playRes)
                currentButtonHolder = playRes

                myPlayMusic.pauseSong()
            } else if (currentButtonHolder.equals(playRes)) {
                pauseORplay.setImageResource(pauseRes)
                currentButtonHolder = pauseRes
                myPlayMusic.resumeSong()

            }

        })


    }



        fun loadData(){


        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == PERMISSION_REQUEST_CODE){

            if(grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(applicationContext,"Permission Granted",Toast.LENGTH_SHORT).show()


            }
        }
    }




}
