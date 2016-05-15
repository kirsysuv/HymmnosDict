package com.binasphere.hymmnosdict.common;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class TextViewUtils {

    private static final String HYMMNOS_TTF = "hymmnos.ttf";
    private Typeface mTf;
    public TextViewUtils(Context context) {
        mTf = Typeface.createFromAsset(context.getAssets(), HYMMNOS_TTF);
    }

    public void setHymmnos(TextView view) {
        view.setTypeface(mTf);
    }
}
