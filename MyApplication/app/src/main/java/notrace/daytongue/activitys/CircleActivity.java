package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;

public class CircleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {
        setNavigation("圈子");

    }

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {

    }
}
