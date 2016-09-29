package net.sarangnamu.common.widget.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 9. 29.. <p/>
 */
public class BkBannerPagerAdapter extends PagerAdapter {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkBannerPagerAdapter.class);

    protected int mRealCount;

    public BkBannerPagerAdapter(int count) {
        mRealCount = count;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getRealCount() {
        return mRealCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
