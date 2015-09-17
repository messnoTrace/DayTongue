package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;

public class OtherPersonCenterActivity extends BaseActivity {


    private LinearLayout ll_focus,ll_fans,ll_pics;
    private FrameLayout fl_space,fl_moreinfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person_center);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        setNavigation("好友中心");
        ll_focus= (LinearLayout) findViewById(R.id.ll_otherpersoncenter_focus);
        ll_fans= (LinearLayout) findViewById(R.id.ll_otherpersoncenter_fans);
        ll_pics= (LinearLayout) findViewById(R.id.ll_otherpersoncenter_pics);
        fl_space= (FrameLayout) findViewById(R.id.fl_otherpersoncenter_space);
        fl_moreinfo= (FrameLayout) findViewById(R.id.fl_otherpersoncenter_moreinfo);
    }

    @Override
    public void bindListener() {

        ll_focus.setOnClickListener(this);
        ll_fans.setOnClickListener(this);
        ll_pics.setOnClickListener(this);
        fl_space.setOnClickListener(this);
        fl_moreinfo.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ll_otherpersoncenter_fans:
                break;
            case R.id.ll_otherpersoncenter_focus:
                break;
            case R.id.ll_otherpersoncenter_pics:
                break;
            case R.id.fl_otherpersoncenter_space:
                break;
            case R.id.fl_otherpersoncenter_moreinfo:
                break;
        }

    }
}
