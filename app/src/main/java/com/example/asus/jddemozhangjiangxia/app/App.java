package com.example.asus.jddemozhangjiangxia.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        ZXingLibrary.initDisplayOpinion(this);
    }
}
