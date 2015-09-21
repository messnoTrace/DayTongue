package notrace.daytongue.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;
import notrace.daytongue.adapters.CommomAdapter;
import notrace.daytongue.adapters.CommomViewHolder;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.Comment;
import notrace.daytongue.entitys.Comments;
import notrace.daytongue.http.RequestCallBack;

public class CommentActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_comment;
    private ListView lv_comment;
    private SwipeRefreshLayout srf_comment;

    private String tcode;

    private CommomAdapter<Comment>adapter;
    private List<Comment>list_comment;

    public static  int REQUESCODE_ADDCOMMENT=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        lv_comment= (ListView) findViewById(R.id.lv_comment);
        iv_back= (ImageView) findViewById(R.id.iv_comment_back);
        tv_comment= (TextView) findViewById(R.id.tv_comment_comment);
        srf_comment= (SwipeRefreshLayout) findViewById(R.id.srf_comment);
    }

    @Override
    public void bindListener() {
        iv_back.setOnClickListener(this);
        tv_comment.setOnClickListener(this);
        srf_comment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    @Override
    public void initData() {
        tcode=getIntent().getStringExtra("tcode");

        list_comment=new ArrayList<>();
        adapter=new CommomAdapter<Comment>(CommentActivity.this,list_comment,R.layout.item_comment) {
            @Override
            public void convert(CommomViewHolder mHolder, Comment item, int position) {
                mHolder.setImageUri(R.id.civ_item_comment_head,item.getUserHead());
                mHolder.setText(R.id.tv_item_comment_name,item.getNickName());
                mHolder.setText(R.id.tv_item_comment_time,item.getCreateDate());
                mHolder.setText(R.id.tv_item_comment_comment,item.getContents());

            }
        };
        lv_comment.setAdapter(adapter);
        loadData();
    }


    private void loadData(){

        RequestHelper.getComment(tcode, new RequestCallBack<Comments>() {
            @Override
            public void onSuccess(Comments comments) {

                list_comment=comments.getItem();
//                if(list_comment!=null&&list_comment.size()>0){
//                    list_comment.addAll(comments.getItem());
//                }else
//                {
//                    list_comment=comments.getItem();
//                }
                adapter.setData(list_comment);
                srf_comment.setRefreshing(false);
            }

            @Override
            public void onFail(String msg) {
                srf_comment.setRefreshing(false);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_comment_back:
                finish();

                break;
            case R.id.tv_comment_comment:
                startActivityForResult(new Intent(CommentActivity.this,AddCommentActivity.class),REQUESCODE_ADDCOMMENT);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESCODE_ADDCOMMENT){

        }
    }
}
