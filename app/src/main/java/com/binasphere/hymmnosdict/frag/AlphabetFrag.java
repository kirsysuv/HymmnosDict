package com.binasphere.hymmnosdict.frag;

import android.os.Bundle;

import com.binasphere.hymmnosdict.bean.HymmnosWord;
import com.binasphere.hymmnosdict.dao.HymmnosDao;

import java.util.List;

/**
 * Created by Kerstin on 2015/12/6.
 */
public class AlphabetFrag extends SearchFragment {
    @Override
    List<HymmnosWord> getWordList(HymmnosDao dao, String key) {
        return dao.findByAlphabet(key);
    }
}
