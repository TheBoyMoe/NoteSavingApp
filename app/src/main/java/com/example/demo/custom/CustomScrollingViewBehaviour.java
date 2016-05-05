package com.example.demo.custom;


import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import timber.log.Timber;

public class CustomScrollingViewBehaviour extends AppBarLayout.ScrollingViewBehavior {

    private Context mContext;
    private AppBarLayout appBarLayout;

    public CustomScrollingViewBehaviour() {
        super();
    }

    public CustomScrollingViewBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context.getApplicationContext();
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        if (appBarLayout == null) {
            appBarLayout = (AppBarLayout) dependency;
        }

        final boolean result = super.onDependentViewChanged(parent, child, dependency);
        final int bottomPadding = calculateBottomPadding(appBarLayout);
        final boolean paddingChanged = bottomPadding != child.getPaddingBottom();
        if (paddingChanged) {
            child.setPadding(
                    child.getPaddingLeft(),
                    child.getPaddingTop(),
                    child.getPaddingRight(),
                    bottomPadding);
            child.requestLayout();
        }
        return paddingChanged || result;
    }


    // Calculate the padding needed to keep the bottom of the view pager's content at the same location on the screen.
    private int calculateBottomPadding(AppBarLayout dependency) {
        // TODO remove status bar height from padding calculations for api >= 21
        final int statusBarHeight = getStatusBarHeight();
        Timber.i("Status bar height: %d", statusBarHeight);
        final int totalScrollRange = dependency.getTotalScrollRange();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return totalScrollRange + dependency.getTop() - statusBarHeight;
        }
        return totalScrollRange + dependency.getTop();
    }



    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}