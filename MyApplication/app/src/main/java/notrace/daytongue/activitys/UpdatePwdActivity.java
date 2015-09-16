package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;

class UpdatePwdActivity extends BaseActivity implements View.OnClickListener{

    private EditText et_pwd;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {
        et_pwd=(EditText)findViewById(R.id.et_updatepwd_pwd);
        btn_ok=(Button)findViewById(R.id.btn_updatepwd_ok);


    }

    @Override
    public void bindListener() {

        btn_ok.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_updatepwd_ok:
                
                break;
        }
    }
}
