package net.sarangnamu.webtoon.controls;

import android.support.v4.app.FragmentTransaction;

import net.sarangnamu.common.frgmt.FrgmtManager;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 4.. <p/>
 */
public class ViewManager extends FrgmtManager {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(ViewManager.class);

    private static ViewManager mInst;

    public static ViewManager getInstance() {
        if (mInst == null) {
            mInst = new ViewManager();
        }

        return mInst;
    }

    private ViewManager() {

    }
}
