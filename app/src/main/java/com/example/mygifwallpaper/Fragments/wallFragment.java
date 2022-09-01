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

import com.example.mygifwallpaper.Adapters.My_Adapter;
import com.example.mygifwallpaper.ModelClasses.WallModel;
import com.example.mygifwallpaper.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class wallFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    public static ArrayList<WallModel> arrayList;


    String name[] = {"001", "002", "003", "004", "005", "006", "007", "008", "008 ", "009","0010 "," 0011","0012","0013","0014"};
   // int image[] = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic15,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,R.drawable.pic10,R.drawable.pic11,R.drawable.pic12,R.drawable.pic13,R.drawable.pic14};
    My_Adapter myAdapter;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wall, container, false);
        recyclerView =  view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        String[] images = new String[0];
        try {
            images = getContext().getAssets().list("wallpapers");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < images.length; i++) {
            WallModel model = new WallModel();
        ArrayList<String> listImages = new ArrayList<String>(Arrays.asList(images));
            InputStream inputstream= null;
            try {
                inputstream = getContext().getAssets().open("wallpapers/"
                        +listImages.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Drawable drawable = Drawable.createFromStream(inputstream, null);
            model.setImg1(drawable);
            arrayList.add(model);
        }

        myAdapter = new My_Adapter(getActivity(),arrayList);
        recyclerView.setAdapter(myAdapter);
        return view;
    }


}