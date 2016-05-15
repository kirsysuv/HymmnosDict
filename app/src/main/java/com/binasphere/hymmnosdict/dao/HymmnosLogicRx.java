package com.binasphere.hymmnosdict.dao;

import com.binasphere.hymmnosdict.bean.HymmnosWord;

import java.util.List;

import rx.Observable;

/**
 * Created by Kerstin on 2016/5/15.
 */
public interface HymmnosLogicRx {
    Observable<List<String>> getAlphabets();

    Observable<List<String>> getDialects();

    Observable<List<HymmnosWord>> findAllWords();

    Observable<List<HymmnosWord>> findByWords(String words);

    Observable<HymmnosWord> findById(String id);

    Observable<HymmnosWord> findByHymmnos(String hymmnos);

    Observable<List<HymmnosWord>> findByDialect(String dialect);

    Observable<List<HymmnosWord>> findByAlphabet(String alpha);
}
