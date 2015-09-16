package notrace.daytongue;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.LinkedList;

import notrace.daytongue.commen.CommonConst;
import notrace.daytongue.immersivestatusbar.SystemBarTintManager;

/**
 * Created by notrace on 2015/7/24.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{

    public static LinkedList<Activity> activityList;

    private DisplayMetrics metrics = new DisplayMetrics();
    protected SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityList().add(this);
        getWidthPixels();
        getHeightPixels();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.navigation_bg);
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public static LinkedList<Activity> getActivityList() {
        if (activityList == null) {
            activityList = new LinkedList<Activity>();
        }
        return activityList;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityList().remove(this);
    }

    public abstract void findViews();
    public abstract  void bindListener();

    public abstract  void initData();


    public int getWidthPixels() {
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        CommonConst.SCREENWIDTH=metrics.widthPixels;
        return metrics.widthPixels;
    }

    public int getHeightPixels() {
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        CommonConst.SCREENWIDTH=metrics.heightPixels;
        return metrics.heightPixels;
    }
}
