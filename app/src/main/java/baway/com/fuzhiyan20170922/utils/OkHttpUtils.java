package baway.com.fuzhiyan20170922.utils;

import android.app.Activity;
import com.google.gson.Gson;

import java.io.IOException;

import baway.com.fuzhiyan20170922.interceptor.LoggingInterceptor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    private static OkHttpUtils utils;
    private OkHttpClient client = new Builder().addInterceptor(new LoggingInterceptor()).build();

    public interface CallBack<T> {
        void getData(T t);
    }

    private OkHttpUtils() {
    }

    public static OkHttpUtils getInstance() {
        if (utils == null) {
            synchronized (OkHttpUtils.class) {
                if (utils == null) {
                    utils = new OkHttpUtils();
                    OkHttpUtils okHttpUtils = utils;
                    return okHttpUtils;
                }
            }
        }
        return utils;
    }

    public <T> void getBeanOfOk(final Activity activity, String url, final Class<T> clazz, final CallBack<T> callback) {
        this.client.newCall(new Request.Builder().get().url(url).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
            }

            public void onResponse(Call call, Response response) throws IOException {
                final T t = new Gson().fromJson(response.body().string(), clazz);
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        if (t != null) {
                            callback.getData(t);
                        }
                    }
                });
            }
        });
    }
}
