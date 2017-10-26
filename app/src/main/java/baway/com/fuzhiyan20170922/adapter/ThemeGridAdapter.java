package baway.com.fuzhiyan20170922.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import baway.com.fuzhiyan20170922.R;
import baway.com.fuzhiyan20170922.bean.ThemeBean;

public class ThemeGridAdapter extends BaseAdapter {
    private Context context;
    private List<ThemeBean.OthersBean> list = new ArrayList();

    static class ViewHolder {
        private ImageView imageView;
        private TextView textView;

        ViewHolder() {
        }
    }

    public ThemeGridAdapter(List<ThemeBean.OthersBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public int getCount() {
        return this.list == null ? 0 : this.list.size();
    }

    public Object getItem(int i) {
        return this.list.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = View.inflate(this.context, R.layout.grid_item, null);
            vh = new ViewHolder();
            vh.imageView = (ImageView) view.findViewById(R.id.grid_image);
            vh.textView = (TextView) view.findViewById(R.id.grid_text);
            ImageLoader.getInstance().displayImage(((ThemeBean.OthersBean) this.list.get(i)).thumbnail, vh.imageView);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.textView.setText(((ThemeBean.OthersBean) this.list.get(i)).description);
        return view;
    }
}
