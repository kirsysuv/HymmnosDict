package com.binasphere.hymmnosdict.frag;

import com.binasphere.hymmnosdict.App;
import com.binasphere.hymmnosdict.bean.HymmnosWord;

import java.util.List;

import rx.Observable;

/**
 * Created by Kerstin on 2015/12/6.
 */
public class AlphabetFrag extends BaseFragment {
    @Override
    Observable<List<HymmnosWord>> getWordList(String key) {
        return App.DAO.findByAlphabet(key);
    }
}
