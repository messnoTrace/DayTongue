package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;

public class AddCommentActivity extends BaseActivity {

    private TextView tv_add,tv_content,tv_text,tv_face;
    private ImageView iv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        tv_add= (TextView) findViewById(R.id.tv_addcomment_add);
        iv_back= (ImageView) findViewById(R.id.iv_comment_back);
        tv_content= (TextView) findViewById(R.id.tv_addcomment_content);
        tv_face= (TextView) findViewById(R.id.tv_addcomment_face);
        tv_text= (TextView) findViewById(R.id.tv_addcomment_text);



    }

    @Override
    public void bindListener() {

        tv_add.setOnClickListener(this);
        tv_text.setOnClickListener(this);
        tv_face.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tv_addcomment_add:
                break;
            case R.id.tv_addcomment_face:
                break;
            case R.id.tv_addcomment_text:
                break;
        }

    }
}
