package com.binasphere.hymmnosdict;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.binasphere.hymmnosdict.bean.HymmnosWord;
import com.binasphere.hymmnosdict.dao.HymmnosDao;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test() {
        HymmnosDao dao = new HymmnosDao(mContext);
        List<HymmnosWord> words = dao.findAllWords();
        for (HymmnosWord word:
             words
                ) {
            Log.d("ceshi",word.getHymmnos());
        }
    }
}