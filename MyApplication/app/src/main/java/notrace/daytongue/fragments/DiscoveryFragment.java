package notrace.daytongue.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.LazyFragment;
import notrace.daytongue.R;
import notrace.daytongue.activitys.FindFriendActivity;
import notrace.daytongue.activitys.LookAroundActivity;
import notrace.daytongue.adapters.CommomAdapter;
import notrace.daytongue.adapters.CommomViewHolder;
import notrace.daytongue.entitys.GridEntity;

public class DiscoveryFragment extends LazyFragment {

    private GridView gv_discovery;

    private List<GridEntity>list;

    private CommomAdapter<GridEntity>adapter;

    private Context mContext;
    @Override
    public void bindListener() {
        gv_discovery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        break;
                    case 1:

                        //lookaround

                        startActivity(new Intent(mContext, LookAroundActivity.class));

                        break;

                    case 2:
                        startActivity(new Intent(mContext, FindFriendActivity.class));
                        break;
                    case 3:
                        break;
                }
            }
        });

    }

    @Override
    public void initData() {

    }
    @Override
    protected int onLayoutIdGenerated() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected void onViewCreated(View parentView) {
        mContext=getActivity();
        gv_discovery=findView(parentView,R.id.gv_discovery);
        loadData();

    }

    private void loadData(){

        list=new ArrayList<>();
        GridEntity e1=new GridEntity(R.drawable.ic_launcher,"图片墙");
        GridEntity e2=new GridEntity(R.drawable.ic_launcher,"随便看看");
        GridEntity e3=new GridEntity(R.drawable.ic_launcher,"找人");
        GridEntity e4=new GridEntity(R.drawable.saoyisao,"扫一扫");
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);

        adapter=new CommomAdapter<GridEntity>(mContext,list,R.layout.item_discovery) {
            @Override
            public void convert(CommomViewHolder mHolder, GridEntity item, int position) {

                mHolder.setImageResourceId(R.id.iv_item_discovery_icon,list.get(position).getIcon());
                mHolder.setText(R.id.tv_item_discovery_name,list.get(position).getName());
            }
        };
        gv_discovery.setAdapter(adapter);
    }
}
