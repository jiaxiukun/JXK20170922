package baway.com.fuzhiyan20170922.frag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import baway.com.fuzhiyan20170922.GridActivity;
import baway.com.fuzhiyan20170922.R;
import baway.com.fuzhiyan20170922.adapter.ThemeGridAdapter;
import baway.com.fuzhiyan20170922.bean.ThemeBean;
import baway.com.fuzhiyan20170922.constant.MyApi;
import baway.com.fuzhiyan20170922.interceptor.LoggingInterceptor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

public class FragTheme extends Fragment {
    private GridView gridView;
    private Handler handler = new Handler();
    private List<ThemeBean.OthersBean> list = new ArrayList();

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_theme, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        new Builder().addInterceptor(new LoggingInterceptor()).build().newCall(new Request.Builder().url(MyApi.THEME_DAILY).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null && response.isSuccessful()) {
                    FragTheme.this.list = ThemeBean.objectFromData(response.body().string()).others;
                    FragTheme.this.handler.post(new Runnable() {
                        public void run() {
                            FragTheme.this.gridView.setAdapter(new ThemeGridAdapter(FragTheme.this.list, FragTheme.this.getActivity()));
                        }
                    });
                }
            }
        });
    }

    private void initView(View view) {
        this.gridView = (GridView) view.findViewById(R.id.frag_theme_grid);
        this.gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragTheme.this.startActivity(new Intent(FragTheme.this.getActivity(), GridActivity.class));
            }
        });
    }
}
