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
import notrace.daytongue.activitys.SearchActivity;
import notrace.daytongue.adapters.CommomAdapter;
import notrace.daytongue.adapters.CommomViewHolder;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.Category;


public class CategoryFragment extends LazyFragment {

    private List<Category>list;
    private CommomAdapter<Category>adater;
    private GridView gv_category;

    private Context mContext;
    @Override
    public void bindListener() {


        gv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(mContext, SearchActivity.class).putExtra("key",list.get(position).getName()));
            }
        });
    }

    @Override
    public void initData() {

        list=new ArrayList<>();
        Category c1=new Category(1,"家畜");
        Category c2=new Category(2,"家禽");
        Category c3=new Category(3,"水产");
        Category c4=new Category(4,"茶");
        Category c5=new Category(5,"干果");
        Category c6=new Category(6,"花卉");
        Category c7=new Category(7,"苗木");
        Category c8=new Category(8,"食用菌");
        Category c9=new Category(9,"蔬菜");
        Category c10=new Category(10,"糖类");
        Category c11=new Category(11,"纤维");
        Category c12=new Category(12,"鲜果");
        Category c13=new Category(13,"油料");
        Category c14=new Category(14,"豆");
        Category c15=new Category(15,"禾谷");
        Category c16=new Category(16,"薯芋");
        Category c17=new Category(17,"特养");
        Category c18=new Category(18,"中药材");
        Category c19=new Category(19,"其他");
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        list.add(c6);
        list.add(c7);
        list.add(c8);
        list.add(c9);
        list.add(c10);
        list.add(c11);
        list.add(c12);
        list.add(c13);
        list.add(c14);
        list.add(c15);
        list.add(c16);
        list.add(c17);list.add(c18);list.add(c19);

        adater=new CommomAdapter<Category>(mContext,list,R.layout.item_category) {
            @Override
            public void convert(CommomViewHolder mHolder, Category item, int position) {

                mHolder.setText(R.id.tv_item_category_id,item.getId()+"");
                mHolder.setText(R.id.tv_item_category_name,item.getName());
            }
        };

        gv_category.setAdapter(adater);

        loadData();
    }

    @Override
    protected int onLayoutIdGenerated() {
        return R.layout.fragment_category;
    }

    @Override
    protected void onViewCreated(View parentView) {
        mContext=getActivity();

        gv_category=findView(parentView,R.id.gv_category);

        initData();
//        loadData();
    }

    private  void loadData()
    {

        RequestHelper.getOccupations();
    }
}
