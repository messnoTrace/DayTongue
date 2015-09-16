package notrace.daytongue;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import notrace.daytongue.fragments.CategoryFragment;
import notrace.daytongue.fragments.DiscoveryFragment;
import notrace.daytongue.fragments.IndexFragment;
import notrace.daytongue.fragments.SpaceFragment;

public class MainActivity extends BaseActivity {

    private String TITLE[]={"首页","空间","分类","发现"};
    private int []ICON={R.drawable.selector_tab_index,R.drawable.selector_tab_space,R.drawable.selector_tab_category,R.drawable.selector_tab_discovery};
    private String []TAGS={"INDEX","SPACE","CATEGORY","DISCOVERY"};


    private FragmentManager manager=getSupportFragmentManager();
    private IndexFragment indexFragment;
    private SpaceFragment spaceFragment;
    private CategoryFragment categoryFragment;
    private DiscoveryFragment discoveryFragment;
    private TabHost tabhost;
    private TabWidget tabwidget;
    private String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        bindListener();
        initData();
    }

    @Override
    public void findViews() {

        tabhost= (TabHost) findViewById(android.R.id.tabhost);
        tabwidget = (TabWidget) findViewById(android.R.id.tabs);
        tabhost.setup();
        tabhost.setOnTabChangedListener(tabchangelistener);
        addtab(TITLE, ICON, TAGS);
        tabhost.setCurrentTab(0);
    }

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }


    // 添加标签
    private void addtab(String[] tabtit, int[] icon, String[] tags) {

        for (int i = 0; i < tabtit.length; i++) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater
                    .from(this).inflate(R.layout.tab_iconlayout, tabwidget,
                            false);
            ImageView icon_image = (ImageView) linearLayout
                    .findViewById(R.id.tab_icon_imageView);
            TextView icon_text = (TextView)linearLayout.findViewById(R.id.tab_text_textview);
            icon_text.setText(tabtit[i]);
            icon_image.setImageResource(icon[i]);
            TabHost.TabSpec spec = tabhost.newTabSpec(tags[i]);
            spec.setIndicator(linearLayout);
            spec.setContent(new DummyTabContent(getBaseContext()));
            tabhost.addTab(spec);
        }
    }


    public class DummyTabContent implements TabHost.TabContentFactory {
        private Context mContext;

        public DummyTabContent(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            return v;
        }

    }
    TabHost.OnTabChangeListener tabchangelistener = new TabHost.OnTabChangeListener() {

        @Override
        public void onTabChanged(String tabId) {
            attachFragment(tabId);
        }
    };

    private void attachFragment(String tagid){
        currentFragment=tagid;
        indexFragment= (IndexFragment) manager.findFragmentByTag(TAGS[0]);
        spaceFragment= (SpaceFragment) manager.findFragmentByTag(TAGS[1]);
        categoryFragment= (CategoryFragment) manager.findFragmentByTag(TAGS[2]);
        discoveryFragment= (DiscoveryFragment) manager.findFragmentByTag(TAGS[3]);


        FragmentTransaction ft=manager.beginTransaction();

        if(indexFragment!=null){
            ft.hide(indexFragment);
        }
        if(spaceFragment!=null){
            ft.hide(spaceFragment);
        }
        if(categoryFragment!=null){
            ft.hide(categoryFragment);
        }

        if(discoveryFragment!=null){
            ft.hide(discoveryFragment);
        }
        switchFragment(ft,currentFragment);
        ft.commit();

    }

    private void switchFragment(FragmentTransaction ft,String  tagid){
        if(tagid.equals(TAGS[0])){

            if(indexFragment==null){
                indexFragment=new IndexFragment();
                ft.add(R.id.realtabcontent,indexFragment,TAGS[0]);
            }else {
                ft.show(indexFragment);
            }
        }else if(tagid.equals(TAGS[1])){
            if(spaceFragment==null){
                spaceFragment=new SpaceFragment();
                ft.add(R.id.realtabcontent,spaceFragment,TAGS[1]);
            }else {
                ft.show(spaceFragment);
            }
        }
        else if(tagid.equals(TAGS[2])){
            if(categoryFragment==null){
                categoryFragment=new CategoryFragment();
                ft.add(R.id.realtabcontent,categoryFragment,TAGS[2]);
            }else {
                ft.show(categoryFragment);
            }
        }
        else if(tagid.equals(TAGS[3])){
            if(discoveryFragment==null){
                discoveryFragment=new DiscoveryFragment();
                ft.add(R.id.realtabcontent,discoveryFragment,TAGS[3]);
            }else {
                ft.show(discoveryFragment);
            }
        }

    }

}
