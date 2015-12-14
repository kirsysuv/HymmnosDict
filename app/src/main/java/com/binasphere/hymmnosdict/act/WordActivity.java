package com.binasphere.hymmnosdict.act;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binasphere.hymmnosdict.R;
import com.binasphere.hymmnosdict.bean.HymmnosWord;
import com.binasphere.hymmnosdict.common.RxProvider;
import com.binasphere.hymmnosdict.common.ShareUtils;
import com.binasphere.hymmnosdict.common.TextViewUtils;
import com.binasphere.hymmnosdict.common.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnLongClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class WordActivity extends AppCompatActivity {
    @Bind(R.id.tv_hym_word)
    TextView mTvHym;
    @Bind(R.id.tv_eng_word)
    TextView mTvEng;
    @Bind(R.id.tv_kata_word)
    TextView mTvKata;
    @Bind(R.id.tv_exp_word)
    TextView mTvExp;
    @Bind(R.id.tv_dialect_word)
    TextView mTvDialect;
    private HymmnosWord word;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtils.share(WordActivity.this, word.toString());
            }
        });
        word = getIntent().getParcelableExtra("Hymmnos");
        initTextView();

    }
    @OnLongClick(R.id.tv_hym_word)
    boolean onHymLongClick(View v){
        v.setDrawingCacheBackgroundColor(Color.WHITE);
        v.buildDrawingCache();
        Bitmap drawingCache = v.getDrawingCache();
        Bitmap bitmap=drawingCache.copy(Bitmap.Config.ARGB_8888,false);
        v.destroyDrawingCache();

        RxProvider.saveImageAndGetPathObservable(WordActivity.this, bitmap, mTvHym.getText().toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>() {
                    @Override
                    public void call(Uri uri) {
                        Toast.makeText(WordActivity.this, "saved picture", Toast.LENGTH_SHORT).show();
                        ShareUtils.shareImage(WordActivity.this, uri, mTvHym.getText().toString());
                    }
                });
        return true;

    }
    private void initTextView() {
        TextViewUtils.getInstance().setHymmnos(mTvHym);
        mTvHym.setText(" " + word.getHymmnos() + " ");

        mTvEng.setText(word.getHymmnos());
        mTvDialect.setText(word.getDialect());
        mTvExp.setText(word.getExp_jp());
        mTvKata.setText(word.getKatakana());
    }

}
