package notrace.daytongue.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.LazyFragment;
import notrace.daytongue.R;
import notrace.daytongue.activitys.TopicActivity;
import notrace.daytongue.adapters.CommomAdapter;
import notrace.daytongue.adapters.CommomViewHolder;
import notrace.daytongue.commen.CommonConst;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.Banner;
import notrace.daytongue.entitys.response.RCMDModel;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.utils.ViewUtil;
import notrace.daytongue.views.AutoScrollViewPager;
import notrace.daytongue.views.UnScrollableListView;

public class IndexFragment extends LazyFragment {

    private SwipeRefreshLayout sfl_index;
    private FrameLayout fl_index_content;



    private UnScrollableListView uslv_user,uslv_photo;

    private StaggeredGridView sgv_index;
    private RelativeLayout bannerView;

    private AutoScrollViewPager autoViewPager;

    private Context mContext;

    private boolean isPrepared;

    private List<RCMDModel>list_banners;
    private List<RCMDModel>list_user;
    private List<RCMDModel>list_photo;


    private CommomAdapter<RCMDModel>adapter_user;
    private CommomAdapter<RCMDModel>adapter_photo;

    private int currentPage=1;
    @Override
    public void bindListener() {

        sfl_index.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadData(currentPage);
            }
        });

        uslv_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(mContext, TopicActivity.class).putExtra("uCode",list_user.get(position).getUcode()));
            }
        });

        uslv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void initData() {

        isPrepared = true;
        if (!isPrepared || !isVisible) {
            return;
        }

    }

    @Override
    protected int onLayoutIdGenerated() {
        return R.layout.fragment_index;
    }

    @Override
    protected void onViewCreated(View parentView) {
        mContext=getActivity();
        sfl_index=findView(parentView, R.id.sfl_index);
        fl_index_content=findView(parentView,R.id.fl_index_bannercontent);
        sgv_index=findView(parentView,R.id.sgv_index);

        uslv_user=findView(parentView,R.id.uslv_index_user);
        uslv_photo=findView(parentView,R.id.uslv_index_photo);

        init();

        loadData(currentPage);
    }




    private void init(){
        list_banners=new ArrayList<>();
        list_user=new ArrayList<>();
        list_photo=new ArrayList<>();


        adapter_photo=new CommomAdapter<RCMDModel>(mContext,list_photo,R.layout.item_index_user) {
            @Override
            public void convert(CommomViewHolder mHolder, RCMDModel item, int position) {

                ImageView imageView= (ImageView) mHolder.getConvertView().findViewById(R.id.iv_item_indexuser_pic);
                RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) imageView.getLayoutParams();

                int width=CommonConst.SCREENWIDTH/2-20;
                params.width=width;

                Bitmap bm= com.nostra13.universalimageloader.core.ImageLoader.getInstance().loadImageSync(item.getImgUrl());
//                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(item.getImgUrl(), imageView);
                params.height=bm.getHeight();
                imageView.setImageBitmap(bm);
//                bm.recycle();
                mHolder.setVisiblility(R.id.tv_item_indexuser_name, View.GONE);

            }
        };

        adapter_user=new CommomAdapter<RCMDModel>(mContext,list_user,R.layout.item_index_user) {
            @Override
            public void convert(CommomViewHolder mHolder, RCMDModel item, int position) {
                ImageView imageView= (ImageView) mHolder.getConvertView().findViewById(R.id.iv_item_indexuser_pic);
                RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                int width=CommonConst.SCREENWIDTH/2-20;
                params.width=width;
                params.height=width;
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(item.getImgUrl(), imageView);
                mHolder.setText(R.id.tv_item_indexuser_name, item.getTitle());
            }
        };

        uslv_user.setAdapter(adapter_user);
        uslv_photo.setAdapter(adapter_photo);

    }
    private void loadData(int page)
    {

        loadBanner();
        loadUser(page);
        loadPhoto(page);
    }

    private void loadBanner()
    {

        RequestHelper.getRCMDBannerInfo(new RequestCallBack<Banner>() {
            @Override
            public void onSuccess(Banner banner) {
                list_banners = banner.getItem();
                initBanner(list_banners);
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }

    private void loadUser(final int page){

        RequestHelper.getRCMDUserInfo(String.valueOf(page), new RequestCallBack<Banner>() {
            @Override
            public void onSuccess(Banner banner) {

                if (list_user != null && list_user.size() > 0 && currentPage != 1) {
                    list_user.addAll(banner.getItem());
                } else {
                    list_user = banner.getItem();
                }
                adapter_user.setData(list_user);
            }

            @Override
            public void onFail(String msg) {

                Toast.makeText(mContext, msg, 0).show();
            }
        });
    }
    private void loadPhoto(final int page){

        RequestHelper.getRCMDPhotoInfo(String.valueOf(page), new RequestCallBack<Banner>() {
            @Override
            public void onSuccess(Banner banner) {

                if (list_photo != null && list_photo.size() > 0 && currentPage != 1) {
                    list_photo.addAll(banner.getItem());
                } else {
                    list_photo = banner.getItem();
                }
                adapter_photo.setData(list_photo);
                sfl_index.setRefreshing(false);
            }

            @Override
            public void onFail(String msg) {
                sfl_index.setRefreshing(false);
            }
        });

    }

    /**
     * init banner
     */
    private void initBanner(List<RCMDModel> list){

        if(bannerView!=null){
            bannerView.removeAllViews();
        }else {
            bannerView=new RelativeLayout(mContext);
        }


        double width = CommonConst.SCREENWIDTH;

        double height=CommonConst.SCREENHEIGHT/8*2;
        LinearLayout ll = new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(ViewUtil.dip2px(mContext,10), 0, ViewUtil.dip2px(mContext,10), 0);

        // 初始化点
        ArrayList<View> dots = new ArrayList<View>();
        ArrayList<String> pics = new ArrayList<String>();

        //TODO 测试，加载本地图片
        int[]respic=new int[list.size()];
        String[] titles = new String[list.size()];

        // 初始化标题
        TextView tv = new TextView(mContext);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        tv.setLines(1);
        tv.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp2.weight = 1;
        ll.addView(tv, lp2);

        lp2 = new LinearLayout.LayoutParams(ViewUtil.dip2px(mContext, 6), ViewUtil.dip2px(mContext,6));
        lp2.gravity = Gravity.CENTER_VERTICAL;
        lp2.leftMargin = ViewUtil.dip2px(mContext,4);
        for (int i = 0; i < list.size(); i++) {
            pics.add(list.get(i).getImgUrl());
//            respic[i]=R.drawable.ic_launcher;
            //TODO 将标题注释掉
//			titles[i] = list.get(i).getTitle();
            titles[i]="";
            View view = new View(mContext);
            view.setBackgroundResource(R.drawable.ic_focus_select);
            ll.addView(view, lp2);
            dots.add(view);
        }

        autoViewPager = new AutoScrollViewPager(mContext, dots, R.drawable.ic_focus_select, R.drawable.ic_focus, new AutoScrollViewPager.OnPagerClickCallback() {
            @Override
            public void onPagerClick(int position) {

                // item点击事件
            }
        });

        //TODO
        autoViewPager.setUriList(pics);
//        autoViewPager.setResImageIds(respic);
        autoViewPager.setTitle(tv, titles);

        autoViewPager.startRoll();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) height);
        bannerView.addView(autoViewPager, lp);

        // 添加标题和点
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams((int) width, ViewUtil.dip2px(mContext,30));
        lp3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        bannerView.addView(ll, lp3);
        bannerView.setGravity(Gravity.CENTER_HORIZONTAL);
        fl_index_content.removeAllViews();
        fl_index_content.addView(bannerView);


    }
}
