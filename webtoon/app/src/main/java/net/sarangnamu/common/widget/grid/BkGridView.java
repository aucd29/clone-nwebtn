package net.sarangnamu.common.widget.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 29.. <p/>
 */
public class BkGridView extends GridView {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkGridView.class);

    private boolean mLockScroll = false;
    private ScrollChangedListener mScrollListener;

    public BkGridView(Context context) {
        super(context);
        initLayout();
    }

    public BkGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public BkGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayout();
    }

    protected void initLayout() {

    }

    public void setOnScrollYListener(ScrollChangedListener listener) {
        mScrollListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mScrollListener != null) {
            mScrollListener.onScroll(getScrollY());
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mLockScroll) {
            return false;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLockScroll) {
            return false;
        }

        return super.onTouchEvent(ev);
    }

    public void setLockScroll(boolean lock) {
        mLockScroll = lock;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // LISTENER
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public static interface ScrollChangedListener {
        public void onScroll(int value);
    }
}
