package com.binasphere.hymmnosdict.dao;

import android.content.Context;

import com.binasphere.hymmnosdict.bean.HymmnosWord;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Kerstin on 2016/5/15.
 */
public class HymmnosDaoRx implements HymmnosLogicRx {

    private HymmnosLogic dao;

    public HymmnosDaoRx(Context context) {
        dao = new HymmnosDao(context);
    }

    @Override
    public Observable<List<String>> getAlphabets() {
        return Observable.just(dao.getAlphabets())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<String>> getDialects() {
        return Observable.just(dao.getDialects())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<HymmnosWord>> findAllWords() {
        return Observable.just(dao.findAllWords())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<HymmnosWord>> findByWords(String words) {
        return Observable.just(dao.findByWords(words))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<HymmnosWord> findById(String id) {
        return Observable.just(dao.findById(id))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<HymmnosWord> findByHymmnos(String hymmnos) {
        return Observable.just(dao.findByHymmnos(hymmnos))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<HymmnosWord>> findByDialect(String dialect) {
        return Observable.just(dao.findByDialect(dialect))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<HymmnosWord>> findByAlphabet(String alpha) {
        return Observable.just(dao.findByAlphabet(alpha))
                .subscribeOn(Schedulers.io());
    }
}
