package notrace.daytongue.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.activitys.ImageViewerActivity;
import notrace.daytongue.commen.CommonConst;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.commen.XMLParser;
import notrace.daytongue.entitys.Photo;
import notrace.daytongue.entitys.Topic;
import notrace.daytongue.entitys.response.GoodResult;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.views.UnScrollableGridView;

/**
 * Created by notrace on 2015/9/18.
 */
public class TopicAdapter extends CommomAdapter<Topic> {



    private int currentItem;
    public TopicAdapter(Context context, List<Topic> mDatas, int mItemLayoutId) {
        super(context, mDatas, mItemLayoutId);
    }

    @Override
    public void convert(CommomViewHolder mHolder, Topic item, int position) {
        final  int p=position;
        currentItem=position;
        mHolder.setImageUri(R.id.civ_item_space_head, item.getUserHead());
        mHolder.setText(R.id.tv_item_space_terminal, item.getCFrom());
        mHolder.setText(R.id.tv_item_space_time, item.getCreateDate());
        mHolder.setText(R.id.tv_item_space_name, item.getNickName());


        mHolder.setOnClickListener(R.id.tv_item_space_good,listener);
        mHolder.setOnClickListener(R.id.tv_item_space_comment,listener);
        mHolder.setOnClickListener(R.id.tv_item_space_collection,listener);
        mHolder.setOnClickListener(R.id.tv_item_space_delete,listener);
        mHolder.setOnClickListener(R.id.tv_item_space_report,listener);

//                tv_item_space_comment,tv_item_space_collection,tv_item_space_delete,tv_item_space_report



        //pictures
        UnScrollableGridView usgv_photo= (UnScrollableGridView) mHolder.getConvertView().findViewById(R.id.usgv_photo);
        CommomAdapter<Photo>adapter_photo=new CommomAdapter<Photo>(mContext,item.getContents().getItem(),R.layout.item_image) {
            @Override
            public void convert(CommomViewHolder mHolder, Photo item, final int position) {
                ImageView imageView= (ImageView) mHolder.getConvertView().findViewById(R.id.iv_item_image);
                RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                int width= CommonConst.SCREENWIDTH/3-20;
                lp.width=width;
                lp.height=width;
                ImageLoader.getInstance().displayImage(item.getSmallPath(), imageView);
            }
        };

        //

        usgv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mContext.startActivity(new Intent(mContext, ImageViewerActivity.class).putExtra("contents", mDatas.get(p).getContents()).putExtra("position", position));
            }
        });
        usgv_photo.setAdapter(adapter_photo);


        RecyclerView rcv_user= (RecyclerView) mHolder.getConvertView().findViewById(R.id.rcv_item_space_gooditem);

        LinearLayoutManager userManager=new LinearLayoutManager(mContext);
        userManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcv_user.setLayoutManager(userManager);






    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.tv_item_space_good:

                    //good

                    addGood();

                    break;

                case R.id.tv_item_space_comment:

                    //comment
                    break;

                case R.id.tv_item_space_collection:
                    //collection
                    break;

                case  R.id.tv_item_space_delete:
                    //delete
                    break;

                case R.id.tv_item_space_report:
                    //report
                    break;

            }
        }
    };

    private void addGood(){
        RequestHelper.addGood(mDatas.get(currentItem).getTCode(), "2", MyApplication.currentUser.getUcode(), "4", new RequestCallBack<String>() {
            @Override
            public void onSuccess(String s) {


                GoodResult result= XMLParser.xml2GoodResult(s);

                Log.d("================",result.getString());
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }
}
