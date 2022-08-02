package com.example.hayavadhanaankita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
public MediaPlayer M;
public AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<song> songs=new ArrayList<>();
        songs.add(new song("GUCHA 1",R.raw.guchaone));
        songs.add(new song("GUCHA 2",R.raw.guchatwo));
        songs.add(new song("GUCHA 3",R.raw.gucha3));
        songs.add(new song("GUCHA 4",R.raw.gucha4));
        songs.add(new song("GUCHA 5",R.raw.gucha5));
        songs.add(new song("GUCHA 6",R.raw.gucha6));
        songs.add(new song("GUCHA 7",R.raw.gucha7));
        songs.add(new song("GUCHA 8",R.raw.gucha8));
        songs.add(new song("GUCHA 9",R.raw.gucha9));
        songs.add(new song("GUCHA 10",R.raw.gucha10));
        songs.add(new song("GUCHA 11",R.raw.gucha11));
        songAdapter myAdapter=new songAdapter(this,songs);
        ListView list=(ListView)findViewById(R.id.songList);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        TextView displayText=(TextView)findViewById(R.id.textView2);
                        song s=songs.get(position);
                        displayText.setText(s.getSongName());

                        onRelease();
                        M=MediaPlayer.create(MainActivity.this,s.getSongResourc());
                        M.start();
                        final SeekBar myProgress=(SeekBar)findViewById(R.id.songProgress);
                        myProgress.setMax(M.getDuration());

                        new Timer().scheduleAtFixedRate(
                                new TimerTask(){
                                    @Override
                                    public void run(){
                                        myProgress.setProgress(M.getCurrentPosition());
                                    }
                                },0,10000
                        );

                        myProgress.setOnSeekBarChangeListener(
                                new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        M.seekTo(progress);
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {

                                    }
                                }
                        );

                        M.setOnCompletionListener(
                                new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        onRelease();
                                    }
                                }
                        );

                    }
                }
        );


        SeekBar myVol=(SeekBar)findViewById(R.id.volume);
        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVol=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        myVol.setMax(maxVol);
        myVol.setProgress(curVol);
        myVol.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );



        Button pauseButton=(Button)findViewById(R.id.button4);
        pauseButton.setOnClickListener(
                new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        M.pause();
                    }
                }
        );

        list.setAdapter(myAdapter);

    }





    public void onRelease(){
        if(M!=null){
        M.release();
        M=null;
        }
    }


}