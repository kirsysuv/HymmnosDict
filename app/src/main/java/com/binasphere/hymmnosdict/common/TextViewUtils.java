package com.binasphere.hymmnosdict.common;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.binasphere.hymmnosdict.App;

public class TextViewUtils {

    private static final String HYMMNOS_TTF = "hymmnos.ttf";
    private Typeface mTf;
    private static TextViewUtils mTVU;

    public TextViewUtils(Context context) {
        mTf = Typeface.createFromAsset(context.getAssets(), HYMMNOS_TTF);
    }

    public static TextViewUtils getInstance() {
        if (mTVU == null) {
            synchronized (TextViewUtils.class) {
                if (mTVU == null) {
                    mTVU = new TextViewUtils(App.mContext);
                }
            }
        }
        return mTVU;
    }

    public void setHymmnos(TextView view) {
        view.setTypeface(mTf);
    }
}
