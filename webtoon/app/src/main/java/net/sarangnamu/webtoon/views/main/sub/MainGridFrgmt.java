package net.sarangnamu.webtoon.views.main.sub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.sarangnamu.common.frgmt.FrgmtManager;
import net.sarangnamu.common.widget.grid.BkGridView;
import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.controls.ViewManager;
import net.sarangnamu.webtoon.model.Cfg;
import net.sarangnamu.webtoon.views.NoPrefixFrgmtBase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 11.. <p/>
 */
public class MainGridFrgmt extends NoPrefixFrgmtBase implements View.OnClickListener {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MainGridFrgmt.class);

    @BindView(R.id.grid)
    BkGridView mGrid;

    private int mPos;

    @Override
    protected void initLayout() {
        mPos = getArguments().getInt(Cfg.POSITION);

        if (mLog.isDebugEnabled()) {
            mLog.debug("main grid pos : " + mPos);
        }

        mGrid.setNumColumns(3);
        mGrid.setAdapter(new MainSubAdapter());
        mGrid.setOnItemClickListener((adapterView, view, i, l) -> {
            ViewManager.getInstance().replace(R.id.view_main, MainListFrgmt.class, FrgmtManager::setSlideTransition);
        });
        mGrid.setLockScroll(true);

        mPos = getArguments().getInt(Cfg.POSITION);
    }

    @Override
    public void onClick(View v) {
        ViewHolder vh = (ViewHolder) v.getTag();

        Bundle bd = new Bundle();
        bd.putInt(Cfg.POSITION, vh.position);

        ViewManager.getInstance().replace(R.id.view_main, MainListFrgmt.class, bd, FrgmtManager::setSlideTransition);
    }

    class ViewHolder {
        ImageView image;
        TextView title;
        ImageView favorite;
        TextView average;
        ImageView newItem;
        TextView writerInfo;
        int position;
    }

    class MainSubAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return Cfg.MAIN_SUB;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder vh;

            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.main_grid_item, null);

                vh = new ViewHolder();
                vh.image      = ButterKnife.findById(view, R.id.image);
                vh.title      = ButterKnife.findById(view, R.id.title);
                vh.favorite   = ButterKnife.findById(view, R.id.favorite);
                vh.average    = ButterKnife.findById(view, R.id.average);
                vh.newItem    = ButterKnife.findById(view, R.id.new_item);
                vh.writerInfo = ButterKnife.findById(view, R.id.writer_info);

                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }

            vh.image.setBackgroundResource(R.drawable.ic_accessibility_black_24dp);
            vh.title.setText("title (" + mPos + ") (" + i + ")");
            vh.writerInfo.setText("writer info");
            vh.position = i;

            view.setOnClickListener(MainGridFrgmt.this);

            return view;
        }
    }
}
