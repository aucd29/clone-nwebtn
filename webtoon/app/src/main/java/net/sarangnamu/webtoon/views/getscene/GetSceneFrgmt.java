package net.sarangnamu.webtoon.views.getscene;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import net.sarangnamu.common.ui.tab.BkTabLayout;
import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.controls.ViewManager;
import net.sarangnamu.webtoon.views.ViewFrgmtBase;
import net.sarangnamu.webtoon.views.getscene.sub.GetSceneHotFrgmt;
import net.sarangnamu.webtoon.views.getscene.sub.GetSceneLegendFrgmt;
import net.sarangnamu.webtoon.views.getscene.sub.GetSceneMyFrgmt;
import net.sarangnamu.webtoon.views.getscene.sub.GetSceneNewFrgmt;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 4.. <p/>
 */
public class GetSceneFrgmt extends ViewFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(GetSceneFrgmt.class);

    @BindView(R.id.tab_layout)
    BkTabLayout mTabLayout;

    @BindView(R.id.my_scene)
    ImageButton mMyScene;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void initLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

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

    @OnClick(R.id.my_scene)
    void doSceneEvent(View view) {
        ViewManager.getInstance().replace(R.id.view_main, GetSceneMyFrgmt.class);
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
                case 0: return new GetSceneHotFrgmt();
                case 1: return new GetSceneNewFrgmt();
                case 2: return new GetSceneLegendFrgmt();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return mCount;
        }
    }
}
