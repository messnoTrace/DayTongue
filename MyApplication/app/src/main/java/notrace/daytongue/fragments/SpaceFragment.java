package notrace.daytongue.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.LazyFragment;
import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.activitys.MineCenterActivity;
import notrace.daytongue.adapters.TopicAdapter;
import notrace.daytongue.choosepicture.ChoosePictureDemoActivity;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.Topic;
import notrace.daytongue.entitys.Topics;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.views.CircleImageView;
import notrace.daytongue.views.UnScrollableListView;


public class SpaceFragment extends LazyFragment implements View.OnClickListener{


    private CircleImageView iv_space_personcenter;

    private ImageView iv_space_image;
    private int page=10;


    private UnScrollableListView uslv_space;
    private SwipeRefreshLayout srfl_space;

    private TopicAdapter adapter;
    private List<Topic> list;

    private int currentPage=1;

    private Context mContext;
    @Override
    public void bindListener() {

        iv_space_image.setOnClickListener(this);
        iv_space_personcenter.setOnClickListener(this);
        srfl_space.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    @Override
    public void initData() {
        list=new ArrayList<>();
        adapter=new TopicAdapter(mContext,list,R.layout.item_space);
        uslv_space.setAdapter(adapter);
        loadData();
    }

    @Override
    protected int onLayoutIdGenerated() {
        return R.layout.fragment_space;
    }

    @Override
    protected void onViewCreated(View parentView) {
        mContext=getActivity();
        iv_space_image=findView(parentView, R.id.iv_space_image);
        uslv_space=findView(parentView, R.id.uslv_space);
        srfl_space=findView(parentView,R.id.srfl_space);
        iv_space_personcenter=findView(parentView,R.id.iv_space_personcenter);
        initData();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.iv_space_image:

                startActivity(new Intent(mContext, ChoosePictureDemoActivity.class));

                break;

            case R.id.iv_space_personcenter:
                startActivity(new Intent(mContext, MineCenterActivity.class));
                break;
        }
    }

    private void loadData(){


        ImageLoader.getInstance().displayImage(MyApplication.currentUser.getUserHead(),iv_space_personcenter);
        RequestHelper.getOnlyTopic(String.valueOf(page), "", "0", MyApplication.currentUser.getUcode(), MyApplication.currentUser.getUcode(), "", new RequestCallBack<Topics>() {
            @Override
            public void onSuccess(Topics topics) {
                list=topics.getItem();
                srfl_space.setRefreshing(false);
                adapter.setData(list);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
