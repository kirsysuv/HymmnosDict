package com.binasphere.hymmnosdict.widget;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Kerstin on 2015/12/10.
 */
public class AlertImDialog {
    AlertImDialog() {

    }

    public static AlertDialog getImDialog(AlertDialog dialog) {
        final Window window = dialog.getWindow();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });
        return dialog;
    }

    public static AlertDialog getImDialog(AlertDialog.Builder builder) {
        return getImDialog(builder.create());
    }
}
