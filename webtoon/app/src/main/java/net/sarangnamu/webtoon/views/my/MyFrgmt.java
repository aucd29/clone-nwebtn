package net.sarangnamu.webtoon.views.my;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import net.sarangnamu.webtoon.MainActivity;
import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.controls.my.MyViewManager;
import net.sarangnamu.webtoon.views.ViewFrgmtBase;
import net.sarangnamu.webtoon.views.my.sub.MyStoreFrgmt;
import net.sarangnamu.webtoon.views.my.sub.MyWebtoonFrgmt;
import net.sarangnamu.webtoon.views.setting.SettingFrgmt;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 4.. <p/>
 */
public class MyFrgmt extends ViewFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MyFrgmt.class);

    @BindView(R.id.my_main_layout)
    FrameLayout mMainLayout;

    @BindView(R.id.my_radio_group)
    RadioGroup mRadioGroup;

    @BindView(R.id.my_setting)
    ImageButton mSetting;

    @Override
    protected void initLayout() {
        MyViewManager.getInstance().setFragmentManager(this);

        mRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if (mLog.isDebugEnabled()) {
                mLog.debug("CHANGED RADIO BTN : " + i);
            }

            switch (i) {
                case R.id.my_webtoon:
                    MyViewManager.getInstance().add(R.id.my_main_layout, MyWebtoonFrgmt.class);
                    break;
                case R.id.my_store:
                    MyViewManager.getInstance().add(R.id.my_main_layout, MyStoreFrgmt.class);
                    break;
            }
        });

        MyViewManager.getInstance().add(R.id.my_main_layout, MyWebtoonFrgmt.class);
    }

    @OnClick(R.id.my_setting)
    public void doSetting(View view) {
        if (mLog.isDebugEnabled()) {
            mLog.debug("clicked settings");
        }

        ((MainActivity) getActivity()).showFragmentFromRootLayout(SettingFrgmt.class);
    }
}
