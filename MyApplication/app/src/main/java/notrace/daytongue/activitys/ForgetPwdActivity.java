package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;

public class ForgetPwdActivity extends BaseActivity {

    private EditText et_name;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        setNavigation("忘记密码");
        btn_next= (Button) findViewById(R.id.btn_forgetpwd_next);
        et_name= (EditText) findViewById(R.id.et_forgetpwd_name);
    }

    @Override
    public void bindListener() {
        btn_next.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {


        switch (v.getId())
        {
            case R.id.btn_forgetpwd_next:
                break;
        }
    }
}
