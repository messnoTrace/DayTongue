package notrace.daytongue.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.adapters.TopicAdapter;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.Topic;
import notrace.daytongue.entitys.Topics;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.views.UnScrollableListView;

public class SearchActivity extends BaseActivity {



    private UnScrollableListView uslv_search;
    private SwipeRefreshLayout srfl_search;

    private TopicAdapter adapter;
    private List<Topic> list;
    private int page=10;
    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        setNavigation("搜索");
        uslv_search= (UnScrollableListView) findViewById(R.id.uslv_search);
        srfl_search= (SwipeRefreshLayout) findViewById(R.id.srfl_search);
    }

    @Override
    public void bindListener() {

        srfl_search.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    public void initData() {

        key=getIntent().getStringExtra("key");
        list=new ArrayList<>();
        adapter=new TopicAdapter(SearchActivity.this,list,R.layout.item_space);
        uslv_search.setAdapter(adapter);
        loadData();
    }

    @Override
    public void onClick(View v) {

    }

    private void loadData(){

        RequestHelper.GetOnlyTopiSerch(key, String.valueOf(page), MyApplication.currentUser.getUcode(), new RequestCallBack<Topics>() {
            @Override
            public void onSuccess(Topics topics) {
                list=topics.getItem();
                srfl_search.setRefreshing(false);
                adapter.setData(list);
            }

            @Override
            public void onFail(String msg) {

            }
        });
//        RequestHelper.getTopic("10", "", "0", MyApplication.currentUser.getUcode(), "");
    }
}
