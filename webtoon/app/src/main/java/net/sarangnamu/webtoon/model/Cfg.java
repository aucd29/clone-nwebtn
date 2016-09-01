package net.sarangnamu.webtoon.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import net.sarangnamu.common.BkCfg;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 11.. <p/>
 */
public class Cfg extends BkCfg {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(Cfg.class);
    
    public static final String POSITION = "position";

    // main
    public static final int BANNER_COUNT = 5;
    public static final int MAIN_SUB = 16;
    public static final int BANNER_HEIGHT = 200;

    public static int actionBarHeight(@NonNull Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }

        return -1;
    }
}
