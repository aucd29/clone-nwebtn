package net.sarangnamu.webtoon.views.my.sub;

import android.support.v4.app.Fragment;

import net.sarangnamu.webtoon.views.ViewPagerFrgmtBase;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 9.. <p/>
 */
public class MyWebtoonFrgmt extends ViewPagerFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MyWebtoonFrgmt.class);

    @Override
    protected void initTab() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));
    }

    @Override
    protected Fragment getViewPagerItem(int position) {
        switch (position) {
            case 0:  return new MyFavoriteFrgmt();
            case 1:  return new MyRecentlyFrgmt();
            case 2:  return new MyTempFrgmt();
            default: return null;
        }
    }

    @Override
    protected String getPrefixForPage() {
        return "";
    }
}
