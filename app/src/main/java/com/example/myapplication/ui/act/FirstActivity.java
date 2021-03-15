package com.example.myapplication.ui.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.frag.ContactFragment;
import com.example.myapplication.ui.frag.HomeFragment;
import com.example.myapplication.ui.frag.MyFragment;

import java.util.ArrayList;

/**
 * vp切换fragment
 */
public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager main_vp;
    private TextView t1;
    private TextView t3;
    private TextView t2;
    private ArrayList<Fragment> fragments;
    private ArrayList<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
//        获取资源ID
        main_vp = findViewById(R.id.main_vp);
        t1 = findViewById(R.id.main_ll_t1);
        t3 = findViewById(R.id.main_ll_t3);
        t2 = findViewById(R.id.main_ll_t2);
//        设置list,装载fragment
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ContactFragment());
        fragments.add(new MyFragment());
//        设置list, 装载textview
        textViews = new ArrayList<>();
        textViews.add(t1);
        textViews.add(t2);
        textViews.add(t3);
//        TextView设置点击事件
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
//        默认展示的第一个页面, 对应的颜色样式要设置
        t1.setBackgroundColor(R.color.colorAccent);
        t2.setBackgroundColor(R.color.textColor);
        t3.setBackgroundColor(R.color.textColor);

//        vp设置适配器 - 和fragment关联起来
        main_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

           @Override
            public int getCount() {
                return fragments.size();
            }
        });
//        vp设置滑动事件 - 给逻辑控制
        main_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (TextView textView :
                        textViews) {
                    textView.setBackgroundColor(R.color.textColor);

                }
                textViews.get(position).setBackgroundColor(R.color.colorAccent);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * TextView的点击事件对应的变化
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_ll_t1:
                main_vp.setCurrentItem(0);
                break;
            case R.id.main_ll_t2:
                main_vp.setCurrentItem(1);
                break;
            case R.id.main_ll_t3:
                main_vp.setCurrentItem(2);
                break;
            default:
                break;
        }
    }
}
