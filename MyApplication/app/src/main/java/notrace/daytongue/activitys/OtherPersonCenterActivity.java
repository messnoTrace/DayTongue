package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.response.GetUserInfoResult;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.views.CircleImageView;

public class OtherPersonCenterActivity extends BaseActivity {


    private LinearLayout ll_focus,ll_fans,ll_pics;
    private FrameLayout fl_space,fl_moreinfo;
    private CircleImageView civ_otherpersoncenter_head;
    private TextView tv_otherpersoncenter_nickname,tv_otherpersoncenter_sign,tv_otherpersoncenter_focus,tv_otherpersoncenter_fans,tv_otherpersoncenter_pics;

    private String ucode;



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
        civ_otherpersoncenter_head= (CircleImageView) findViewById(R.id.civ_otherpersoncenter_head);
        tv_otherpersoncenter_nickname= (TextView) findViewById(R.id.tv_otherpersoncenter_nickname);
        tv_otherpersoncenter_sign= (TextView) findViewById(R.id.tv_otherpersoncenter_sign);
        tv_otherpersoncenter_focus= (TextView) findViewById(R.id.tv_otherpersoncenter_focus);
        tv_otherpersoncenter_fans= (TextView) findViewById(R.id.tv_otherpersoncenter_fans);
        tv_otherpersoncenter_pics= (TextView) findViewById(R.id.tv_otherpersoncenter_pics);
    }

    @Override
    public void bindListener() {

        setNavigation("好友中心");
        ll_focus.setOnClickListener(this);
        ll_fans.setOnClickListener(this);
        ll_pics.setOnClickListener(this);
        fl_space.setOnClickListener(this);
        fl_moreinfo.setOnClickListener(this);

    }

    @Override
    public void initData() {
        ucode=getIntent().getStringExtra("ucode");
        loadData();
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
    private void loadData(){
        RequestHelper.getUserInfo(ucode, new RequestCallBack<GetUserInfoResult>() {
            @Override
            public void onSuccess(GetUserInfoResult getUserInfoResult) {
                ImageLoader.getInstance().displayImage(getUserInfoResult.getUserHead(),civ_otherpersoncenter_head);
                tv_otherpersoncenter_nickname.setText(getUserInfoResult.getNickName());
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
