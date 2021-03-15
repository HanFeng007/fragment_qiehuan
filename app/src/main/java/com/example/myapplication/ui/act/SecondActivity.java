package com.example.myapplication.ui.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.ui.frag.ContactFragment;
import com.example.myapplication.ui.frag.HomeFragment;
import com.example.myapplication.ui.frag.MyFragment;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * tablayout切换fragment
 */
public class SecondActivity extends AppCompatActivity {

    private TabLayout tl;
    private ViewPager vp;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        tl = findViewById(R.id.sec_tl);
        vp = findViewById(R.id.sec_vp);
        //设置list装载fragment
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ContactFragment());
        fragments.add(new MyFragment());
//        vp设置适配器 和fragments关联
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tl.setupWithViewPager(vp);

//        tablayout的数据源
        stringList = new ArrayList<>();
        stringList.add("第一个");
        stringList.add("第二个");
        stringList.add("第三个");
        for (int i = 0; i < stringList.size(); i++) {
            tl.getTabAt(i).setText(stringList.get(i));
        }
    }
}
