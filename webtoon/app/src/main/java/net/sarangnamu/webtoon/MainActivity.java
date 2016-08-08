package net.sarangnamu.webtoon;

import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.roughike.bottombar.OnMenuTabSelectedListener;

import net.sarangnamu.common.FrgmtBase;
import net.sarangnamu.common.frgmt.FrgmtManager;
import net.sarangnamu.webtoon.controls.ViewManager;
import net.sarangnamu.webtoon.views.getcut.GetCutFrgmt;
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

//    @BindView(R.id.root_layout)
//    CoordinatorLayout mRootLayout;
//
//    @BindView(R.id.view_main)
//    FrameLayout mViewMain;

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ButterKnife.bind(this);

        // init
        initFrgmt();
        initSplash();
        initBottomMenu(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }

    private void initBottomMenu(Bundle savedInstanceState) {
        mBottomBar = BottomBar.attach(findViewById(R.id.view_main), savedInstanceState);
        mBottomBar.setMaxFixedTabs(5);
        mBottomBar.noResizeGoodness();

        mBottomBar.setItems(R.menu.main_bottom_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.main_bottom_menu_1:
                        mLog.debug("click menu1");
                        if (SplashFrgmt.isEnded()) {
                            ViewManager.getInstance().add(R.id.view_main, MainFrgmt.class);
                        }
                        break;
                    case R.id.main_bottom_menu_2:
                        mLog.debug("click menu2");
                        // call intent
                        break;
                    case R.id.main_bottom_menu_3:
                        mLog.debug("click menu3");
                        ViewManager.getInstance().add(R.id.view_main, StoreFrgmt.class);
                        break;
                    case R.id.main_bottom_menu_4:
                        mLog.debug("click menu4");
                        ViewManager.getInstance().add(R.id.view_main, GetCutFrgmt.class);
                        break;
                    case R.id.main_bottom_menu_5:
                        mLog.debug("click menu5");
                        ViewManager.getInstance().add(R.id.view_main, MyFrgmt.class);
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });

        mBottomBar.hide();

        // Use custom typeface that's located at the "/src/main/assets" directory. If using with
        // custom text appearance, set the text appearance first.
//        bottomBar.setTypeFace("MyFont.ttf");
    }

    private void initFrgmt() {
        ViewManager.getInstance().setFragmentManager(MainActivity.this);
    }

    private void initSplash() {
        ViewManager.getInstance().add(R.id.root_layout, SplashFrgmt.class);
        SplashFrgmt.setListener(() -> {
            mBottomBar.show();
            ViewManager.getInstance().add(R.id.view_main, MainFrgmt.class);
        });
    }
}
