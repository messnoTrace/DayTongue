package notrace.daytongue.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.views.CircleImageView;

public class MineCenterActivity extends BaseActivity implements View.OnClickListener{


    private RelativeLayout rl_attitude,rl_center,rl_collection,rl_circle,rl_suggestion,rl_setting;
    private LinearLayout ll_daytongue,ll_foucs,ll_fans;

    private TextView tv_name,tv_sign,tv_day,tv_foucs,tv_fans;
    private CircleImageView civ_head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_center);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {
//        ((TextView)findViewById(R.id.tv_title)).setText("我的");
//        findViewById(R.id.iv_navigate_back).setOnClickListener(this);

        setNavigation("我的");

        rl_attitude= (RelativeLayout) findViewById(R.id.rl_minecenter_attitude);
        rl_center= (RelativeLayout) findViewById(R.id.rl_minecenter_center);
        rl_collection= (RelativeLayout) findViewById(R.id.rl_minecenter_collection);
        rl_circle= (RelativeLayout) findViewById(R.id.rl_minecenter_circle);
        rl_suggestion= (RelativeLayout) findViewById(R.id.rl_minecenter_suggestion);
        rl_setting= (RelativeLayout) findViewById(R.id.rl_minecenter_setting);

        ll_daytongue= (LinearLayout) findViewById(R.id.ll_minecenter_daytongue);
        ll_foucs= (LinearLayout) findViewById(R.id.ll_minecenter_foucs);
        ll_fans= (LinearLayout) findViewById(R.id.ll_minecenter_fans);

        tv_name= (TextView) findViewById(R.id.tv_minecenter_name);
        tv_sign=(TextView)findViewById(R.id.tv_minecenter_sign);
        civ_head= (CircleImageView) findViewById(R.id.civ_minecenter_head);

        tv_day=(TextView)findViewById(R.id.tv_minecenter_day);
        tv_foucs=(TextView)findViewById(R.id.tv_minecenter_fouces);
        tv_fans=(TextView)findViewById(R.id.tv_minecenter_fans);
    }

    @Override
    public void bindListener() {
        rl_attitude.setOnClickListener(this);
        rl_center.setOnClickListener(this);
        rl_collection.setOnClickListener(this);
        rl_circle.setOnClickListener(this);
        rl_suggestion.setOnClickListener(this);
        rl_setting.setOnClickListener(this);

        ll_daytongue.setOnClickListener(this);
        ll_fans.setOnClickListener(this);
        ll_foucs.setOnClickListener(this);

    }

    @Override
    public void initData() {

        if(MyApplication.currentUser!=null){
            tv_name.setText(MyApplication.currentUser.getNickName());
            com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(MyApplication.currentUser.getUserHead(),civ_head);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
//            case R.id.iv_navigate_back:
//                finish();
//                break;

            case R.id.rl_minecenter_attitude:
                break;
            case R.id.rl_minecenter_center:

                startActivity(new Intent(MineCenterActivity.this,MineDetailActivity.class));
                break;
            case R.id.rl_minecenter_collection:
                break;
            case R.id.rl_minecenter_circle:
                break;
            case R.id.rl_minecenter_suggestion:
                break;
            case R.id.rl_minecenter_setting:
                break;
            case R.id.ll_minecenter_daytongue:
                break;
            case R.id.ll_minecenter_fans:
                break;
            case R.id.ll_minecenter_foucs:
                break;
        }
    }
}
