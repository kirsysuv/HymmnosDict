package com.binasphere.hymmnosdict.frag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.binasphere.hymmnosdict.App;
import com.binasphere.hymmnosdict.R;
import com.binasphere.hymmnosdict.act.WordActivity;
import com.binasphere.hymmnosdict.adapter.MyReAdapter;
import com.binasphere.hymmnosdict.bean.HymmnosWord;
import com.binasphere.hymmnosdict.common.LogUtil;
import com.binasphere.hymmnosdict.common.UiUtils;
import com.binasphere.hymmnosdict.db.DBInfo;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRv;
    private MyReAdapter adapter;

    public BaseFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaseFragment newInstance(String param1, String param2) {
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        LogUtil.d("Hymmnos", mParam2 + " fragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mRv = (RecyclerView) view.findViewById(R.id.rv_search);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();

//        words = DAO.findByAlphabet(mParam2);
//        adapter = new MyReAdapter(words);

//        mRv.setAdapter(adapter);
        mRv.addItemDecoration(new MyItemDecoration());
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setHasFixedSize(true);
        return view;
    }

    Observable<List<HymmnosWord>> getWordList(String key) {
        switch (Integer.parseInt(mParam1)) {
            case DBInfo.ORDER_ALPHA:
                return App.DAO.findByAlphabet(key);
            case DBInfo.ORDER_DIALECT:
            default:
                return App.DAO.findByDialect(key);
        }
    }

    void initData() {
        getWordList(mParam2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<HymmnosWord>>() {
                    @Override
                    public void call(List<HymmnosWord> words) {
                        initAdapter(words);
                    }
                });
    }

    private void initAdapter(List<HymmnosWord> words) {
        if (adapter == null) {
            adapter = new MyReAdapter(words);
            adapter.setOnItemClickListener(new MyReAdapter.MyItemClickListener() {
                @Override
                public void onClick(View v, int position) {
                    HymmnosWord word = adapter.getItem(position);
                    Intent intent = new Intent(getActivity(), WordActivity.class);
                    intent.putExtra("Hymmnos", word);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
                    LogUtil.d("Hymmnos", mParam2 + " fragment click");
                }
            });
            mRv.setAdapter(adapter);
        }
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) != 0) {
                outRect.top = UiUtils.dp2px(10);
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d("Hymmnos", mParam2 + " fragment onDetach");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
        LogUtil.d("Hymmnos", mParam2 + " fragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("Hymmnos", mParam2 + " fragment onDestroy");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.d("Hymmnos", mParam2 + " fragment onAttach");

    }
}
