package com.binasphere.hymmnosdict.dao;

import com.binasphere.hymmnosdict.bean.HymmnosWord;

import java.util.List;

/**
 * Created by Kerstin on 2016/5/15.
 */
public interface HymmnosLogic {
    List<String> getAlphabets();

    List<String> getDialects();

    List<HymmnosWord> findAllWords();

    List<HymmnosWord> findByWords(String words);

    HymmnosWord findById(String id);

    HymmnosWord findByHymmnos(String hymmnos);

    List<HymmnosWord> findByDialect(String dialect);

    List<HymmnosWord> findByAlphabet(String alpha);
}
