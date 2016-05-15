package com.binasphere.hymmnosdict.act;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.binasphere.hymmnosdict.App;
import com.binasphere.hymmnosdict.R;
import com.binasphere.hymmnosdict.bean.HymmnosWord;
import com.binasphere.hymmnosdict.common.RxProvider;
import com.binasphere.hymmnosdict.common.ShareUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @Bind(R.id.toolbar)Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton fab;
    private HymmnosWord word;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        word = getIntent().getParcelableExtra("Hymmnos");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(word.getHymmnos());
        }
        initTextView();

    }
    @OnClick(R.id.fab)
    void onFabClick(View v){
        ShareUtils.share(WordActivity.this, word.toString());

    }
    @OnLongClick(R.id.tv_hym_word)
    boolean onHymLongClick(View v){


        RxProvider.saveImageAndGetPathObservable(WordActivity.this, v, WordActivity.this.getString(R.string.action_share))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>() {
                    @Override
                    public void call(Uri uri) {
                        Toast.makeText(WordActivity.this, "picture saved", Toast.LENGTH_SHORT).show();
                        ShareUtils.shareImage(WordActivity.this, uri, mTvHym.getText().toString());
                    }
                });
        return true;

    }
    private void initTextView() {
        App.TVU.setHymmnos(mTvHym);
        mTvHym.setText(" " + word.getHymmnos() + " ");

        mTvEng.setText(word.getHymmnos());
        mTvDialect.setText(word.getDialect());
        mTvExp.setText(word.getExp_jp());
        mTvKata.setText(word.getKatakana());
    }

}
