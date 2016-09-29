package net.sarangnamu.common.widget.viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 12.. <p/>
 */
public class BkBannerViewPager extends ViewPager {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkBannerViewPager.class);
    private int mAutoScrollDelay = 0;

    private boolean mActionDown = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mScrollRunnable;
    private OnPageChangeListener mPageListener;

    public BkBannerViewPager(Context context) {
        super(context);
        initLayout();
    }

    public BkBannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    protected void initLayout() {
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);

        if (adapter instanceof BkBannerPagerAdapter) {
            setCurrentItem(((BkBannerPagerAdapter) adapter).getRealCount() * 1000);
        }
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
                    // touch down 이 있는 상태에는 runnable 을 2번 등록
                    // 될 수 있기 때문에 이를 진행하지 않게 하기 위해서
                    // flag 를 하나 둔다
                    if (mActionDown) {
                        mActionDown = false;
                        return ;
                    }

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

    private synchronized void doAutoScroll() {
        removeScrollRunnable();

        if (mAutoScrollDelay == 0) {
            return ;
        }

        mScrollRunnable = () -> setCurrentItem(getCurrentItem() + 1);
        mHandler.postDelayed(mScrollRunnable, mAutoScrollDelay);
    }

    private synchronized void removeScrollRunnable() {
        if (mScrollRunnable != null) {
            mHandler.removeCallbacks(mScrollRunnable);
            mScrollRunnable = null;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // touch down 를 하게 되면 auto scrolling 을 멈추기 위해
                // 등록해 두었던 runnable 를 remove 한다
                mActionDown = true;
                removeScrollRunnable();
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                // touch up 을 하면 다시 auto scrolling 을 하기 위해
                // runnable 을 등록 한다
                if (mScrollRunnable == null) {
                    doAutoScroll();
                }
                break;
        }

        return super.onTouchEvent(ev);
    }
}
