package com.jw.mymvp.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.RadioGroup;

import com.jw.mymvp.R;
import com.jw.mymvp.adapter.MyFragmentPagerAdapter;
import com.jw.mymvp.base.BaseActivity;
import com.jw.mymvp.fragment.MainFragment1;
import com.jw.mymvp.fragment.MainFragment2;
import com.jw.mymvp.fragment.MainFragment3;
import com.jw.mymvp.fragment.MainFragment4;
import com.jw.mymvp.mvp.presenter.MainPresenter;
import com.jw.mymvp.mvp.view.IMainView;
import com.jw.mymvp.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity implements IMainView {
    @Bind(R.id.pager)
    NoScrollViewPager pager;
    @Bind(R.id.radioGroupIndicator)
    RadioGroup radioGroupIndicator;

    private MainPresenter mainPresenter;
    private FragmentStatePagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private MainFragment1 f1;
    private MainFragment2 f2;
    private MainFragment3 f3;
    private MainFragment4 f4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);
        mainPresenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dimissLoading() {

    }

    @Override
    public void initData() {
        mFragments = new ArrayList<Fragment>();
        f1 = new MainFragment1();
        f2 = new MainFragment2();
        f3 = new MainFragment3();
        f4 = new MainFragment4();
        mFragments.add(f1);
        mFragments.add(f2);
        mFragments.add(f3);
        mFragments.add(f4);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments, null);
        pager.setAdapter(mAdapter);
        radioGroupIndicator.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.radio0:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.radio1:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.radio2:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.radio3:
                        pager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public FragmentStatePagerAdapter getMainViewPagerAdapter(){
        return mAdapter;
    }
}
