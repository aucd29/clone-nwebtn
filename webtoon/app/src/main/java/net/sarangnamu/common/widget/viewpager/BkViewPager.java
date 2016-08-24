package net.sarangnamu.common.widget.viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 12.. <p/>
 */
public class BkViewPager extends ViewPager {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkViewPager.class);
    private int mAutoScrollDelay = 0;

    private boolean mActionDown = false;
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
        // for infinite scrolling
        setCurrentItem(getCurrentItem() * 1000);
    }

//    @Override
//    public void setAdapter(PagerAdapter adapter) {
//        super.setAdapter(adapter);
//
//        setCurrentItem(0);
//    }
//
//    @Override
//    public void setCurrentItem(int item) {
//        setCurrentItem(item, false);
//    }
//
//    @Override
//    public void setCurrentItem(int item, boolean smoothScroll) {
//        if (getAdapter().getCount() == 0) {
//            super.setCurrentItem(item, smoothScroll);
//            return;
//        }
//        item = getOffsetAmount() + (item % getAdapter().getCount());
//        super.setCurrentItem(item, smoothScroll);
//    }
//
//    private int getOffsetAmount() {
//        if (getAdapter().getCount() == 0) {
//            return 0;
//        }
//        if (getAdapter() instanceof InfinitePagerAdapter) {
//            InfinitePagerAdapter infAdapter = (InfinitePagerAdapter) getAdapter();
//            // allow for 100 back cycles from the beginning
//            // should be enough to create an illusion of infinity
//            // warning: scrolling to very high values (1,000,000+) results in
//            // strange drawing behaviour
//            return infAdapter.getRealCount() * 100;
//        } else {
//            return 0;
//        }
//    }
//
//    @Override
//    public int getCurrentItem() {
//        if (getAdapter().getCount() == 0) {
//            return super.getCurrentItem();
//        }
//        int position = super.getCurrentItem();
//        if (getAdapter() instanceof InfinitePagerAdapter) {
//            InfinitePagerAdapter infAdapter = (InfinitePagerAdapter) getAdapter();
//            // Return the actual item position in the data backing InfinitePagerAdapter
//            return (position % infAdapter.getRealCount());
//        } else {
//            return super.getCurrentItem();
//        }
//    }


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
