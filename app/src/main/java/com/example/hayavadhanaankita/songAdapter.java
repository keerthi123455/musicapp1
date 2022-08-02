package com.example.hayavadhanaankita;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class songAdapter extends ArrayAdapter<song> {
    public songAdapter(Activity context, ArrayList<song> songs){
        super(context,0,songs);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.custom_song,parent,false);
        }
        song s=getItem(position);

        TextView myText=(TextView)listItemView.findViewById(R.id.textView);
        myText.setText(s.getSongName());
        return listItemView;
    }
}
