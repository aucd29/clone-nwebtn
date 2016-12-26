package net.sarangnamu.webtoon.views.main;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import net.sarangnamu.common.BkApp;
import net.sarangnamu.common.DimTool;
import net.sarangnamu.common.ani.AnimatorEndListener;
import net.sarangnamu.common.frgmt.FrgmtManager;
import net.sarangnamu.common.widget.scroll.BkScrollView;
import net.sarangnamu.common.widget.viewpager.BkBannerViewPager;
import net.sarangnamu.common.widget.viewpager.BkBannerPagerAdapter;
import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.controls.ViewManager;
import net.sarangnamu.webtoon.model.Cfg;
import net.sarangnamu.webtoon.views.ViewPagerFrgmtBase;
import net.sarangnamu.webtoon.views.game.GameFrgmt;
import net.sarangnamu.webtoon.views.main.sub.MainGridFrgmt;
import net.sarangnamu.webtoon.views.main.sub.MainListFrgmt;
import net.sarangnamu.webtoon.views.search.SearchFrgmt;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 4.. <p/>
 */
public class MainFrgmt extends ViewPagerFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MainFrgmt.class);

    @BindView(R.id.moving_title)
    RelativeLayout mMovingTitle;

    @BindView(R.id.moving_banner)
    RelativeLayout mMovingBanner;

    @BindView(R.id.moving_banner_image)
    ImageView mMovingBannerImage;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.banner)
    BkBannerViewPager mBanner;

    @BindView(R.id.indicator)
    DotIndicator mIndicator;

    @BindView(R.id.scroll)
    BkScrollView mScroll;

    private ObjectAnimator mMovingTitleAnimator;

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
                DimTool.dpToPixelInt(getContext(), Cfg.BANNER_HEIGHT)));

        int viewPagerHeight = (int) (BkApp.screenY() - dpToPixel(Cfg.BANNER_HEIGHT) + Cfg.actionBarHeight(getActivity()));
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mViewPager.getLayoutParams();
        lp.height = viewPagerHeight;

        initScroll();
    }

    private void initScroll() {
        final int hiddenTitleHeight = Cfg.actionBarHeight(getActivity());
        mMovingTitle.setTranslationY(hiddenTitleHeight * -1);
        mMovingBanner.setTranslationY(hiddenTitleHeight);

        mScroll.setOnScrollYListener(value -> {
            if (mMovingTitle.getVisibility() == View.GONE && value > 0) {
                mLog.debug("y listener value (visible) : " + value);

                mMovingTitle.setVisibility(View.VISIBLE);
                mMovingBanner.setVisibility(View.VISIBLE);

                moveLayout(mMovingTitle, 0, null);
                moveLayout(mMovingBanner, 0, null);
            } else if (mMovingTitle.getVisibility() == View.VISIBLE && value == 0){
                // animation 중에는 animation 요청을 하지 않는다
                if (mMovingTitleAnimator != null && mMovingTitleAnimator.isRunning()) {
                    return ;
                }

                mLog.debug("y listener value (gone)    : " + value);
                mMovingTitleAnimator = moveLayout(mMovingTitle, hiddenTitleHeight * -1, new AnimatorEndListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mMovingTitle.setVisibility(View.GONE);
                        mMovingTitleAnimator = null;
                    }
                });

                moveLayout(mMovingBanner, hiddenTitleHeight, new AnimatorEndListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mMovingBanner.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private ObjectAnimator moveLayout(View view, int y, AnimatorEndListener listener) {
        ObjectAnimator obj = ObjectAnimator.ofFloat(view, "translationY", y);

        if (listener != null) {
            obj.addListener(listener);
        }

        obj.start();

        return obj;
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

    class MainBannerPagerAdapter extends BkBannerPagerAdapter {
        public MainBannerPagerAdapter(int count) {
            super(count);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int color;
            int pos = position % mRealCount;

            switch (pos) {
                case 0:          color = android.R.color.holo_blue_dark;                   break;
                case 1:          color = android.R.color.holo_orange_dark;                 break;
                case 2:          color = android.R.color.holo_red_dark;                    break;
                default:         color = android.R.color.holo_green_dark;                  break;
            }

            View view = new View(getActivity());
            view.setClickable(true);
            view.setBackgroundResource(color);
            view.setOnClickListener(v -> {
                Bundle db = new Bundle();
                db.putInt(Cfg.POSITION, pos);

                ViewManager.getInstance().replace(R.id.view_main, MainListFrgmt.class, db, FrgmtManager::setSlideTransition);
            });

            container.addView(view);

            return view;
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

        ViewManager.getInstance().replace(R.id.root_layout, GameFrgmt.class, FrgmtManager::setSlideTransition);
    }

    @OnClick(R.id.to_left)
    void toLeft(View view) {
        mLog.debug("to left");

        // change order by
    }

    @OnClick(R.id.to_right)
    void toRight(View view) {
        mLog.debug("to right");

        // change order by
    }

    @OnClick(R.id.search)
    void showSearch(View view) {
        mLog.debug("show search");

        ViewManager.getInstance().replace(R.id.root_layout, SearchFrgmt.class, FrgmtManager::setSlideTransition);
    }
}
