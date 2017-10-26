package baway.com.fuzhiyan20170922.bean;

import com.google.gson.Gson;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DailyMoreBean {
    public String date;
    public List<StoriesBean> stories;

    public static class StoriesBean {
        public String ga_prefix;
        public int id;
        public List<String> images;
        public boolean multipic;
        public String title;
        public int type;

        public static StoriesBean objectFromData(String str) {
            return (StoriesBean) new Gson().fromJson(str, StoriesBean.class);
        }

        public static StoriesBean objectFromData(String str, String key) {
            try {
                return (StoriesBean) new Gson().fromJson(new JSONObject(str).getString(str), StoriesBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static DailyMoreBean objectFromData(String str) {
        return (DailyMoreBean) new Gson().fromJson(str, DailyMoreBean.class);
    }

    public static DailyMoreBean objectFromData(String str, String key) {
        try {
            return (DailyMoreBean) new Gson().fromJson(new JSONObject(str).getString(str), DailyMoreBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
