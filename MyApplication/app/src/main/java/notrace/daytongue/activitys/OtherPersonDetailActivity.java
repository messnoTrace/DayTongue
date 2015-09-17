package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;
import notrace.daytongue.views.CircleImageView;

public class OtherPersonDetailActivity extends BaseActivity {

    private TextView tv_nick,tv_sign,tv_realname,tv_sex,tv_birthday,tv_profession,tv_city,tv_style,tv_mobile;
    private CircleImageView civ_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person_detail);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {
        civ_head= (CircleImageView) findViewById(R.id.civ_otherpersondetail_head);
        tv_nick= (TextView) findViewById(R.id.tv_otherpersondetail_name);
        tv_sign= (TextView) findViewById(R.id.tv_otherpersondetail_sign);
        tv_sex= (TextView) findViewById(R.id.tv_otherpersondetail_sex);
        tv_birthday= (TextView) findViewById(R.id.tv_otherpersondetail_birthday);
        tv_profession= (TextView) findViewById(R.id.tv_otherpersondetail_profession);
        tv_city= (TextView) findViewById(R.id.tv_otherpersondetail_city);
        tv_style= (TextView) findViewById(R.id.tv_otherpersondetail_style);
        tv_mobile= (TextView) findViewById(R.id.tv_otherpersondetail_mobile);

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
