package com.example.msdhainizam.loginschoolapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * Created by IGWMobileTeam on 13/01/2016.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AnnounceDataLoadedListener {

    private static final String PARCEL_DATA = "announcement";
    private static String LOG_TAG = "RecyclerViewActivity";

    private TextView mTextVolleyError;
    private RecyclerView mRecyclerView;
    private RecyclerView.ItemDecoration itemDecoration;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<Data> mDataList = new ArrayList<>();
    private HomeAdapter mAdapter;


    public HomeFragment() {
        setRetainInstance(true);
    }

    public static HomeFragment newInstance(String params1, String params2) {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        mTextVolleyError = (TextView) view.findViewById(R.id.textVolleyError);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeData);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        itemDecoration = new DividerItemDecoration(MyApplication.getAppContext(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new HomeAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setFirstOnly(false);
        scaleAdapter.setInterpolator(new OvershootInterpolator());
        mRecyclerView.setAdapter(scaleAdapter);

        if(savedInstanceState != null) {
            mDataList = savedInstanceState.getParcelableArrayList(PARCEL_DATA);
        }else {
            new TaskLoadAnnouncement(this).execute();
        }

        mAdapter.setData(mDataList);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(PARCEL_DATA, mDataList);
    }

    @Override
    public void onAnnounceDataLoaded(ArrayList<Data> listAnnounceData) {

        if(mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        mAdapter.setData(listAnnounceData);
    }

    @Override
    public void onRefresh() {
        new TaskLoadAnnouncement(this).execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeAdapter) mAdapter).setOnItemClickListener(new HomeAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, "Clicked on Item" + position);
            }
        });
    }
}
