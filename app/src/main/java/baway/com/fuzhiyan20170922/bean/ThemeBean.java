package baway.com.fuzhiyan20170922.bean;

import com.google.gson.Gson;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ThemeBean {
    public int limit;
    public List<OthersBean> others;
    public List<?> subscribed;

    public static class OthersBean {
        public int color;
        public String description;
        public int id;
        public String name;
        public String thumbnail;

        public static OthersBean objectFromData(String str) {
            return (OthersBean) new Gson().fromJson(str, OthersBean.class);
        }

        public static OthersBean objectFromData(String str, String key) {
            try {
                return (OthersBean) new Gson().fromJson(new JSONObject(str).getString(str), OthersBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static ThemeBean objectFromData(String str) {
        return (ThemeBean) new Gson().fromJson(str, ThemeBean.class);
    }

    public static ThemeBean objectFromData(String str, String key) {
        try {
            return (ThemeBean) new Gson().fromJson(new JSONObject(str).getString(str), ThemeBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
