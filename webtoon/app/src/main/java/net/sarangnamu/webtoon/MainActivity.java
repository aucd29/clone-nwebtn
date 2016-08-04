package net.sarangnamu.webtoon;

import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.roughike.bottombar.OnMenuTabSelectedListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final Logger mLog = LoggerFactory.getLogger(MainActivity.class);

    @BindView(R.id.root_layout)
    CoordinatorLayout mRootLayout;

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // init
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
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setMaxFixedTabs(5);
        mBottomBar.noResizeGoodness();

        mBottomBar.setItems(R.menu.main_bottom_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.main_bottom_menu_1:
                        mLog.debug("click menu1");
                        break;
                    case R.id.main_bottom_menu_2:
                        mLog.debug("click menu2");
                        break;
                    case R.id.main_bottom_menu_3:
                        mLog.debug("click menu3");
                        break;
                    case R.id.main_bottom_menu_4:
                        mLog.debug("click menu4");
                        break;
                    case R.id.main_bottom_menu_5:
                        mLog.debug("click menu5");
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });



//        mBottomBar.noTopOffset();

//        mBottomBar.noResizeGoodness();

        // Set the color for the active tab. Ignored on mobile when there are more than three tabs.
//        mBottomBar.setActiveTabColor("#C2185B");

        // Use the dark theme. Ignored on mobile when there are more than three tabs.
//        bottomBar.useDarkTheme(false);
        // Use custom text appearance in tab titles.
//        bottomBar.setTextAppearance(R.style.MyTextAppearance);

        // Use custom typeface that's located at the "/src/main/assets" directory. If using with
        // custom text appearance, set the text appearance first.
//        bottomBar.setTypeFace("MyFont.ttf");
    }


}
