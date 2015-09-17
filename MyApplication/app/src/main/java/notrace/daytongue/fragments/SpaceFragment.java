package notrace.daytongue.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import notrace.daytongue.LazyFragment;
import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.choosepicture.ChoosePictureDemoActivity;
import notrace.daytongue.commen.RequstHelper;


public class SpaceFragment extends LazyFragment implements View.OnClickListener{


    private ImageView iv_space_image;
    private Context mContext;
    @Override
    public void bindListener() {

        iv_space_image.setOnClickListener(this);
    }

    @Override
    public void initData() {

        loadData();
    }

    @Override
    protected int onLayoutIdGenerated() {
        return R.layout.fragment_space;
    }

    @Override
    protected void onViewCreated(View parentView) {
        mContext=getActivity();
        iv_space_image=findView(parentView,R.id.iv_space_image);
        loadData();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.iv_space_image:


                startActivity(new Intent(mContext, ChoosePictureDemoActivity.class));

                break;
        }
    }

    private void loadData(){
        RequstHelper.getTopic("10","","0", MyApplication.currentUser.getUcode(),"");
    }
}
