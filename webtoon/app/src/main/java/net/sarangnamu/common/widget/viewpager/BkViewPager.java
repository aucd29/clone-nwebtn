package net.sarangnamu.common.widget.viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 12.. <p/>
 */
public class BkViewPager extends ViewPager {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkViewPager.class);
    private int mAutoScrollDelay = 0;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mScrollRunnable;
    private OnPageChangeListener mPageListener;

    public BkViewPager(Context context) {
        super(context);
        initLayout();
    }

    public BkViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    protected void initLayout() {

    }

    public void setAutoScroll(int delay) {
        mAutoScrollDelay = delay;

        if (mAutoScrollDelay == 0){
            if (mPageListener != null) {
                removeOnPageChangeListener(mPageListener);
            }

            mPageListener = null;
        } else {
            mPageListener = new OnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    doAutoScroll();
                }

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
                @Override
                public void onPageScrollStateChanged(int state) {}
            };

            addOnPageChangeListener(mPageListener);
        }

        doAutoScroll();
    }

    private void doAutoScroll() {
        if (mScrollRunnable != null) {
            mHandler.removeCallbacks(mScrollRunnable);
            mScrollRunnable = null;
        }

        if (mAutoScrollDelay == 0) {
            return ;
        }

        mScrollRunnable = () -> setCurrentItem(getCurrentItem() + 1);
        mHandler.postDelayed(mScrollRunnable, mAutoScrollDelay);
    }
}
