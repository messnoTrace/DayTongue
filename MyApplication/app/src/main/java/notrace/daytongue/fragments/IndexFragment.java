package notrace.daytongue.fragments;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.LazyFragment;
import notrace.daytongue.R;
import notrace.daytongue.commen.CommonConst;
import notrace.daytongue.entitys.TestEntity;
import notrace.daytongue.utils.ViewUtil;
import notrace.daytongue.views.AutoScrollViewPager;

public class IndexFragment extends LazyFragment {

    private SwipeRefreshLayout sfl_index;
    private RecyclerView rcv_index;
    private FrameLayout fl_index_content;



    private StaggeredGridView sgv_index;
    private RelativeLayout bannerView;

    private AutoScrollViewPager autoViewPager;

    private Context mContext;

    private boolean isPrepared;
    @Override
    public void bindListener() {

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
        sfl_index=findView(parentView,R.id.sfl_index);
        rcv_index=findView(parentView,R.id.rcv_index);
        fl_index_content=findView(parentView,R.id.fl_index_bannercontent);
        sgv_index=findView(parentView,R.id.sgv_index);

    }


    /**
     * init banner
     */
    private void initBanner(List<TestEntity> list){

        if(bannerView!=null){
            bannerView.removeAllViews();
        }else {
            bannerView=new RelativeLayout(mContext);
        }


        double width = CommonConst.SCREENWIDTH;

        double height=CommonConst.SCREENHEIGHT/8*3;
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
//            pics.add(list.get(i).getImgurl());
            respic[i]=R.drawable.ic_launcher;
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
//        autoViewPager.setUriList(pics);
        autoViewPager.setResImageIds(respic);
        autoViewPager.setTitle(tv, titles);

        autoViewPager.startRoll();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) height);
        bannerView.addView(autoViewPager, lp);

        // 添加标题和点
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams((int) width, ViewUtil.dip2px(mContext,30));
        lp3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        bannerView.addView(ll, lp3);
        bannerView.setGravity(Gravity.CENTER_HORIZONTAL);
        fl_index_content.addView(bannerView);


    }
}
