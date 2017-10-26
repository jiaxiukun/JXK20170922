package baway.com.fuzhiyan20170922;

import android.app.Application;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;

public class MyApp extends Application {
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(new Builder(getApplicationContext()).build());
    }
}
