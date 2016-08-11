package net.sarangnamu.webtoon.views;

import net.sarangnamu.common.InflateFrgmtBase;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 9.. <p/>
 */
public abstract class NoPrefixFrgmtBase extends InflateFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(NoPrefixFrgmtBase.class);

    @Override
    protected String getPrefixForPage() {
        return "";
    }
}
