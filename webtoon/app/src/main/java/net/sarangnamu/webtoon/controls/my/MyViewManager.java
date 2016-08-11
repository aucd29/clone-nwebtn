package net.sarangnamu.webtoon.controls.my;

import net.sarangnamu.common.frgmt.FrgmtManager;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 9.. <p/>
 */
public class MyViewManager extends FrgmtManager {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MyViewManager.class);

    private static MyViewManager mInst;

    public static MyViewManager getInstance() {
        if (mInst == null) {
            mInst = new MyViewManager();
        }

        return mInst;
    }

    private MyViewManager() {

    }
}
