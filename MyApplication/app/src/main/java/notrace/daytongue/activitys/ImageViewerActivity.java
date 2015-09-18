package notrace.daytongue.activitys;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;
import notrace.daytongue.entitys.Contents;
import notrace.daytongue.entitys.Photo;

public class ImageViewerActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_count;
    private ViewPager vp_imageviewer;


    private Contents contents;
    private  List<Photo>list;
    ViewPagerAdapter adapter;
    private int currentPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        iv_back= (ImageView) findViewById(R.id.iv_imageview_back);
        tv_count= (TextView) findViewById(R.id.tv_imageviewer_count);
        vp_imageviewer= (ViewPager) findViewById(R.id.vp_imageviewer);
    }

    @Override
    public void bindListener() {

        iv_back.setOnClickListener(this);

        vp_imageviewer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tv_count.setText(position+1 + "/" + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {

        list=new ArrayList<>();
        currentPosition=getIntent().getIntExtra("position",0);

        contents= (Contents) getIntent().getSerializableExtra("contents");
        if(contents!=null){
            list=contents.getItem();
        }

        adapter=new ViewPagerAdapter();
        vp_imageviewer.setAdapter(adapter);
        vp_imageviewer.setCurrentItem(currentPosition);
        tv_count.setText(currentPosition+1 + "/" + list.size());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.iv_imageview_back:
                finish();
                break;
        }
    }


    class ViewPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {

            return list.size();
        }

        @Override
        public Object instantiateItem(View container, final int position) {
            // View view = View.inflate(context, R.layout.viewpager_item, null);

            ImageView view = new ImageView(ImageViewerActivity.this);
            ((ViewPager) container).addView(view);
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            view.setOnTouchListener(myOnTouchListener);
                ImageLoader.getInstance().displayImage(list.get(position).getImgName(), view);
            return view;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // 将ImageView从ViewPager移除
            ((ViewPager) arg0).removeView((View) arg2);
        }
    }

}
