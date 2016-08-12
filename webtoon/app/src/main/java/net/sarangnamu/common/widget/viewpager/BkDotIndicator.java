package net.sarangnamu.common.widget.viewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import net.sarangnamu.common.DimTool;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 12.. <p/>
 */
public class BkDotIndicator extends View {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkDotIndicator.class);

    private int mCount;
    private int mActiveDot;
    private int mDotSpacing;
    private int mX = 0;
    private Paint mPaint;
    private int mDotSize;
    private int mActiveColor, mNormalColor;

    public BkDotIndicator(Context context) {
        super(context);
        initLayout();
    }

    public BkDotIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public BkDotIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayout();
    }

    protected void initLayout() {
        mPaint   = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotSize = (int) (3 * getContext().getResources().getDisplayMetrics().density);
        mDotSpacing = (int) (2 * getContext().getResources().getDisplayMetrics().density);

        mLog.debug("dotsize : " + mDotSize + ", dot spacing : " + mDotSpacing);

        mActiveColor = 0xffffffff;
        mNormalColor = 0xffdedede;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = mCount * (mDotSize + mDotSpacing + getDotSpacing());

        width = resolveSize(width, widthMeasureSpec);

        int height = mDotSize;
        height = resolveSize(height, heightMeasureSpec);

        mLog.debug("width : " + width + ", height : " + height);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawDot(canvas);
    }

    private void drawDot(Canvas canvas) {
        for (int i = 0; i < mCount; i++) {
            if (i == mActiveDot) {
                canvas.drawCircle(mX, 0, mDotSize, mPaint);
            } else {
                canvas.drawCircle(mX, 0, mDotSize, mPaint);
            }

            mX = mX + mDotSize + mDotSpacing;
        }
    }

    public void refresh() {
        mX = 0;
        invalidate();
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
        mActiveDot = 0;
        mX = 0;

        invalidate();
    }

    public int getActiveDot() {
        return mActiveDot;
    }

    public void setActiveDot(int activeDot) {
        this.mActiveDot = activeDot;

        mX = 0;
        invalidate();
    }

    public int getDotSpacing() {
        return mDotSpacing;
    }

    public void setDotSpacing(int dotSpacing) {
        this.mDotSpacing = dotSpacing;

        mX = 0;
        invalidate();
    }
}
