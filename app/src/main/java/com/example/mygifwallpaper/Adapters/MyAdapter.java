package com.example.mygifwallpaper.Adapters;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygifwallpaper.GIFWallpaperService;
import com.example.mygifwallpaper.ModelClasses.Modelclass;
import com.example.mygifwallpaper.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {
   Context context;
   ArrayList<Modelclass> arrayList;
   public static  int possi;

    public MyAdapter(Context context, ArrayList<Modelclass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public MyAdapter(GIFWallpaperService context) {
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gif_data,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        Modelclass model = arrayList.get(position);

         holder.images.setImageDrawable(model.getImage());

        holder.images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                        new ComponentName(context, GIFWallpaperService.class));
                intent.putExtra("resId",position);
                possi= position;

               context.startActivity(intent);
            }
        });

        holder.setDetials(model);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView images;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.image);
        }

        @SuppressLint("ResourceType")
        public void setDetials(Modelclass model) {
            images.setImageDrawable(model.getImage());
          }
    }
}
