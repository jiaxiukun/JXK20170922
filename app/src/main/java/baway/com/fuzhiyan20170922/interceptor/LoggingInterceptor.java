package baway.com.fuzhiyan20170922.interceptor;

import android.util.Log;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import java.io.IOException;
import java.util.Locale;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoggingInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Log.v(GifHeaderParser.TAG, "request:" + chain.request().toString());
        long t1 = System.nanoTime();
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        Log.v(GifHeaderParser.TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s", new Object[]{response.request().url(), Double.valueOf(((double) (t2 - t1)) / 1000000.0d), response.headers()}));
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.i(GifHeaderParser.TAG, "response body:" + content);
        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
    }
}
