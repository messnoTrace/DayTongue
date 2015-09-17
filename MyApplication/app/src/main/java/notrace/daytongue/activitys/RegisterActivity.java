package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;
import notrace.daytongue.commen.RequstHelper;
import notrace.daytongue.http.RequestCallBack;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private Button btn_register;
    private EditText et_pwd, et_confirmpwd,et_name;

    private String name;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        bindListener();
    }

    @Override
    public void findViews() {
        et_pwd = (EditText) findViewById(R.id.et_register_pwd);
        et_confirmpwd = (EditText) findViewById(R.id.et_register_confirmpwd);
        et_name= (EditText) findViewById(R.id.et_register_name);
        btn_register = (Button) findViewById(R.id.btn_register);

    }

    @Override
    public void bindListener() {

        btn_register.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_register:

                register();
                break;
        }
    }

    public void register()
    {

        checkForm();

        RequstHelper.register(name, pwd, new RequestCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(RegisterActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * check the name  and pwd
     */
    private void checkForm(){
        name=et_name.getText().toString().trim();
        pwd=et_pwd.getText().toString().trim();

        //// TODO: 2015/9/16    check name and pwd
    }
}
