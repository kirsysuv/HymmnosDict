package com.binasphere.hymmnosdict;

import android.app.Application;
import android.content.Context;

/**
 * Created by Kerstin on 2015/12/6.
 */
public class App extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }

}
