package com.example.engyousef.startjoe

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_ticket.view.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var listSongs = ArrayList<SongInfo>()
    var adapter:MySongAdapter?=null
    var mp:MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoadURLOnline()
        adapter=MySongAdapter(listSongs)
        lsListSongs.adapter=adapter
        var mytracking=mySongTrack()
        mytracking.start()
    }
    fun LoadURLOnline(){
        listSongs.add(SongInfo("Alay","Ahmed","http://server6.mp3quran.net/hamad/000.mp3"))
        listSongs.add(SongInfo("Alay","Ahmed","http://server6.mp3quran.net/hamad/001.mp3"))
        listSongs.add(SongInfo("Alay","Ahmed","http://server6.mp3quran.net/hamad/012.mp3"))
        listSongs.add(SongInfo("Alay","Ahmed","http://server6.mp3quran.net/hamad/013.mp3"))
        listSongs.add(SongInfo("Alay","Ahmed","http://server6.mp3quran.net/hamad/044.mp3"))
    }

    inner class MySongAdapter:BaseAdapter{

        var myListSong=ArrayList<SongInfo>()

        constructor(myListSong:ArrayList<SongInfo>):super(){
          this.myListSong=myListSong
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
           val myView = layoutInflater.inflate(R.layout.song_ticket,null)
           val Song = this.myListSong[position]
            myView.tvSongName.text = Song.Title
            myView.tvAuthor.text = Song.AuthorName

            myView.buPlay.setOnClickListener(View.OnClickListener {
                if(myView.buPlay.text.equals("Stop")){
                    mp!!.stop()
                    myView.buPlay.text = "Play"
                }else {
                    mp = MediaPlayer()
                    try {
                        mp!!.setDataSource(Song.SongURL)
                        mp!!.prepare()
                        mp!!.start()
                        myView.buPlay.text = "Stop"
                        sbProgress.max=mp!!.duration
                    } catch (ex: Exception) {
                    }
                }
            })

            return myView
        }

        override fun getItem(position: Int): Any {
            return this.myListSong[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return this.myListSong.size
        }

    }

    inner class mySongTrack():Thread(){

        override fun run(){
           while(true) {
               try {
                   Thread.sleep(1000)
               } catch (ex: Exception) {
               }

               runOnUiThread {
                   if (mp != null) {
                       sbProgress.progress = mp!!.currentPosition
                   }
               }
           }
        }
    }

}
