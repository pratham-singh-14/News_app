package com.example.myapplication;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Articles;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Articles> articlesList;

    public Adapter(Context context, List<Articles> articlesList) {
        this.context = context;
        this.articlesList = articlesList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
          Articles  a =articlesList.get(position);
          holder.tvTitle.setText(a.getTitle());
          holder.tvSource.setText(a.getSource().getName());
       //   holder.tvDate.setText(dateTime(a.getPublishedAt()));//"\u2022"+
        holder.tvDate.setText(a.getAuthor());
          String imgUrl=a.getUrlToImage();
          String url = a.getUrl();
        Picasso.with(context).load(imgUrl).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailedActivity.class);
                intent.putExtra("title",a.getTitle());
                intent.putExtra("image",a.getUrlToImage());
                intent.putExtra("source",a.getSource().getName());
                intent.putExtra("time",dateTime(a.getPublishedAt()));
                intent.putExtra("url",url);
                intent.putExtra("desc",a.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvSource,tvDate;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

          tvTitle=itemView.findViewById(R.id.tvId);
          tvDate=itemView.findViewById(R.id.tvDate);
          tvSource=itemView.findViewById(R.id.tvSource);
          imageView=itemView.findViewById(R.id.image1);
          cardView=itemView.findViewById(R.id.cardView);


        }
    }

    public String dateTime(String t)
    {
        PrettyTime prettyTime=new PrettyTime(new Locale(getCounty()));
        String time = null;
        try
        {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date=simpleDateFormat.parse(t);
            time=prettyTime.format(date);
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
        return time;
    }
    public String getCounty()
    {
        Locale locale=Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }

}
