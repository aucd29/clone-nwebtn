package net.sarangnamu.webtoon.views.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.sarangnamu.common.widget.viewpager.BkDotIndicator;
import net.sarangnamu.common.widget.viewpager.BkViewPager;
import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.model.Cfg;
import net.sarangnamu.webtoon.views.ViewPagerFrgmtBase;
import net.sarangnamu.webtoon.views.main.sub.MainSubFrgmt;

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
    BkDotIndicator mIndicator;

    @Override
    protected void initTab() {
        for (int i=0; i<9; ++i) {
            mTabLayout.addTab(mTabLayout.newTab().setText("" + i));
        }

        mIndicator.setCount(Cfg.BANNER_COUNT);
        mIndicator.setActiveDot(0);
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        mBanner.setAdapter(new MainBannerPagerAdapter(4));
        mBanner.setAutoScroll(5000);
        mBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int bannerDiv = position % Cfg.BANNER_COUNT;
                mLog.debug("@@ onPageScrolled : " + position + ", DIV : " + bannerDiv);

                mIndicator.setActiveDot(bannerDiv);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageScrollStateChanged(int state) { }
        });
    }

    @Override
    protected Fragment getViewPagerItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Cfg.POSITION, position);

        Fragment frgmt = new MainSubFrgmt();
        frgmt.setArguments(bundle);

        return frgmt;
    }

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
            int pos = position % 3;

//            mLog.debug("BANNER POSITION : " + pos);

            switch (pos) {
                case 0:
                    color = android.R.color.holo_blue_bright;
                    break;
                case 1:
                    color = android.R.color.background_light;
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
//            mLog.debug("BANNER DESTROY POS : " + position);
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
