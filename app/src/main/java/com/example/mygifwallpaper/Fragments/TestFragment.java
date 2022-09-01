package com.example.mygifwallpaper.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mygifwallpaper.Adapters.MyAdapter;
import com.example.mygifwallpaper.ModelClasses.Modelclass;
import com.example.mygifwallpaper.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;


public class TestFragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    MyAdapter adapter;
    ArrayList<Modelclass> modelarray;
    String[] images;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test, container, false);
        recyclerView =  view.findViewById(R.id.recycle);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        modelarray = new ArrayList<>();
        try {
            images =getContext().getAssets().list("images");

        }catch (IOException e){
            e.printStackTrace();

        }

        for (int i = 0; i <images.length; i++) {
            Modelclass itemModel = new Modelclass();
            InputStream inputstream = null;
            try {
                inputstream = getContext().getAssets().open("images/"
                        +images[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Drawable drawable = Drawable.createFromStream(inputstream, null);
            itemModel.setImage(drawable);
            modelarray.add(itemModel);
        }
        adapter = new MyAdapter(getContext(),modelarray);
        recyclerView.setAdapter(adapter);
   return view;
    }

}