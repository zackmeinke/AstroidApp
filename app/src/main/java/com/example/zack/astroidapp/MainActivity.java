package com.example.zack.astroidapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    //Draw onto the Canvas...
    GameActivityLayout canvasLayoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvasLayoutView = new GameActivityLayout(this);
        setContentView(canvasLayoutView);
        onFocusChanged(true);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //calling pause
        canvasLayoutView.pause();
        onFocusChanged(true);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //calling onResume
        canvasLayoutView.resume();
        onFocusChanged(true);
    }
    //Remove extra UI elements
    public void onFocusChanged(boolean hasChanged){
        super.onWindowFocusChanged(hasChanged);
        View decorView = getWindow().getDecorView();
        if(hasChanged){
            decorView.setSystemUiVisibility((View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    |View.SYSTEM_UI_FLAG_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION));
        }
    }
}
