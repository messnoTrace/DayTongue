package notrace.daytongue.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;
import notrace.daytongue.utils.PhotoChooseUtil;
import notrace.daytongue.views.CircleImageView;

public class MineDetailActivity extends BaseActivity {

    private CircleImageView civ_head;
    private RelativeLayout rl_nick,rl_qrcode,rl_sign,rl_realname,rl_sex,rl_birthday,rl_city,rl_profession,rl_style,rl_mobile;

    private TextView tv_nick,tv_sign,tv_realname,tv_sex,tv_birthday,tv_city,tv_profession,tv_style,tv_mobile;
    private ImageView iv_qrcode;

    private PhotoChooseUtil photoutil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_detail);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {
        setNavigation("个人资料");
        rl_nick= (RelativeLayout) findViewById(R.id.rl_minedetail_nick);
        rl_qrcode= (RelativeLayout) findViewById(R.id.rl_minedetail_qrcode);
        rl_sign= (RelativeLayout) findViewById(R.id.rl_minedetail_sign);
        rl_realname= (RelativeLayout) findViewById(R.id.rl_minedetail_realname);
        rl_sex= (RelativeLayout) findViewById(R.id.rl_minedetail_sex);
        rl_birthday= (RelativeLayout) findViewById(R.id.rl_minedetail_birthday);
        rl_city= (RelativeLayout) findViewById(R.id.rl_minedetail_city);
        rl_profession= (RelativeLayout) findViewById(R.id.rl_minedetail_profession);
        rl_style= (RelativeLayout) findViewById(R.id.rl_minedetail_style);
        rl_mobile= (RelativeLayout) findViewById(R.id.rl_minedetail_mobile);


        tv_nick= (TextView) findViewById(R.id.tv_minedetail_nick);
        tv_sign= (TextView) findViewById(R.id.tv_minedetail_nick);
        tv_realname= (TextView) findViewById(R.id.tv_minedetail_nick);
        tv_sex= (TextView) findViewById(R.id.tv_minedetail_nick);
        tv_birthday= (TextView) findViewById(R.id.tv_minedetail_nick);
        tv_profession= (TextView) findViewById(R.id.tv_minedetail_nick);
        tv_style= (TextView) findViewById(R.id.tv_minedetail_nick);
        tv_city= (TextView) findViewById(R.id.tv_minedetail_nick);
        tv_mobile= (TextView) findViewById(R.id.tv_minedetail_nick);
        iv_qrcode= (ImageView) findViewById(R.id.iv_minedetail_qrcode);
        civ_head= (CircleImageView) findViewById(R.id.civ_minedetail_head);

    }

    @Override
    public void bindListener() {

        civ_head.setOnClickListener(this);
        rl_nick.setOnClickListener(this);
        rl_qrcode.setOnClickListener(this);
        rl_realname.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_birthday.setOnClickListener(this);
        rl_city.setOnClickListener(this);
        rl_profession.setOnClickListener(this);
        rl_style.setOnClickListener(this);
        rl_mobile.setOnClickListener(this);

    }

    @Override
    public void initData() {

        photoutil=new PhotoChooseUtil(MineDetailActivity.this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.civ_minedetail_head:
                photoutil.showPop(MineDetailActivity.this,findViewById(R.id.ll_minedetail_root),  Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
            case R.id.rl_minedetail_nick:
                break;
            case R.id.rl_minedetail_qrcode:
                break;
            case R.id.rl_minedetail_sign:
                break;
            case R.id.rl_minedetail_realname:
                break;

            case R.id.rl_minedetail_sex:
                break;
            case R.id.rl_minedetail_birthday:
                break;
            case R.id.rl_minedetail_city:
                break;
            case R.id.rl_minedetail_profession:
                break;
            case R.id.rl_minedetail_mobile:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoutil.getResult(resultCode,data,civ_head);
    }


}
