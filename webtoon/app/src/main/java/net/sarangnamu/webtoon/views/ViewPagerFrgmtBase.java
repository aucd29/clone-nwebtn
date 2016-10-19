package net.sarangnamu.webtoon.views;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import net.sarangnamu.common.InflateFrgmtBase;
import net.sarangnamu.common.widget.tab.BkTabLayout;
import net.sarangnamu.webtoon.R;

import butterknife.BindView;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 11.. <p/>
 */
public abstract class ViewPagerFrgmtBase extends InflateFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(ViewPagerFrgmtBase.class);

    @BindView(R.id.tab_layout)
    public BkTabLayout mTabLayout;

    @BindView(R.id.view_pager)
    public ViewPager mViewPager;

    @Override
    protected void initLayout() {
        initTab();

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final BkPagerAdapter adapter = new BkPagerAdapter(getChildFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new BkTabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new BkTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(BkTabLayout.Tab tab) {
                if (mLog.isDebugEnabled()) {
                    mLog.debug("TAB SELECTED : " + tab.getPosition());
                }

                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(BkTabLayout.Tab tab) { }
            @Override
            public void onTabReselected(BkTabLayout.Tab tab) { }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // ABSTRACT
    //
    ////////////////////////////////////////////////////////////////////////////////////

    protected abstract void initTab();
    protected abstract Fragment getViewPagerItem(int position);

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // PagerAdapter
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public class BkPagerAdapter extends FragmentStatePagerAdapter {
        int mCount;

        public BkPagerAdapter(FragmentManager fm, int count) {
            super(fm);

            mCount = count;
        }

        @Override
        public Fragment getItem(int position) {
            return getViewPagerItem(position);
        }

        @Override
        public int getCount() {
            return mCount;
        }
    }
}
