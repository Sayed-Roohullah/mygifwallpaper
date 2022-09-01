package com.example.mygifwallpaper.Activities;

import static com.example.mygifwallpaper.Adapters.My_Adapter.posi;
import static com.example.mygifwallpaper.Fragments.wallFragment.arrayList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mygifwallpaper.Adapters.My_Adapter;
import com.example.mygifwallpaper.ModelClasses.WallModel;
import com.example.mygifwallpaper.R;

import java.util.ArrayList;

public class favoriteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    My_Adapter adapter;
    WallModel model;
    public static ArrayList<WallModel> modelArrayList;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        modelArrayList = new ArrayList<>();
        Create();
        recyclerView = findViewById(R.id.favrecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new My_Adapter(this, modelArrayList);
        recyclerView.setAdapter(adapter);


    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void Create() {
        SharedPreferences preferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < arrayList.size(); i++) {
             int pos =preferences.getInt(String.valueOf(arrayList.get(i).getImg1()),999);
            Toast.makeText(favoriteActivity.this, ""+pos, Toast.LENGTH_SHORT).show();

            if(pos!=999){
                model = new WallModel();
                model.setImg1( arrayList.get(i).getImg1());
                modelArrayList.add(model);

                pos = posi;
                editor.putInt(String.valueOf(arrayList.get(posi).getImg1()),pos);
                editor.commit();

            }


        }

    }
}