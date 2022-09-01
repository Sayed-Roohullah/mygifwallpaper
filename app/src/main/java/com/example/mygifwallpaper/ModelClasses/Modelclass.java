package com.example.mygifwallpaper.ModelClasses;

 import android.graphics.drawable.Drawable;


public class Modelclass {
    Drawable image;

    public Modelclass(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Modelclass() {

    }
}