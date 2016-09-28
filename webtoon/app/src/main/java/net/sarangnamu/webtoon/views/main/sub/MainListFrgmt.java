package net.sarangnamu.webtoon.views.main.sub;

import net.sarangnamu.webtoon.model.Cfg;
import net.sarangnamu.webtoon.views.NoPrefixFrgmtBase;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 22.. <p/>
 */
public class MainListFrgmt extends NoPrefixFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MainListFrgmt.class);

    private int mPos;

    @Override
    protected void initLayout() {
        mPos = getArguments().getInt(Cfg.POSITION);
        mLog.debug("main list position : " + mPos);
    }
}
