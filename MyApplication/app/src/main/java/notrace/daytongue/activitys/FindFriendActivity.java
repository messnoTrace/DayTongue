package notrace.daytongue.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.adapters.CommomAdapter;
import notrace.daytongue.adapters.CommomViewHolder;
import notrace.daytongue.commen.RequestHelper;
import notrace.daytongue.entitys.Users;
import notrace.daytongue.entitys.response.UserList;
import notrace.daytongue.http.RequestCallBack;

public class FindFriendActivity extends BaseActivity {

    private TextView tv_next;
    private EditText et_key;
    private ListView lv_list;
    private String key;

    private CommomAdapter<Users>adapter;
    private int currentIndex=1;


    private List<Users>list_users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);
        findViews();
        bindListener();
        initData();

    }

    @Override
    public void findViews() {
        setNavigation("找人");
        tv_next= (TextView) findViewById(R.id.tv_findfriend_next);
        et_key= (EditText) findViewById(R.id.et_findfriend_key);
        lv_list= (ListView) findViewById(R.id.lv_findfriend_list);

    }

    @Override
    public void bindListener() {

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(FindFriendActivity.this,OtherPersonCenterActivity.class).putExtra("ucode",list_users.get(position).getUCode()));
            }
        });


        et_key.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    key=et_key.getText().toString();
                    RequestHelper.SerchGetAllUsers(key, String.valueOf(currentIndex), MyApplication.currentUser.getUcode(), new RequestCallBack<UserList>() {
                        @Override
                        public void onSuccess(UserList userList) {

                            list_users=userList.getItem();
                            adapter.setData(list_users);
                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {

        list_users=new ArrayList<>();
        adapter=new CommomAdapter<Users>(FindFriendActivity.this,list_users,R.layout.item_friendlist) {
            @Override
            public void convert(CommomViewHolder mHolder, Users item, int position) {
                mHolder.setImageUri(R.id.civ_item_friend_head,list_users.get(position).getUserHead());
                mHolder.setText(R.id.tv_item_friend_name,list_users.get(position).getNickName());
                if(list_users.get(position).isLock()){
                    mHolder.setText(R.id.tv_item_friend_focus,"已关注");
                }else
                {
                    mHolder.setText(R.id.tv_item_friend_focus,"关注");
                }

            }
        };
        lv_list.setAdapter(adapter);
        loadData();
    }

    private void loadData(){

        RequestHelper.getUserList(MyApplication.currentUser.getUcode(), new RequestCallBack<UserList>() {
            @Override
            public void onSuccess(UserList userList) {

                list_users=userList.getItem();
                adapter.setData(list_users);

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
