package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;

public class SendSettingActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_setting);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {
        iv_back= (ImageView) findViewById(R.id.iv_sendsetting_back);
        tv_ok= (TextView) findViewById(R.id.tv_sendsetting_ok);


    }

    @Override
    public void bindListener() {

        iv_back.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.iv_sendsetting_back:
                finish();
                break;
            case R.id.tv_sendsetting_ok:
                break;
        }
    }
}
