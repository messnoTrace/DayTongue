package notrace.daytongue.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;

public class SettingActivity extends BaseActivity {

    private RelativeLayout rl_setting_editpwd,rl_setting_cleancache;
    private Button btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        setNavigation("设置中心");
        rl_setting_editpwd= (RelativeLayout) findViewById(R.id.rl_setting_editpwd);
        rl_setting_cleancache= (RelativeLayout) findViewById(R.id.rl_setting_cleancache);
        btn_logout= (Button) findViewById(R.id.btn_setting_logout);
    }

    @Override
    public void bindListener() {

        rl_setting_cleancache.setOnClickListener(this);
        rl_setting_editpwd.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_setting_logout:
                break;
            case R.id.rl_setting_editpwd:

                startActivity(new Intent(SettingActivity.this,UpdatePwdActivity.class));
                break;
            case R.id.rl_setting_cleancache:
                break;
        }
    }
}
