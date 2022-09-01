package com.example.mygifwallpaper;



 import static com.example.mygifwallpaper.Adapters.MyAdapter.possi;

import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.io.InputStream;


public class GIFWallpaperService  extends WallpaperService {

     @Override
    public Engine onCreateEngine() {

          String [] images = new String[0];
         try {
             images = getAssets().list("images");
         } catch (IOException e) {
             e.printStackTrace();
         }
         InputStream inputstream = null;
         try {
             inputstream = getAssets().open("images/"
                     +images[possi]);
         } catch (IOException e) {
             e.printStackTrace();
         }
            Movie movie = Movie.decodeStream(inputstream);
         return new GIFWallpaperEngine(movie);


     }




    private class GIFWallpaperEngine extends Engine {

        private final int frameDuration = 20;
        private SurfaceHolder holder;
        private Movie movie;
        private boolean visible;
        private Handler handler;

        public GIFWallpaperEngine(Movie movie) {
            //A Handler allows you to send and process Message and Runnable objects associated
            //with a thread's MessageQueue
            this.movie = movie;
            handler = new Handler();
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {

            super.onCreate(surfaceHolder);
            this.holder = surfaceHolder;

        }

        //The Runnable interface should be implemented by any class whose instances are intended to
        // be executed by a thread. The class must define a method of no arguments called run.
        private Runnable drawGIF = new Runnable() {
            public void run() {
                draw();
            }
        };


        private void draw() {
            if (visible) {
                Canvas canvas = holder.lockCanvas();
                canvas.save();
                // Adjust size and position so that
                // the image looks good on your screen
                canvas.scale(2f, 2f);
                movie.draw(canvas, -60, 0);
                canvas.restore();
                holder.unlockCanvasAndPost(canvas);
                movie.setTime((int) (System.currentTimeMillis() % movie.duration()));

                handler.removeCallbacks(drawGIF);
                handler.postDelayed(drawGIF, frameDuration);
            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            //Called when the visibility of the view or an ancestor of the view has changed.
            this.visible = visible;
            if (visible) {
                handler.post(drawGIF);
            } else {
                handler.removeCallbacks(drawGIF);
            }
        }


        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(drawGIF);
        }
    }
}

