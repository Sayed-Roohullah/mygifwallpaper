package com.example.mygifwallpaper.ModelClasses;

import android.graphics.drawable.Drawable;

public class WallModel {
    Drawable img1;

    public WallModel(Drawable img1) {
        this.img1 = img1;
     }

    public WallModel() {

    }

    public Drawable getImg1() {
        return img1;
    }

    public void setImg1(Drawable img1) {
        this.img1 = img1;
    }


 }
