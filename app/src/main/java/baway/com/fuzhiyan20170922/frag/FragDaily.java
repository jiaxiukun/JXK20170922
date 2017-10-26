package baway.com.fuzhiyan20170922.frag;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import baway.com.fuzhiyan20170922.R;
import baway.com.fuzhiyan20170922.adapter.RecyclerAdapter;
import baway.com.fuzhiyan20170922.bean.DailyBean;
import baway.com.fuzhiyan20170922.constant.MyApi;
import baway.com.fuzhiyan20170922.utils.OkHttpUtils;

public class FragDaily extends Fragment {
    private RecyclerAdapter adapter;
    private int findLastVisibleItemPosition;
    private Handler handler = new Handler();
    private List<DailyBean.StoriesBean> list = new ArrayList();
    private RecyclerView recycler;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fram, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        OkHttpUtils.getInstance().getBeanOfOk(getActivity(), MyApi.DAILY, DailyBean.class, new OkHttpUtils.CallBack<DailyBean>() {
            public void getData(DailyBean dailyBean) {
                FragDaily.this.list = dailyBean.stories;
                FragDaily.this.adapter = new RecyclerAdapter(FragDaily.this.list, FragDaily.this.getContext());
                FragDaily.this.recycler.setAdapter(FragDaily.this.adapter);
            }
        });
    }

    private void initView(View view) {
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        this.swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()));
        this.swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                FragDaily.this.initData();
                FragDaily.this.adapter.notifyDataSetChanged();
                FragDaily.this.swipeRefreshLayout.setRefreshing(false);
            }
        });
        this.recycler = (RecyclerView) view.findViewById(R.id.recycler);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.recycler.setLayoutManager(linearLayoutManager);
        this.recycler.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    int findLastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    int findFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    FragDaily.this.findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    if (FragDaily.this.findLastVisibleItemPosition == FragDaily.this.list.size() - 1) {
                        FragDaily.this.initDataMore();
                        FragDaily.this.adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void initDataMore() {
        OkHttpUtils.getInstance().getBeanOfOk(getActivity(), MyApi.DAILY_MORE, DailyBean.class, new OkHttpUtils.CallBack<DailyBean>() {
            public void getData(DailyBean dailyBean) {
                FragDaily.this.list = dailyBean.stories;
                FragDaily.this.adapter = new RecyclerAdapter(FragDaily.this.list, FragDaily.this.getContext());
                FragDaily.this.recycler.setAdapter(FragDaily.this.adapter);
            }
        });
    }
}
