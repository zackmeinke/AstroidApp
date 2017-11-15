package com.example.zack.astroidapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Zack on 11/15/2017.
 */

public class GameActivityLayout extends SurfaceView implements Runnable {

    Thread thread = null;
    boolean canDraw = false;
    Bitmap backGroundPic;
    Bitmap plane3;
    Canvas mCanvas;
    SurfaceHolder mSurfaceHolder;

    public GameActivityLayout(Context context) {
        super(context);
        mSurfaceHolder = getHolder();

        backGroundPic = BitmapFactory.decodeResource(getResources(),R.drawable.backgroundpic);
        backGroundPic = Bitmap.createScaledBitmap(backGroundPic, 1440, 2960, false);
        plane3 = BitmapFactory.decodeResource(getResources(),R.drawable.plane3);
    }

    public int getSurfaceFrame(){
        return 0;///.getSurfaceFrame();

    }

    @Override
    public void run(){

        while(canDraw){
            //Draw
            if(!mSurfaceHolder.getSurface().isValid()){
                continue;
            }
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawBitmap(backGroundPic,0,0,null);
            mCanvas.drawBitmap(plane3,((getWidth()/2)-plane3.getWidth()/2), getHeight()-300,null);
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    public void pause(){
        canDraw = false;

        while(true){
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }

    public void resume(){
        canDraw = true;
        thread = new Thread(this);
        thread.start();
    }

}
