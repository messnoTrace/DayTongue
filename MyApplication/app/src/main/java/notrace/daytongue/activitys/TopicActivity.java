package notrace.daytongue.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
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

public class TopicActivity extends BaseActivity {



    private UnScrollableListView uslv_space;
    private SwipeRefreshLayout srfl_topic;

    private String uCode;
    private int currentPage=1;

//    private CommomAdapter<Topic>adapter;

    private TopicAdapter adapter;
    private List<Topic>list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        setNavigation("他(她)的每日舌尖");
        uslv_space= (UnScrollableListView) findViewById(R.id.uslv_topic);
        srfl_topic= (SwipeRefreshLayout) findViewById(R.id.srfl_topic);
    }

    @Override
    public void bindListener() {

        srfl_topic.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(currentPage);
            }
        });
    }

    @Override
    public void initData() {
        uCode=getIntent().getStringExtra("uCode");
        list=new ArrayList<>();
//        adapter=new CommomAdapter<Topic>(TopicActivity.this,list,R.layout.item_space) {
//            @Override
//            public void convert(CommomViewHolder mHolder, Topic item, int position) {
//
//
//                final  int p=position;
//
//
//                mHolder.setImageUri(R.id.civ_item_space_head, item.getUserHead());
//                mHolder.setText(R.id.tv_item_space_terminal, item.getCFrom());
//                mHolder.setText(R.id.tv_item_space_time, item.getCreateDate());
//                mHolder.setText(R.id.tv_item_space_name, item.getNickName());
//
//                //GOOD
//                mHolder.setOnClickListener(R.id.tv_item_space_good, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
////                tv_item_space_comment,tv_item_space_collection,tv_item_space_delete,tv_item_space_report
//
//
//
//                UnScrollableGridView usgv_photo= (UnScrollableGridView) mHolder.getConvertView().findViewById(R.id.usgv_photo);
//                CommomAdapter<Photo>adapter_photo=new CommomAdapter<Photo>(TopicActivity.this,item.getContents().getItem(),R.layout.item_image) {
//                    @Override
//                    public void convert(CommomViewHolder mHolder, Photo item, final int position) {
//                        ImageView imageView= (ImageView) mHolder.getConvertView().findViewById(R.id.iv_item_image);
//                        RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) imageView.getLayoutParams();
//                        int width= CommonConst.SCREENWIDTH/3-20;
//                        lp.width=width;
//                        lp.height=width;
//                        ImageLoader.getInstance().displayImage(item.getSmallPath(), imageView);
//                    }
//                };
//
//
//                //
//
//                usgv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        startActivity(new Intent(TopicActivity.this,ImageViewerActivity.class).putExtra("contents",list.get(p).getContents()).putExtra("position",position));
//                    }
//                });
//                usgv_photo.setAdapter(adapter_photo);
//
//            }
//        };
//

        adapter=new TopicAdapter(TopicActivity.this,list,R.layout.item_space);
        uslv_space.setAdapter(adapter);
        loadData(currentPage);
    }

    @Override
    public void onClick(View v) {

    }

    private void loadData(int page)
    {
        RequestHelper.GetUserPicTopic(uCode, String.valueOf(page), new RequestCallBack<Topics>() {
            @Override
            public void onSuccess(Topics topics) {
                Log.d("tag","");

                if(list!=null&&list.size()>0&&currentPage!=1){
                    list.addAll(topics.getItem());
                }else
                {
                    list=topics.getItem();
                }

                srfl_topic.setRefreshing(false);
                adapter.setData(list);
            }

            @Override
            public void onFail(String msg) {
                Log.d("tag","");
            }
        });
    }
}
