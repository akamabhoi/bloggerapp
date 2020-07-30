package com.example.hp.blogapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {


    public ArrayList<TextModel> mylist;

    private Context context;
    private LayoutInflater inflater;

    //create constructor to initializ context and data sent from main activity.
    public Adapter(ArrayList<TextModel> mylist) {
        this.mylist=mylist;

    }

    @Override
    public Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_search, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        TextModel textModel = mylist.get(position);
        textModel.setTitle(textModel.getTitle());
        ((MyHolder) holder).texttitle.setText(textModel.getTitle());

        TextModel textModel1 = mylist.get(position);
        textModel.setContent(textModel1.getContent());
        ((MyHolder) holder).textData.setText(textModel1.getContent());

     //  holder.textData.setText(mylist.get(position));
     //  holder.texttitle.setText(mylist.get(position));

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView texttitle, textData;

        //contructor for getting reference to the widget
        public MyHolder(View itemView) {
            super(itemView);

            textData = (TextView) itemView.findViewById(R.id.titletv);
            texttitle = (TextView) itemView.findViewById(R.id.conttv);


        }


    }
}