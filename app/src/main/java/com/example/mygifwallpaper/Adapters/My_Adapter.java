package com.example.mygifwallpaper.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygifwallpaper.Activities.imgViewActivity;
import com.example.mygifwallpaper.ModelClasses.WallModel;
import com.example.mygifwallpaper.R;


import java.util.ArrayList;

public class My_Adapter extends RecyclerView.Adapter<My_Adapter.viewHolder> {
    Context context;
    ArrayList<WallModel> Models;
    public static int posi;
    public static Drawable d;

    public My_Adapter(Context context, ArrayList<WallModel> Models ) {
        this.context = context;
        this.Models = Models;
     }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_data,parent,false);
        return new viewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        WallModel model = Models.get(position);
        holder.img1.setImageDrawable(model.getImg1());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = Models.get(position).getImg1();

                Intent intent = new Intent(context, imgViewActivity.class);
                intent.putExtra("resId", String.valueOf(Models.get(position).getImg1()));  //here
                intent.putExtra("pos",position);
                posi = position;

                context.startActivity(intent);

            }
        });



         holder.setDetials(model);


    }

    @Override
    public int getItemCount() {
        return Models.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView img1;
        TextView name;
         public viewHolder(@NonNull View itemView) {
            super(itemView);

             img1 = itemView.findViewById(R.id.image);
             name = itemView.findViewById(R.id.name);
         }
        void setDetials(WallModel model){
             img1.setImageDrawable(model.getImg1());

        }


    }
}
