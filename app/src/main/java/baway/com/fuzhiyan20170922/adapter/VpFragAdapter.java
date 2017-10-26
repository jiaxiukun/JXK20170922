package baway.com.fuzhiyan20170922.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class VpFragAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragments;
    private List<String> strings;

    public VpFragAdapter(List<Fragment> fragments, List<String> strings, FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.strings = strings;
        this.context = context;
        this.fragments = fragments;
    }

    public Fragment getItem(int position) {
        return (Fragment) this.fragments.get(position);
    }

    public int getCount() {
        return this.strings.size();
    }

    public CharSequence getPageTitle(int position) {
        return (CharSequence) this.strings.get(position);
    }
}
