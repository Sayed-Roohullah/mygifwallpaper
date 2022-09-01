package com.example.mygifwallpaper.Activities;

import static com.example.mygifwallpaper.Adapters.My_Adapter.d;
import static com.example.mygifwallpaper.Adapters.My_Adapter.posi;
import static com.example.mygifwallpaper.Fragments.wallFragment.arrayList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mygifwallpaper.Adapters.My_Adapter;
import com.example.mygifwallpaper.ModelClasses.WallModel;
import com.example.mygifwallpaper.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class imgViewActivity extends AppCompatActivity {
    ImageView Screenimage,setbackground,favImage,SaveImage;
    private InterstitialAd mInterstitialAd;
    int pos;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int position;

    private static final int EXTERNAL_STORAGE_PERMISSION_CODE = 1;

    My_Adapter my_adapter;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_view);
        my_adapter = new My_Adapter(this,arrayList);
        builder = new AlertDialog.Builder(this);
        Screenimage = findViewById(R.id.imageView2);
        setbackground= findViewById(R.id.setas);
        favImage= findViewById(R.id.fav);
        SaveImage = findViewById(R.id.saveas);



         MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            position = bundle.getInt("pos",888);
            Screenimage.setImageDrawable(d);
            Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();

        }

        position = posi;


        preferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        editor = preferences.edit();
        pos =preferences.getInt(String.valueOf(arrayList.get(position).getImg1()),999);
        Toast.makeText(this, ""+pos, Toast.LENGTH_SHORT).show();
        if(pos!=999){
             favImage.setImageResource(R.drawable.ic_baseline_favorite_24);
        }else{

            favImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);

        }

        SaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterstitialAd.show();


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, EXTERNAL_STORAGE_PERMISSION_CODE);
                    }
                    else {
                        saveImage();
                    }
                }

            }
        });


        setbackground.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Drawable drawable = Screenimage.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                try {
                    myWallpaperManager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(), "Background Set Successfully", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        favImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {

                set();
                mInterstitialAd.show();

            }
        });

    }



    private void saveImage() {
        Drawable drawable = Screenimage.getDrawable();
         Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        String time = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(System.currentTimeMillis());
        File path = Environment.getExternalStorageDirectory();

        File dir = new File(path + "/DCIM");
        dir.mkdir();
        String imagename = time + ".JPEG";
        File file = new File(dir, imagename);
        OutputStream out;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(imgViewActivity.this, "Image save in DCIM", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Toast.makeText(imgViewActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case EXTERNAL_STORAGE_PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0]==
                        PackageManager.PERMISSION_GRANTED){

                }
                else {
                    Toast.makeText(imgViewActivity.this, "Permission enable", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void set() {
        SharedPreferences preferences = getSharedPreferences("Prefs",Context.MODE_PRIVATE);
        editor = preferences.edit();

        posi = position;
        pos =preferences.getInt(String.valueOf(arrayList.get(position).getImg1()),999);

        if(pos!=999){

            WallModel model = arrayList.get(position);
            preferences.getInt("posi",posi);

            preferences.edit().remove(String.valueOf(arrayList.get(position).getImg1())).apply();
            //favImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            // my_adapter.notifyItemRemoved(Integer.parseInt(model.getImg1().toString()));

            builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

            builder.setMessage("Do you want to remove from favorite ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            favImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                            my_adapter.notifyItemRemoved(position);

                            Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Un_Favorite Alert");
            alert.show();

            Toast.makeText(imgViewActivity.this, "Image Removed from Favorite", Toast.LENGTH_SHORT).show();

        }else{

            WallModel model = arrayList.get(position);
            editor.putInt(String.valueOf(arrayList.get(position).getImg1()),position);
            // editor.putInt(""+position,position);
            favImage.setImageResource(R.drawable.ic_baseline_favorite_24);
            editor.commit();
            Toast.makeText(imgViewActivity.this, "Image Set to Favorite", Toast.LENGTH_SHORT).show();


        }
    }


}
