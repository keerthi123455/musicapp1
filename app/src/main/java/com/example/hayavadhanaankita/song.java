package com.example.hayavadhanaankita;

public class song {

    private String mSongName;
    private int mSongResourceId;

    public song(String songName,int SongResourceId){
        mSongName=songName;
        mSongResourceId=SongResourceId;
    }

    public String getSongName(){
        return mSongName;
    }
    public int getSongResourc(){
        return mSongResourceId;
    }

}
