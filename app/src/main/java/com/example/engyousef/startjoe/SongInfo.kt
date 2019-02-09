package com.example.engyousef.startjoe

import android.service.quicksettings.Tile

public class SongInfo{
    var Title:String?=null
    var AuthorName:String?=null
    var SongURL:String?=null
    constructor(Title:String,AuthorName:String,SongURL:String){
        this.Title=Title
        this.AuthorName=AuthorName
        this.SongURL=SongURL
    }
}