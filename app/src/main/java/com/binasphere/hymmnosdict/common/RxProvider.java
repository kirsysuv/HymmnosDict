package com.binasphere.hymmnosdict.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.binasphere.hymmnosdict.App;
import com.binasphere.hymmnosdict.db.DBInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Kerstin on 2015/12/7.
 */
public class RxProvider {
    public static Observable<List<String>> getTabListObservable(int order) {
        switch (order) {
            case DBInfo.ORDER_ALPHA:
                return App.DAO.getAlphabets();
            case DBInfo.ORDER_DIALECT:
            default:
                return App.DAO.getDialects();
        }

    }

    public static Observable<Uri> saveImageAndGetPathObservable(final Context context, final View v, final String title) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                v.setDrawingCacheBackgroundColor(Color.WHITE);
                v.buildDrawingCache();
                Bitmap drawingCache = v.getDrawingCache();
                Bitmap bitmap = drawingCache.copy(Bitmap.Config.ARGB_8888, false);
                v.destroyDrawingCache();
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        }).flatMap(new Func1<Bitmap, Observable<Uri>>() {
            @Override
            public Observable<Uri> call(Bitmap bitmap) {
                File appDir = new File(Environment.getExternalStorageDirectory(), "Hymmnos_Pic");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = title.replace('/', '-') + ".jpg";
                File file = new File(appDir, fileName);
                if (!file.exists()) {
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
//                        assert bitmap != null;
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Uri uri = Uri.fromFile(file);
                Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                context.sendBroadcast(scannerIntent);
                return Observable.just(uri);
            }
        }).subscribeOn(Schedulers.io());
    }

}
