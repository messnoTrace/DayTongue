package notrace.daytongue.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.MainActivity;
import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.response.LoginResult;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.utils.RegExUtil;

public class LoginActivity extends BaseActivity {

    private EditText et_name,et_pwd;

    private Button btn_login;
    private TextView tv_login_forgetpwd,btn_register;
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
        btn_register= (TextView) findViewById(R.id.btn_login_register);
        et_name= (EditText) findViewById(R.id.et_login_name);
        et_pwd= (EditText) findViewById(R.id.et_login_pwd);
        tv_login_forgetpwd= (TextView) findViewById(R.id.tv_login_forgetpwd);
    }

    @Override
    public void bindListener() {

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        tv_login_forgetpwd.setOnClickListener(this);
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

            case R.id.tv_login_forgetpwd:

                //forgetpwd

                startActivity(new Intent(LoginActivity.this,ForgetPwdActivity.class));
                break;
        }
    }

    private void  login()
    {

        if(check()){
            //// TODO: 2015/9/16  regex check
            RequestHelper.checkLogin(name, pwd, new RequestCallBack<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {

                    MyApplication.currentUser = loginResult;

                    //TODO Login success test
//                startActivity(new Intent(LoginActivity.this,MineCenterActivity.class));
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                }

                @Override
                public void onFail(String msg) {
                    Log.d("==============",msg.toString());
                }
            });
        }



    }

    private boolean check()
    {
        name=et_name.getText().toString().trim();
        pwd=et_pwd.getText().toString().trim();

        if("".equals(name)||"".equals("pwd")){

            Toast.makeText(LoginActivity.this,"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(!RegExUtil.isMobile(name))
        {
            return  false;
        }
        return true;
    }

}
