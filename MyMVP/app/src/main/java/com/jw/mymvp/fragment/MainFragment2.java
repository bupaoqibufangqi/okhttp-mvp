package com.jw.mymvp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jw.mymvp.R;
import com.jw.mymvp.activity.MainActivity;
import com.jw.mymvp.adapter.MyFragmentPagerAdapter;
import com.jw.mymvp.fragment.base.BaseFragment;
import com.jw.mymvp.mvp.presenter.MainFragment2Presenter;
import com.jw.mymvp.mvp.view.IMainFragment2;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MainFragment2 extends BaseFragment implements IMainFragment2 {
    @Bind(R.id.tab_indictor)
    TabPageIndicator tabIndictor;
    @Bind(R.id.pager)
    ViewPager pager;

    private MainFragment2Presenter mainFragment2Presenter;
    private FragmentStatePagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private String[] titles = {"1", "2", "3", "4"};
    private MainFragment1 f1, f2, f3, f4;

    @Override
    public void getData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_2;
    }

    @Override
    public void initUi(View view) {
        mainFragment2Presenter = new MainFragment2Presenter(this);
        mainFragment2Presenter.init();
    }

    @Override
    public void afterActivityCreat() {
        mainFragment2Presenter.getData();
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<Fragment>();
        f1 = new MainFragment1();
        f2 = new MainFragment1();
        f3 = new MainFragment1();
        f4 = new MainFragment1();
        mFragments.add(f1);
        mFragments.add(f2);
        mFragments.add(f3);
        mFragments.add(f4);
        mAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, titles);
        pager.setAdapter(mAdapter);
        tabIndictor.setViewPager(pager);
        tabIndictor.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        f1.getMainFragment1Presenter().getData();
                        break;
                    case 1:
                        f1.getMainFragment1Presenter().getData();
                        break;
                    case 2:
                        f1.getMainFragment1Presenter().getData();
                        break;
                    case 3:
                        f1.getMainFragment1Presenter().getData();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onSuccess(String result) {
        dimissLoading();
    }

    @Override
    public void onEmpty() {
//        showSpecificView(R.layout.page_empty);
    }

    @Override
    public void netError() {
//        showSpecificView(R.layout.page_error);
    }

    @Override
    public void showLoading() {
//        showSpecificView(R.layout.page_loading);
    }

    @Override
    public void dimissLoading() {
        dismissLoadingView();
    }
}
