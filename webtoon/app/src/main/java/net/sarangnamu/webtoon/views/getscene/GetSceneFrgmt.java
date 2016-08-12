package net.sarangnamu.webtoon.views.getscene;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;

import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.controls.ViewManager;
import net.sarangnamu.webtoon.views.ViewPagerFrgmtBase;
import net.sarangnamu.webtoon.views.getscene.sub.GetSceneHotFrgmt;
import net.sarangnamu.webtoon.views.getscene.sub.GetSceneLegendFrgmt;
import net.sarangnamu.webtoon.views.getscene.sub.GetSceneMyFrgmt;
import net.sarangnamu.webtoon.views.getscene.sub.GetSceneNewFrgmt;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 4.. <p/>
 */
public class GetSceneFrgmt extends ViewPagerFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(GetSceneFrgmt.class);

    @BindView(R.id.my_scene)
    ImageButton mMyScene;

    @Override
    protected void initTab() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));
    }

    @Override
    protected Fragment getViewPagerItem(int position) {
        switch (position) {
            case 0: return new GetSceneHotFrgmt();
            case 1: return new GetSceneNewFrgmt();
            case 2: return new GetSceneLegendFrgmt();
            default: return null;
        }
    }

    @OnClick(R.id.my_scene)
    void showMyScene(View view) {
        ViewManager.getInstance().replace(R.id.view_main, GetSceneMyFrgmt.class);
    }
}
