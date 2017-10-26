package baway.com.fuzhiyan20170922.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class MyViewPager extends ViewPager {
    private ViewGroup parent;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setNestedpParent(ViewGroup parent) {
        this.parent = parent;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.parent != null) {
            this.parent.requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (this.parent != null) {
            this.parent.requestDisallowInterceptTouchEvent(true);
        }
        return super.onInterceptTouchEvent(arg0);
    }

    public boolean onTouchEvent(MotionEvent arg0) {
        if (this.parent != null) {
            this.parent.requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(arg0);
    }
}
