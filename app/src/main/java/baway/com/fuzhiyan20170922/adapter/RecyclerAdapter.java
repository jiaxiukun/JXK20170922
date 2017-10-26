package baway.com.fuzhiyan20170922.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import baway.com.fuzhiyan20170922.R;
import baway.com.fuzhiyan20170922.bean.DailyBean;
import baway.com.fuzhiyan20170922.view.MyViewPager;

public class RecyclerAdapter extends Adapter {
    private Context context;
    private int currentItem = 0;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            RecyclerAdapter.this.viewHolderOne.myViewPager.setCurrentItem(RecyclerAdapter.this.currentItem);
        }
    };
    private List<DailyBean.StoriesBean> list = new ArrayList();
    private ViewHolderOne viewHolderOne;
    private List<View> views;

    private class YuanDianRun implements Runnable {
        private YuanDianRun() {
        }

        public void run() {
            synchronized (RecyclerAdapter.this.viewHolderOne.myViewPager) {
                RecyclerAdapter.this.currentItem = (RecyclerAdapter.this.currentItem + 1) % 5;
                RecyclerAdapter.this.handler.obtainMessage().sendToTarget();
            }
        }
    }

    class ViewHolderMy extends ViewHolder {
        private ImageView imageView_my;
        private TextView textView_my;

        public ViewHolderMy(View itemView) {
            super(itemView);
            this.imageView_my = (ImageView) itemView.findViewById(R.id.daily_recycler_image);
            this.textView_my = (TextView) itemView.findViewById(R.id.daily_recycler_text);
        }
    }

    class ViewHolderOne extends ViewHolder {
        private MyViewPager myViewPager;
        private TextView textview_Viewpager;
        private View textview_tuijian_tou1;
        private View textview_tuijian_tou2;
        private View textview_tuijian_tou3;
        private View textview_tuijian_tou4;
        private View textview_tuijian_tou5;

        public ViewHolderOne(View itemView) {
            super(itemView);
            this.myViewPager = (MyViewPager) itemView.findViewById(R.id.myViewPage_tuijian_tou);
            this.textview_Viewpager = (TextView) itemView.findViewById(R.id.textview_Viewpager);
            this.textview_tuijian_tou1 = itemView.findViewById(R.id.textview_tuijian_tou1);
            this.textview_tuijian_tou2 = itemView.findViewById(R.id.textview_tuijian_tou2);
            this.textview_tuijian_tou3 = itemView.findViewById(R.id.textview_tuijian_tou3);
            this.textview_tuijian_tou4 = itemView.findViewById(R.id.textview_tuijian_tou4);
            this.textview_tuijian_tou5 = itemView.findViewById(R.id.textview_tuijian_tou5);
            RecyclerAdapter.this.views = new ArrayList();
            RecyclerAdapter.this.views.add(this.textview_tuijian_tou1);
            RecyclerAdapter.this.views.add(this.textview_tuijian_tou2);
            RecyclerAdapter.this.views.add(this.textview_tuijian_tou3);
            RecyclerAdapter.this.views.add(this.textview_tuijian_tou4);
            RecyclerAdapter.this.views.add(this.textview_tuijian_tou5);
        }
    }

    private class ViewpagerRecommendPageChangeListener implements OnPageChangeListener {
        private int oldposition;

        private ViewpagerRecommendPageChangeListener() {
            this.oldposition = 0;
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            RecyclerAdapter.this.currentItem = position;
            RecyclerAdapter.this.viewHolderOne.textview_Viewpager.setText(((DailyBean.StoriesBean) RecyclerAdapter.this.list.get(position)).title);
            ((View) RecyclerAdapter.this.views.get(this.oldposition)).setBackgroundResource(R.drawable.dot_normal);
            ((View) RecyclerAdapter.this.views.get(position)).setBackgroundResource(R.drawable.dot_focused);
            this.oldposition = position;
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    private class ViewpagerecommendAdapter extends PagerAdapter {
        private Context mContext;

        public ViewpagerecommendAdapter(Context mContext) {
            this.mContext = mContext;
        }

        public int getCount() {
            return 5;
        }

        public Object instantiateItem(View container, int position) {
            ImageView imageview = new ImageView(RecyclerAdapter.this.context);
            ImageLoader.getInstance().displayImage((String) ((DailyBean.StoriesBean) RecyclerAdapter.this.list.get(position)).images.get(0), imageview);
            ((ViewPager) container).addView(imageview);
            return imageview;
        }

        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        public Parcelable saveState() {
            return null;
        }

        public void startUpdate(View arg0) {
        }

        public void finishUpdate(View arg0) {
        }
    }

    public RecyclerAdapter(List<DailyBean.StoriesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ViewHolderOne(LayoutInflater.from(this.context).inflate(R.layout.list_item_tou, parent, false));
            case 1:
                return new ViewHolderMy(LayoutInflater.from(this.context).inflate(R.layout.list_item, parent, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                this.viewHolderOne = (ViewHolderOne) holder;
                this.viewHolderOne.textview_Viewpager.setText(((DailyBean.StoriesBean) this.list.get(position)).title);
                this.viewHolderOne.myViewPager.setAdapter(new ViewpagerecommendAdapter(this.context));
                this.viewHolderOne.myViewPager.setOnPageChangeListener(new ViewpagerRecommendPageChangeListener());
                Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new YuanDianRun(), 3, 6, TimeUnit.SECONDS);
                return;
            case 1:
                ViewHolderMy viewHolderMy = (ViewHolderMy) holder;
                ImageLoader.getInstance().displayImage((String) ((DailyBean.StoriesBean) this.list.get(position)).images.get(0), viewHolderMy.imageView_my);
                viewHolderMy.textView_my.setText(((DailyBean.StoriesBean) this.list.get(position)).title);
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        return this.list == null ? 0 : this.list.size();
    }

    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    public int getWidth() {
        WindowManager wm = (WindowManager) this.context.getSystemService("window");
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return (int) (((float) dm.widthPixels) / dm.density);
    }
}
