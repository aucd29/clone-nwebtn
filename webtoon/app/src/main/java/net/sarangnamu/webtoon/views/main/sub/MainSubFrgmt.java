package net.sarangnamu.webtoon.views.main.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.model.Cfg;
import net.sarangnamu.webtoon.views.NoPrefixFrgmtBase;

import butterknife.BindView;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 11.. <p/>
 */
public class MainSubFrgmt extends NoPrefixFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MainSubFrgmt.class);

    @Override
    protected void initLayout() {
        int position = getArguments().getInt(Cfg.POSITION);

        if (mLog.isDebugEnabled()) {
            String log = "";
            log += "===================================================================\n";
            log += "position : " + position + "\n";
            log += "===================================================================\n";
            mLog.debug(log);
        }
    }
}
