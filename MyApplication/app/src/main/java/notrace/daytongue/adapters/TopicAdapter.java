package notrace.daytongue.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.activitys.CommentActivity;
import notrace.daytongue.activitys.ImageViewerActivity;
import notrace.daytongue.activitys.OtherPersonCenterActivity;
import notrace.daytongue.commen.CommonConst;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.commen.XMLParser;
import notrace.daytongue.entitys.Comment;
import notrace.daytongue.entitys.Comments;
import notrace.daytongue.entitys.Photo;
import notrace.daytongue.entitys.Topic;
import notrace.daytongue.entitys.response.BaseResult;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.views.TwoStatusTextView;
import notrace.daytongue.views.UnScrollableGridView;
import notrace.daytongue.views.UnScrollableListView;
import notrace.daytongue.xmlutils.XmlUtils;

/**
 * Created by notrace on 2015/9/18.
 */
public class TopicAdapter extends CommomAdapter<Topic> {



    private boolean isGoodClick=false;
    private int currentItem;
    public TopicAdapter(Context context, List<Topic> mDatas, int mItemLayoutId) {
        super(context, mDatas, mItemLayoutId);
    }

    private CommomViewHolder currentHolder;
    @Override
    public void convert(final CommomViewHolder mHolder, Topic item, int position) {
        final  int p=position;
        currentItem=position;
        currentHolder=mHolder;
        mHolder.setImageUri(R.id.civ_item_space_head, item.getUserHead());
        mHolder.setOnClickListener(R.id.civ_item_space_head, listener);
        mHolder.setText(R.id.tv_item_space_terminal, item.getCFrom());
        mHolder.setText(R.id.tv_item_space_time, item.getCreateDate());
        mHolder.setText(R.id.tv_item_space_name, item.getNickName());


//        mHolder.setOnClickListener(R.id.tv_item_space_good, listener);
        mHolder.setOnClickListener(R.id.tv_item_space_comment, listener);
        mHolder.setOnClickListener(R.id.tv_item_space_collection,listener);
        mHolder.setOnClickListener(R.id.tv_item_space_delete,listener);
        mHolder.setOnClickListener(R.id.tv_item_space_report, listener);


        //add good
        final TwoStatusTextView tvGood= (TwoStatusTextView) mHolder.getConvertView().findViewById(R.id.tv_item_space_good);

        checkGood(item.getTCode(),tvGood);

        //此处需要根据Topic中某个变量判断是否点过赞
//        if(tvGood.getTag())
        tvGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //没有点过赞
//                boolean status= (boolean) v.getTag();
//                tvGood.changeStatus();
                addGood((TwoStatusTextView) v);

            }
        });

        //collection
        final TwoStatusTextView tvCollection= (TwoStatusTextView) mHolder.getConvertView().findViewById(R.id.tv_item_space_collection);

        tvCollection.setStatus(true);//default
        tvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tvCollection.changeStatus();
                collection(tvCollection);
            }

        });



        //delete
        TextView tvDelete= (TextView) mHolder.getConvertView().findViewById(R.id.tv_item_space_delete);

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        //report
        TextView tvReport= (TextView) mHolder.getConvertView().findViewById(R.id.tv_item_space_report);
        tvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



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


//        RecyclerView rcv_user= (RecyclerView) mHolder.getConvertView().findViewById(R.id.rcv_item_space_gooditem);
//
//        LinearLayoutManager userManager=new LinearLayoutManager(mContext);
//        userManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rcv_user.setLayoutManager(userManager);


        //comment

        UnScrollableListView uslv_comment= (UnScrollableListView) mHolder.getConvertView().findViewById(R.id.uslv_item_space_commentitem);
        TextView tvCount= (TextView) mHolder.getConvertView().findViewById(R.id.tv_item_space_commentcount);
        initComment(mDatas.get(currentItem).getTCode(),uslv_comment,tvCount);

        uslv_comment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mContext.startActivity(new Intent(mContext, CommentActivity.class).putExtra("tcode",mDatas.get(currentItem).getTCode()));

            }
        });

    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.tv_item_space_good:

                    //good
//                    addGood(currentHolder);

                    break;

                case R.id.tv_item_space_comment:

                    //comment
                    mContext.startActivity(new Intent(mContext, CommentActivity.class).putExtra("tcode",mDatas.get(currentItem).getTCode()));

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

                case R.id.civ_item_space_head:
                    mContext.startActivity(new Intent(mContext, OtherPersonCenterActivity.class).putExtra("ucode",mDatas.get(currentItem).getUCode()));
                    break;

            }
        }
    };

    private void addGood(final TwoStatusTextView textView){
        RequestHelper.addGood(mDatas.get(currentItem).getTCode(), "2", MyApplication.currentUser.getUcode(), "4", new RequestCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                boolean status = XMLParser.getStatusCode(s);

                if (status) {
//                    if(bs){
//                        //已经赞过
//                        textView.setText("点赞");
//                        textView.setTag(false);
//
//                    }else
//                    {
//                        textView.setText("取消赞");
//                        textView.setTag(true);
//                    }

                    textView.changeStatus();
                }


            }

            @Override
            public void onFail(String msg) {

            }
        });

    }

    private void initComment(final String tcode,final UnScrollableListView uslv, final TextView tvCount)
    {

                RequestHelper.getComment(tcode, new RequestCallBack<Comments>() {
                    @Override
                    public void onSuccess(Comments comments) {

                        List<Comment> list_comment = new ArrayList<Comment>();

                        if (comments.getItem().size() <= 5) {
                            list_comment = comments.getItem();
                        } else {
                            for (int i = 0; i < 4; i++) {
                                list_comment.add(comments.getItem().get(i));
                            }
                        }
                        CommomAdapter<Comment> adapter_comment = new CommomAdapter<Comment>(mContext, list_comment, R.layout.item_topic_comment) {
                            @Override
                            public void convert(CommomViewHolder mHolder, Comment item, int position) {
                                mHolder.setImageUri(R.id.civ_item_topic_comment_head, item.getUserHead());
                                mHolder.setText(R.id.tv_item_topic_comment_name, item.getNickName());
                                mHolder.setText(R.id.tv_item_topic_comment_time, item.getCreateDate());
                                mHolder.setText(R.id.tv_item_topic_comment_comment, item.getContents());
                            }
                        };
                        uslv.setAdapter(adapter_comment);

                        if (list_comment.size() >= 1) {
                            tvCount.setText("查看剩下" + list_comment.size() + "条评论");
                        } else {
                            tvCount.setText("暂无评论");
                        }
                        tvCount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mContext.startActivity(new Intent(mContext, CommentActivity.class).putExtra("tcode", mDatas.get(currentItem).getTCode()));
                            }
                        });
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
    }


    private void checkGood(String fcode, final TwoStatusTextView textView){
        final boolean isGood=false;
            RequestHelper.CheckGood(fcode, "2", MyApplication.currentUser.getUcode(), new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {


                    textView.setStatus(XMLParser.getStatusCode(s));
//                    if(XMLParser.getStatusCode(s)){
//                        textView.setText("取消赞");
//                        textView.setTag(true);
//                    }else
//                    {
//                        textView.setText("点赞");
//                        textView.setTag(false);
//                    }
                }

                @Override
                public void onFail(String msg) {

                }
            });
    }
    private void collection(final TwoStatusTextView textView){

        RequestHelper.manageCollecion(mDatas.get(currentItem).getTCode(), "1", MyApplication.currentUser.getUcode(), new RequestCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                BaseResult result= XmlUtils.xmlToBean(s,BaseResult.class);
                if(Integer.valueOf(result.getStatus())>0){
                    textView.changeStatus();
                }

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
    private void delete(){}
    private void report(){}
}
