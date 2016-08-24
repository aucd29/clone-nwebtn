package net.sarangnamu.webtoon.views.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import net.sarangnamu.common.DimTool;
import net.sarangnamu.common.widget.viewpager.BkViewPager;
import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.model.Cfg;
import net.sarangnamu.webtoon.views.ViewPagerFrgmtBase;
import net.sarangnamu.webtoon.views.main.sub.MainGridFrgmt;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 4.. <p/>
 */
public class MainFrgmt extends ViewPagerFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MainFrgmt.class);

    @BindView(R.id.hidden_title)
    RelativeLayout mHiddenTitle;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.banner)
    BkViewPager mBanner;

    @BindView(R.id.indicator)
    DotIndicator mIndicator;

    @Override
    protected void initTab() {
        for (int i=0; i<9; ++i) {
            mTabLayout.addTab(mTabLayout.newTab().setText("" + i));
        }

        mIndicator.setNumberOfItems(Cfg.BANNER_COUNT);
        mIndicator.setSelectedItem(0, false);
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        mBanner.setAdapter(new MainBannerPagerAdapter(Cfg.BANNER_COUNT));
        mBanner.setAutoScroll(5000);
        mBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int bannerDiv = position % Cfg.BANNER_COUNT;
                mIndicator.setSelectedItem(bannerDiv, true);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        // FIXME
        // image 를 넣을게 아니니.
        mBanner.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DimTool.dpToPixelInt(getContext(), 200)));
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // ViewPagerFrgmtBase
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected Fragment getViewPagerItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Cfg.POSITION, position);

        Fragment frgmt = new MainGridFrgmt();
        frgmt.setArguments(bundle);

        return frgmt;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // MainBannerPagerAdapter
    //
    ////////////////////////////////////////////////////////////////////////////////////

    class MainBannerPagerAdapter extends PagerAdapter {
        int mCount;

        public MainBannerPagerAdapter(int count) {
            mCount = count;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int color;
            int pos = position % Cfg.BANNER_COUNT;

            switch (pos) {
                case 0:
                    color = android.R.color.holo_blue_dark;
                    break;
                case 1:
                    color = android.R.color.holo_orange_dark;
                    break;
                case 2:
                    color = android.R.color.holo_red_dark;
                    break;
                default:
                    color = android.R.color.holo_green_dark;
                    break;
            }

            View view = new View(getActivity());
            view.setClickable(true);
            view.setBackgroundResource(color);

            container.addView(view);

            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // CLICK EVENTS
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @OnClick(R.id.game)
    void showGame(View view) {
        mLog.debug("show game");
    }

    @OnClick(R.id.to_left)
    void toLeft(View view) {
        mLog.debug("to left");
    }

    @OnClick(R.id.to_right)
    void toRight(View view) {
        mLog.debug("to right");
    }

    @OnClick(R.id.search)
    void showSearch(View view) {
        mLog.debug("show search");
    }
}
