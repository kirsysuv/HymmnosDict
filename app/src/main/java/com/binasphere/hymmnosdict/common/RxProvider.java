package com.binasphere.hymmnosdict.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.binasphere.hymmnosdict.App;
import com.binasphere.hymmnosdict.dao.HymmnosDao;
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
                return Observable.create(new Observable.OnSubscribe<List<String>>() {

                    @Override
                    public void call(Subscriber<? super List<String>> subscriber) {
                        HymmnosDao dao = HymmnosDao.newInstance(App.mContext);
                        subscriber.onNext(dao.getAlphabets());
                    }
                }).subscribeOn(Schedulers.io());
            case DBInfo.ORDER_DIALECT:
            default:
                return Observable.create(new Observable.OnSubscribe<List<String>>() {

                    @Override
                    public void call(Subscriber<? super List<String>> subscriber) {
                        HymmnosDao dao = HymmnosDao.newInstance(App.mContext);
                        subscriber.onNext(dao.getDialects());
                    }
                }).subscribeOn(Schedulers.io());

        }

    }

    public static Observable<Uri> saveImageAndGetPathObservable(final Context context, final Bitmap cache, final String title) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
//                Bitmap bitmap = Bitmap.createBitmap(tv.getWidth(), tv.getHeight(),
//                        Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                tv.draw(canvas);
//                tv.setDrawingCacheEnabled(true);
//                tv.buildDrawingCache();
                subscriber.onNext(cache);
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
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
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
