package notrace.daytongue.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.response.BaseResult;
import notrace.daytongue.http.RequestCallBack;

class UpdatePwdActivity extends BaseActivity implements View.OnClickListener{

    private EditText et_pwd,et_oldpwd,et_confirmpwd;
    private Button btn_ok;

    private String oldpwd,newpwd,confirmpwd;
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

        setNavigation("修改密码");
        et_pwd=(EditText)findViewById(R.id.et_updatepwd_pwd);
        et_oldpwd=(EditText)findViewById(R.id.et_update_old_pwd);
        et_confirmpwd=(EditText)findViewById(R.id.et_updatepwd_confirmpwd);
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

                update();
                break;
        }
    }

    private void  update(){
        check();


    }

    private void  check(){

        oldpwd=et_oldpwd.getText().toString().trim();
        newpwd=et_pwd.getText().toString().trim();
        confirmpwd=et_confirmpwd.getText().toString().trim();


        RequestHelper.updatePassword(newpwd, MyApplication.currentUser.getUcode(), oldpwd, new RequestCallBack<BaseResult>() {
            @Override
            public void onSuccess(BaseResult baseResult) {
                if(baseResult!=null&&"0".equals(baseResult.getStatus())){
                    //// TODO: 2015/9/17  success
                    logOut();
                    startActivity(new Intent(UpdatePwdActivity.this, LoginActivity.class));
                    finish();


                }
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }
}
