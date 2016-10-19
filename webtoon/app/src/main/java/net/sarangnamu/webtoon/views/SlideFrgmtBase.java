package net.sarangnamu.webtoon.views;

import net.sarangnamu.common.InflateFrgmtBase;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 4.. <p/>
 */
public class SlideFrgmtBase extends InflateFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(SlideFrgmtBase.class);

    @Override
    protected void initLayout() {
        mBaseView.setAlpha(0);
        mBaseView.setTranslationX(dpToPixel(80));
        mBaseView.animate().alpha(1).setDuration(200).translationX(dpToPixel(0));
    }
}
