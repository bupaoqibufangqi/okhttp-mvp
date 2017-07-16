package com.jw.mymvp.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/15.
 */

public abstract class BaseFragment extends Fragment {
    private LinearLayout mRoot;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = new LinearLayout(getActivity());
        mRoot.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        View view = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, view);
        initUi(view);
        mRoot.addView(view);
        return mRoot;
    }

    /**
     * 此方法在Activity的OnCreate方法执行完成后调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        afterActivityCreat();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void showSpecificView(int viewId) {
        if (getContext() == null || mRoot == null){
            return;
        }
        View emptyview = View.inflate(getContext(),viewId, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(mRoot.getChildCount() > 1){
            mRoot.removeView(mRoot.getChildAt(mRoot.getChildCount()-1));
        }
        mRoot.getChildAt(0).setVisibility(View.GONE);
        mRoot.addView(emptyview, lp);
    }

    public void dismissLoadingView(){
        if (getContext() == null || mRoot == null){
            return;
        }
        if(mRoot.getChildCount() > 1){
            mRoot.removeView(mRoot.getChildAt(mRoot.getChildCount()-1));
        }
        mRoot.getChildAt(0).setVisibility(View.VISIBLE);
    }

    public abstract int getLayoutId();
    public abstract void initUi(View view);
    public abstract void afterActivityCreat();
}
