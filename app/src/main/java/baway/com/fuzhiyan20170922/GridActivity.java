package baway.com.fuzhiyan20170922;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import baway.com.fuzhiyan20170922.bean.ThemeBean;
import baway.com.fuzhiyan20170922.constant.MyApi;
import baway.com.fuzhiyan20170922.utils.OkHttpUtils;

public class GridActivity extends AppCompatActivity {
    private List<ThemeBean.OthersBean> list = new ArrayList();
    private ListView listview;

    class MyBaseListAdapter extends BaseAdapter {
        MyBaseListAdapter() {
        }

        public int getCount() {
            return GridActivity.this.list.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public int getItemViewType(int position) {
            if (position % 2 == 0) {
                return 1;
            }
            return 2;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            switch (getItemViewType(i)) {
                case 1:
                    inflate = View.inflate(GridActivity.this, R.layout.item_list, null);
                    TextView text = (TextView) inflate.findViewById(R.id.tv_list);
                    Glide.with(GridActivity.this).load(((ThemeBean.OthersBean) GridActivity.this.list.get(i)).thumbnail).into((ImageView) inflate.findViewById(R.id.list_image));
                    text.setText(((ThemeBean.OthersBean) GridActivity.this.list.get(i)).name);
                    return inflate;
                case 2:
                    inflate = View.inflate(GridActivity.this, R.layout.item_list2, null);
                    TextView texta = (TextView) inflate.findViewById(R.id.tv_list);
                    Glide.with(GridActivity.this).load(((ThemeBean.OthersBean) GridActivity.this.list.get(i)).thumbnail).into((ImageView) inflate.findViewById(R.id.list_image));
                    texta.setText(((ThemeBean.OthersBean) GridActivity.this.list.get(i)).name);
                    return inflate;
                default:
                    return null;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_second);
        this.listview = (ListView) findViewById(R.id.listview);
        OkHttpUtils.getInstance().getBeanOfOk(this, MyApi.THEME_DAILY, ThemeBean.class, new OkHttpUtils.CallBack<ThemeBean>() {
            public void getData(ThemeBean themeBean) {
                GridActivity.this.list = themeBean.others;
                GridActivity.this.listview.setAdapter(new MyBaseListAdapter());
            }
        });
    }
}
