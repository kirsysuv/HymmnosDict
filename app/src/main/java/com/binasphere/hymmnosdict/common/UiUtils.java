package com.binasphere.hymmnosdict.common;

import android.content.Context;
import android.support.v7.view.StandaloneActionMode;

import com.binasphere.hymmnosdict.App;

/**
 * Created by Kerstin on 2015/12/12.
 */
public class UiUtils {
    public static Context getApplication(){
        return App.mContext;
    }
    public static float getDensity(){
        return  (getApplication().getResources().getDisplayMetrics().density+0.5f);
    }
    public static int dp2px( int dp) {
        float density =getDensity();
        return (int) (dp * density+0.5f);
    }

    public static int px2dp (int px) {
        float density = getDensity();
        return (int) (px / density+0.5f);
    }
}
