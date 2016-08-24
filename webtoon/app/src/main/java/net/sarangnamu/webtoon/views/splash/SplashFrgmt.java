package net.sarangnamu.webtoon.views.splash;

import android.os.AsyncTask;

import net.sarangnamu.webtoon.views.ViewFrgmtBase;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 8. 5.. <p/>
 */
public class SplashFrgmt extends ViewFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(SplashFrgmt.class);
    private static OnSplashEndListener mListener;
    private static boolean mEnded = false;

    @Override
    protected void initLayout() {
        new AsyncTask<Void, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... data) {
                try {
                    Thread.sleep(1000);
                    // TODO DOWNLOAD NETWORK RESOURCES
                } catch (Exception e) {
                    mLog.error(e.getMessage());
                }
        
                return true;
            }
        
            @Override
            protected void onPostExecute(Boolean result) {
                if (mListener != null) {
                    mEnded = true;
                    mListener.onEnd();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    
    public static void setListener(OnSplashEndListener listener) {
        mListener = listener;
    }

    public static boolean isEnded() {
        return mEnded;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////
    //
    // OnSplashEndListener
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public interface OnSplashEndListener {
        public void onEnd();
    }
}
