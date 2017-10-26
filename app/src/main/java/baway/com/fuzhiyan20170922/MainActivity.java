package baway.com.fuzhiyan20170922;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import baway.com.fuzhiyan20170922.adapter.VpFragAdapter;
import baway.com.fuzhiyan20170922.frag.FragDaily;
import baway.com.fuzhiyan20170922.frag.FragTheme;
import baway.com.fuzhiyan20170922.frag.FragThree;
import baway.com.fuzhiyan20170922.frag.FragTwo;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragments = new ArrayList();
    private List<String> strings = new ArrayList();
    private TabLayout tabLayout_shouye;
    private ViewPager viewPager_shouye;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdate();
        initView();
    }
    private void initdate() {
        this.fragments.add(new FragDaily());
        this.strings.add("最新日报");
        this.fragments.add(new FragTwo());
        this.strings.add("专栏");
        this.fragments.add(new FragThree());
        this.strings.add("热门");
        this.fragments.add(new FragTheme());
        this.strings.add("日报主题");
    }

    private void initView() {
        this.tabLayout_shouye = (TabLayout) findViewById(R.id.tabLayout);
        this.viewPager_shouye = (ViewPager) findViewById(R.id.activity_vp);
        this.viewPager_shouye.setAdapter(new VpFragAdapter(this.fragments, this.strings, getSupportFragmentManager(), this));
        this.tabLayout_shouye.setupWithViewPager(this.viewPager_shouye);
        this.tabLayout_shouye.setTabTextColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimaryDark));
    }
}
