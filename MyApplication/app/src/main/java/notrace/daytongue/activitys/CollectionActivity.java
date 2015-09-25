package notrace.daytongue.activitys;

import android.os.Bundle;
import android.view.View;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.commen.RequestHelper;

public class CollectionActivity extends BaseActivity {


    private int page=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        findViews();
        bindListener();
        initData();

    }

    @Override
    public void findViews() {

    }

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {

        loadData();
    }

    private void  loadData(){

        RequestHelper.getCollections(String.valueOf(page), MyApplication.currentUser.getUcode(),"");
    }

    @Override
    public void onClick(View v) {

    }
}
