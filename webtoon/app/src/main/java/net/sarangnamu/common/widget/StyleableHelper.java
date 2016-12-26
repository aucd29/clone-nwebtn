package net.sarangnamu.common.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.lang.reflect.Field;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 10. 19.. <p/>
 */
public class StyleableHelper {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(StyleableHelper.class);

    public static Styleable init(Context context, TypedArray typedArr, String prefix) {
        return new Styleable(context, typedArr, prefix);
    }

    public static Styleable init(Context context, AttributeSet set, String prefix) {
        int attrs[] = getStyleableArrayId(context, prefix);
        TypedArray typedArr = context.obtainStyledAttributes(set, attrs);
        return new Styleable(context, typedArr, prefix);
    }

    // http://stackoverflow.com/questions/13816596/accessing-declare-styleable-resources-programatically
    private static int[] getStyleableArrayId(Context context, String name) {
        try {
            //use reflection to access the resource class
            Field[] fields2 = Class.forName(context.getPackageName() + ".R$styleable").getFields();

            //browse all fields
            for (Field f : fields2) {
                //pick matching field
                if (f.getName().equals(name)) {
                    //return as int array
                    return (int[]) f.get(null);
                }
            }
        } catch (Throwable ignored) {
        }

        return null;
    }

    public static class Styleable {
        private Context mContext;
        private TypedArray mTypeArr;
        private String mPrefix;

        Styleable(Context context, TypedArray typedArr, String prefix) {
            mContext = context;
            mTypeArr = typedArr;
            mPrefix  = prefix;
        }

        public int getDimensionPixelSize(String resid, int defaultValue) {
            int id = getStyleableId(mContext, resid);
            return mTypeArr.getDimensionPixelSize(id, defaultValue);
        }

        public Drawable getDrawable(String resid) {
            int id = getStyleableId(mContext, resid);
            return mTypeArr.getDrawable(id);
        }

        public CharSequence getText(String resid) {
            int id = getStyleableId(mContext, resid);
            return mTypeArr.getText(id);
        }

        public ColorStateList getColorStateList(String resid) {
            int id = getStyleableId(mContext, resid);
            return mTypeArr.getColorStateList(id);
        }

        public void recycle() {
            mTypeArr.recycle();
        }

        private int getStyleableId(Context context, String name) {
            try {
                name = mPrefix + "_" + name;
                //use reflection to access the resource class
                Field[] fields2 = Class.forName(context.getPackageName() + ".R$styleable").getFields();

                //browse all fields
                for (Field f : fields2) {
                    //pick matching field
                    if (f.getName().equals(name)) {
                        //return as int array
                        return (int) f.get(null);
                    }
                }
            } catch (Throwable ignored) {
            }

            return -1;
        }
    }
}
