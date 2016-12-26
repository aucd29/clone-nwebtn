package net.sarangnamu.webtoon.views.main.sub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import net.sarangnamu.common.frgmt.FrgmtManager;
import net.sarangnamu.common.widget.button.IconButton;
import net.sarangnamu.webtoon.R;
import net.sarangnamu.webtoon.controls.ViewManager;
import net.sarangnamu.webtoon.model.Cfg;
import net.sarangnamu.webtoon.views.NoPrefixFrgmtBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 22.. <p/>
 */
public class MainListFrgmt extends NoPrefixFrgmtBase implements View.OnClickListener {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MainListFrgmt.class);

    private int mPos;

    @BindView(R.id.list)
    ListView mList;

    @BindView(R.id.like_area)
    IconButton mLike;

    @Override
    protected void initLayout() {
        mPos = getArguments().getInt(Cfg.POSITION);
        mLog.debug("main list position : " + mPos);

        mList.setAdapter(new MainListAdapter());
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // CLICK EVENTS
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @OnClick(R.id.back)
    void back() {
        ViewManager.getInstance().popBack();
    }

    @OnClick(R.id.sharing)
    void sharing() {
        mLog.debug("SHARING");
    }

    @OnClick(R.id.set_area)
    void setArea() {
        mLog.debug("set area");
    }

    @OnClick(R.id.like_area)
    void likeThis() {
        if (mLike.isIconSelected()) {

        }

    }

    @OnClick(R.id.goto_first_page)
    void gotoFirstPage() {
        mLog.debug("goto first page");
    }

    @OnClick(R.id.favorite)
    void setFavorite() {
        mLog.debug("set favorite");
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // ADAPTER
    //
    ////////////////////////////////////////////////////////////////////////////////////

    class ViewHolder {
        ImageView image;
        TextView title;
        ImageView starIcon;
        TextView starText;
        TextView date;
        ImageView musicIcon;

        int position;
    }

    class MainListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return Cfg.MAIN_LIST_COUNT;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder vh;

            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.main_list_item, null);

                vh = new ViewHolder();
                vh.image     = ButterKnife.findById(view, R.id.image);
                vh.title     = ButterKnife.findById(view, R.id.title);
                vh.starIcon  = ButterKnife.findById(view, R.id.star_icon);
                vh.starText  = ButterKnife.findById(view, R.id.star_text);
                vh.date      = ButterKnife.findById(view, R.id.date);
                vh.musicIcon = ButterKnife.findById(view, R.id.music_icon);

                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }

            vh.image.setBackgroundResource(R.drawable.ic_accessibility_black_24dp);
            vh.title.setText("title (" + mPos + ") (" + position + ")");
            vh.starText.setText("9.99");
            vh.date.setText("16.09.30");

            if (true) {
                vh.musicIcon.setVisibility(View.VISIBLE);
            } else {
                vh.musicIcon.setVisibility(View.GONE);
            }

            vh.position = position;
            view.setOnClickListener(MainListFrgmt.this);

            return view;
        }
    }

    @Override
    public void onClick(View v) {
        ViewHolder vh = (ViewHolder) v.getTag();

        Bundle bd = new Bundle();
        bd.putInt(Cfg.POSITION, vh.position);

        ViewManager.getInstance().replace(R.id.view_main, MainDetailFrgmt.class, bd, FrgmtManager::setSlideTransition);
    }

}
