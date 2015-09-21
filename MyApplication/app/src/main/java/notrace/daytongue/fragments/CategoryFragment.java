package notrace.daytongue.fragments;

import android.view.View;

import notrace.daytongue.LazyFragment;
import notrace.daytongue.R;
import notrace.daytongue.commen.RequestHelper;


public class CategoryFragment extends LazyFragment {

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected int onLayoutIdGenerated() {
        return R.layout.fragment_category;
    }

    @Override
    protected void onViewCreated(View parentView) {


        loadData();
    }

    private  void loadData()
    {

        RequestHelper.getOccupations();
    }
}
