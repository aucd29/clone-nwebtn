package net.sarangnamu.webtoon.views.my.sub;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import net.sarangnamu.common.ui.tab.BkTabLayout;
import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.views.NoPrefixFrgmtBase;

import butterknife.BindView;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 9.. <p/>
 */
public class MyWebtoonFrgmt extends NoPrefixFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MyWebtoonFrgmt.class);

    @BindView(R.id.my_webtoon_tab_layout)
    BkTabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void initLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        if (mLog.isDebugEnabled()) {
            mLog.debug("TAB COUNT : " + mTabLayout.getTabCount());
        }

        final PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), mTabLayout.getTabCount());

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
    // PagerAdapter
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mCount;

        public PagerAdapter(FragmentManager fm, int count) {
            super(fm);

            mCount = count;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:  return new MyFavoriteFrgmt();
                case 1:  return new MyRecentlyFrgmt();
                case 2:  return new MyTempFrgmt();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return mCount;
        }
    }
}
