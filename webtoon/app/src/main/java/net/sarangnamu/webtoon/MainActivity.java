package net.sarangnamu.webtoon;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.sarangnamu.common.widget.tab.BkTabLayout;
import net.sarangnamu.webtoon.controls.SlideViewManager;
import net.sarangnamu.webtoon.controls.ViewManager;
import net.sarangnamu.webtoon.views.getscene.GetSceneFrgmt;
import net.sarangnamu.webtoon.views.main.MainFrgmt;
import net.sarangnamu.webtoon.views.my.MyFrgmt;
import net.sarangnamu.webtoon.views.splash.SplashFrgmt;
import net.sarangnamu.webtoon.views.store.StoreFrgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final Logger mLog = LoggerFactory.getLogger(MainActivity.class);

    @BindView(R.id.tab_layout)
    BkTabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initFrgmt();
        initSplash();
        initTab(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (!SplashFrgmt.isEnded()) {
            return ;
        }

        super.onBackPressed();
    }

    private void initTab(Bundle savedInstanceState) {
//        0xFFD9D9D9
        mTabLayout.setSeparatorPosition(BkTabLayout.SeparatorPosition.TOP, 0xFF000000);

        for (int i=0; i<5; ++i) {
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab " + (i + 1)).setIcon(R.drawable.main_tab_ic_selector));
        }

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.addOnTabSelectedListener(new BkTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(BkTabLayout.Tab tab) {
                if (mLog.isDebugEnabled()) {
                    mLog.debug("MAIN TAB SELECTED : " + tab.getPosition());
                }

                switch (tab.getPosition()) {
                    case 1:
                        break;
                    case 2:
                        ViewManager.getInstance().add(R.id.view_main, StoreFrgmt.class);
                        break;
                    case 3:
                        ViewManager.getInstance().add(R.id.view_main, GetSceneFrgmt.class);
                        break;
                    case 4:
                        ViewManager.getInstance().add(R.id.view_main, MyFrgmt.class);
                        break;
                    default:
                        if (SplashFrgmt.isEnded()) {
                            ViewManager.getInstance().add(R.id.view_main, MainFrgmt.class);
                        }
                        break;
                }
            }

            @Override
            public void onTabUnselected(BkTabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(BkTabLayout.Tab tab) {
            }
        });
    }

    private void initFrgmt() {
        ViewManager.getInstance().setFragmentManager(MainActivity.this);
        SlideViewManager.getInstance().setFragmentManager(MainActivity.this);
    }

    private void initSplash() {
        ViewManager.getInstance().replace(R.id.root_layout, SplashFrgmt.class);
        SplashFrgmt.setListener(() -> {
            ViewManager.getInstance().popBack();
            ViewManager.getInstance().add(R.id.view_main, MainFrgmt.class);
        });
    }

    public void showFragmentFromRootLayout(Class<?> clazz) {
        if (mLog.isDebugEnabled()) {
            mLog.debug("show setting menu");
        }

        SlideViewManager.getInstance().replace(R.id.root_layout, clazz);
    }
}
