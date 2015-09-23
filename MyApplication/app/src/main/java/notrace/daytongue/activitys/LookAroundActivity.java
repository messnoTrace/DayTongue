package notrace.daytongue.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;
import notrace.daytongue.adapters.TopicAdapter;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.Topic;
import notrace.daytongue.entitys.Topics;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.views.UnScrollableListView;

public class LookAroundActivity extends BaseActivity {



    private UnScrollableListView uslv_lookaround;
    private SwipeRefreshLayout srfl_lookaround;

    private TopicAdapter adapter;
    private List<Topic> list;
    private int page=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_around);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        setNavigation("随便看看");
        uslv_lookaround= (UnScrollableListView) findViewById(R.id.uslv_lookaround);
        srfl_lookaround= (SwipeRefreshLayout) findViewById(R.id.srfl_lookaround);

    }

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {

        list=new ArrayList<>();
        adapter=new TopicAdapter(LookAroundActivity.this,list,R.layout.item_space);
        uslv_lookaround.setAdapter(adapter);
        loadData();
    }


    @Override
    public void onClick(View v) {

    }

    private void loadData(){

        RequestHelper.getNoneLoginTopic(String.valueOf(page),"", new RequestCallBack<Topics>() {
            @Override
            public void onSuccess(Topics topics) {
                list=topics.getItem();
                adapter.setData(list);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
