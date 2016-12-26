package net.sarangnamu.common.widget.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.sarangnamu.common.DimTool;
import net.sarangnamu.common.ui.LpInst;
import net.sarangnamu.common.widget.StyleableHelper;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 10. 19.. <p/>
 */
public class IconButton extends LinearLayout {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(IconButton.class);

    protected ImageView mIcon;
    protected TextView mText;
    protected boolean mEnabled;

    public IconButton(Context context) {
        super(context);
        initLayout(null);
    }

    public IconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(attrs);
    }

    public IconButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayout(attrs);
    }

    protected void initLayout(AttributeSet attrs) {
        StyleableHelper.Styleable style = StyleableHelper.init(getContext(), attrs, "bk_icon_button");

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setDuplicateParentStateEnabled(true);
        setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mIcon.setSelected(!mIcon.isSelected());
                    break;
            }

            return true;
        });

        mIcon = new ImageView(getContext());
        mText = new TextView(getContext());

        mIcon.setBackgroundColor(0x00ffffff); // transparent

        Drawable icon;
        CharSequence text;
        ColorStateList textColor;

        int iconLeft, iconTop, iconRight, iconBottom;
        int textLeft, textTop, textRight, textBottom;
        int textSize;

        try {
            icon = style.getDrawable("bk_icon");
            text = style.getText("bk_text");

            iconLeft   = style.getDimensionPixelSize("bk_icon_left_margin", -1);
            iconTop    = style.getDimensionPixelSize("bk_icon_top_margin", -1);
            iconRight  = style.getDimensionPixelSize("bk_icon_right_margin", -1);
            iconBottom = style.getDimensionPixelSize("bk_icon_bottom_margin", -1);

            textLeft   = style.getDimensionPixelSize("bk_text_left_margin", -1);
            textTop    = style.getDimensionPixelSize("bk_text_top_margin", -1);
            textRight  = style.getDimensionPixelSize("bk_text_right_margin", -1);
            textBottom = style.getDimensionPixelSize("bk_text_bottom_margin", -1);

            textSize   = style.getDimensionPixelSize("bk_text_size", -1);
            textColor  = style.getColorStateList("bk_text_color");
        } finally {
            style.recycle();
            style = null;
        }

        if (icon != null) {
            mIcon.setImageDrawable(icon);
        }

        setMargin(mIcon, iconLeft, iconTop, iconRight, iconBottom);
        setMargin(mText, textLeft, textTop, textRight, textBottom);

        if (text != null) {
            mText.setText(text);
        }

        if (textSize != -1) {
            mText.setTextSize(textSize);
        }

        if (textColor != null) {
            mText.setTextColor(textColor);
        }

        addView(mIcon);
        addView(mText);
    }

    private void setMargin(View view, int left, int top, int right, int bottom) {
        LayoutParams lp = LpInst.linearWw();
        if (left != -1) {
            lp.leftMargin = left;
        }

        if (top != -1) {
            lp.topMargin = top;
        }

        if (right != -1) {
            lp.rightMargin = right;
        }

        if (bottom != -1) {
            lp.bottomMargin = bottom;
        }

        view.setLayoutParams(lp);
    }

    // ICON

    public void setIcon(@DrawableRes int resid) {
        mIcon.setImageResource(resid);
    }

    public void setIconMargin(float left, float top, float right, float bottom) {
        LayoutParams lp = (LayoutParams) mIcon.getLayoutParams();
        if (lp == null) {
            lp = LpInst.linearWw();
        }

        lp.leftMargin   = DimTool.dpToPixelInt(getContext(), left);
        lp.topMargin    = DimTool.dpToPixelInt(getContext(), top);
        lp.rightMargin  = DimTool.dpToPixelInt(getContext(), right);
        lp.bottomMargin = DimTool.dpToPixelInt(getContext(), bottom);

        mIcon.setLayoutParams(lp);
    }

    public boolean isIconSelected() {
        return mIcon.isSelected();
    }

    public void setIconSelected(boolean selected) {
        mIcon.setSelected(selected);
    }

    // TEXT

    public void setText(@NonNull String text) {
        mText.setText(text);
    }

    public void setText(@StringRes int resid) {
        mText.setText(resid);
    }

    public void setTextMargin(float left, float top, float right, float bottom) {
        LayoutParams lp = (LayoutParams) mText.getLayoutParams();
        if (lp == null) {
            lp = LpInst.linearWw();
        }

        lp.leftMargin   = DimTool.dpToPixelInt(getContext(), left);
        lp.topMargin    = DimTool.dpToPixelInt(getContext(), top);
        lp.rightMargin  = DimTool.dpToPixelInt(getContext(), right);
        lp.bottomMargin = DimTool.dpToPixelInt(getContext(), bottom);

        mText.setLayoutParams(lp);
    }
}
