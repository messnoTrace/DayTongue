package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import emojicon.EmojiconEditText;
import emojicon.EmojiconGridFragment;
import emojicon.EmojiconsFragment;
import emojicon.emoji.Emojicon;
import notrace.daytongue.BaseActivity;
import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.Comment;
import notrace.daytongue.utils.PhoneUtil;

public class AddCommentActivity extends BaseActivity implements EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener{

    private TextView tv_add,tv_text,tv_face;
    private ImageView iv_back;

    EmojiconEditText mEditEmojicon;
    private FrameLayout fl_content;
    private String comment;

    private String tcode;

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
        mEditEmojicon= (EmojiconEditText) findViewById(R.id.editEmojicon);
        tv_face= (TextView) findViewById(R.id.tv_addcomment_face);
        tv_text= (TextView) findViewById(R.id.tv_addcomment_text);
        fl_content= (FrameLayout) findViewById(R.id.emojicons);

        fl_content.setVisibility(View.INVISIBLE);

    }

    @Override
    public void bindListener() {
        tv_add.setOnClickListener(this);
        tv_text.setOnClickListener(this);
        tv_face.setOnClickListener(this);


    }

    @Override
    public void initData() {

        tcode=getIntent().getStringExtra("tcode");
        setEmojiconFragment(false);
    }

    private void setEmojiconFragment(boolean useSystemDefault) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.emojicons, EmojiconsFragment.newInstance(useSystemDefault))
                .commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tv_addcomment_add:

                comment=mEditEmojicon.getText().toString();
                addComment(comment);
                break;
            case R.id.tv_addcomment_face:

                PhoneUtil.hideInputBord(AddCommentActivity.this,mEditEmojicon);
                fl_content.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_addcomment_text:
                PhoneUtil.showInputBord(AddCommentActivity.this, mEditEmojicon);
                fl_content.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_comment_back:
                finish();
                break;
        }

    }

    @Override
    public void onEmojiconBackspaceClicked(View v) {
        EmojiconsFragment.backspace(mEditEmojicon);
    }




    @Override
    public void onEmojiconClicked(Emojicon emojicon) {

        EmojiconsFragment.input(mEditEmojicon, emojicon);
    }

    private  void addComment(String str){

        Comment comment=new Comment();
        comment.setCreateDate(new Date().toString());
        comment.setContents(str);
        comment.setCType("0");
        comment.setFCode(tcode);
        comment.setImgCode("0");
        comment.setUImgCode("0");
        comment.setUserHead(MyApplication.currentUser.getUserHead());
        comment.setNickName(MyApplication.currentUser.getNickName());
        comment.setUCode(MyApplication.currentUser.getUcode());

        RequestHelper.addCommentReturn(comment);
    }
}
