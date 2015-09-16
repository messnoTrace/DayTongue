package notrace.daytongue.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;
import notrace.daytongue.commen.RequstHelper;
import notrace.daytongue.entitys.response.LoginResult;
import notrace.daytongue.http.RequesCallBack;

public class LoginActivity extends BaseActivity {

    private EditText et_name,et_pwd;

    private Button btn_login,btn_register;
    private String name;
    private String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        bindListener();

    }

    @Override
    public void findViews() {

        btn_login= (Button) findViewById(R.id.btn_login_login);
        btn_register= (Button) findViewById(R.id.btn_login_register);
        et_name= (EditText) findViewById(R.id.et_login_name);
        et_pwd= (EditText) findViewById(R.id.et_login_pwd);
    }

    @Override
    public void bindListener() {

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_login_login:
                //login

                login();

                break;

            case R.id.btn_login_register:

                //register
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
                break;
        }
    }

    private void  login()
    {
        check();

        //// TODO: 2015/9/16  regex check
        RequstHelper.checkLogin(name, pwd, new RequesCallBack<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onFail(String msg) {

            }
        });

    }

    private void check()
    {
        name=et_name.getText().toString().trim();
        pwd=et_pwd.getText().toString().trim();
    }

}
