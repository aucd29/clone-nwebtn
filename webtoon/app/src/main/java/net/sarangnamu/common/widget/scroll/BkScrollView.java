package net.sarangnamu.common.widget.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 29.. <p/>
 */
public class BkScrollView extends ScrollView {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkScrollView.class);

    private ScrollChangedListener mScrollListener;

    public BkScrollView(Context context) {
        super(context);
        initLayout();
    }

    public BkScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public BkScrollView(Context context, AttributeSet attrs, int defStyle) {
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

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // LISTENER
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public static interface ScrollChangedListener {
        public void onScroll(int value);
    }

}
