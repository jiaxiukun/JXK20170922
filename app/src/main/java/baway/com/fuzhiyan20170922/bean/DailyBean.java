package baway.com.fuzhiyan20170922.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DailyBean {
    public String date;
    public List<StoriesBean> stories;
    public List<TopStoriesBean> top_stories;

    public static class StoriesBean {
        public String ga_prefix;
        public int id;
        public List<String> images;
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

    public static class TopStoriesBean {
        public String ga_prefix;
        public int id;
        public String image;
        public String title;
        public int type;

        public static TopStoriesBean objectFromData(String str) {
            return (TopStoriesBean) new Gson().fromJson(str, TopStoriesBean.class);
        }

        public static TopStoriesBean objectFromData(String str, String key) {
            try {
                return (TopStoriesBean) new Gson().fromJson(new JSONObject(str).getString(str), TopStoriesBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static DailyBean objectFromData(String str) {
        return (DailyBean) new Gson().fromJson(str, DailyBean.class);
    }

    public static DailyBean objectFromData(String str, String key) {
        try {
            return (DailyBean) new Gson().fromJson(new JSONObject(str).getString(str), DailyBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
