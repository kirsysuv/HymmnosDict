package com.binasphere.hymmnosdict.frag;

import android.support.v4.app.Fragment;

public class FragmentFactory {

    public static Fragment newInstance(int order,String string) {
        return SearchFragment.newInstance(order+"",string);
//        switch (order) {
//            case DBInfo.ORDER_ALPHA:
//                return AlphabetFrag.newInstance("",string);
//            case DBInfo.ORDER_DIALECT:
//            default:
//                return DialectFrag.newInstance("",string);
//        }
    }
}
