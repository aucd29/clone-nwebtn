package net.sarangnamu.webtoon.controls;

import android.support.v4.app.FragmentTransaction;

import net.sarangnamu.common.frgmt.FrgmtManager;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 4.. <p/>
 */
public class SlideViewManager extends FrgmtManager {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(SlideViewManager.class);

    private static SlideViewManager mInst;

    public static SlideViewManager getInstance() {
        if (mInst == null) {
            mInst = new SlideViewManager();
        }

        return mInst;
    }

    private SlideViewManager() {

    }

    @Override
    protected void setTransition(FragmentTransaction trans) {
        setSlideTransition(trans);
    }
}
