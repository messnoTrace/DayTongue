package notrace.daytongue.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import notrace.daytongue.R;

/**
 * Created by notrace on 2015/9/24.
 */
public class Adapter_choosepic extends BaseAdapter{

    private  List<String>list;
    private Context mContext;


    public Adapter_choosepic(Context mContext,List<String>list){
        this.list=list;
        this.mContext=mContext;
    }

    public void setData(List<String>list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder mHolder;
        if(convertView==null){
            mHolder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_choosepic_gridview,null);
            mHolder.iv_pic= (ImageView) convertView.findViewById(R.id.iv_item_choosepic_img);
            mHolder.ib_delete= (ImageButton) convertView.findViewById(R.id.ib_item_choosepic_delete);

            convertView.setTag(mHolder);
        }
        else
        {
            mHolder= (ViewHolder) convertView.getTag();
        }


        mHolder.ib_delete.setVisibility(View.VISIBLE);
        mHolder.iv_pic.setImageResource(R.drawable.ic_error);
        mHolder.iv_pic.setColorFilter(Color.parseColor("#00ffffff"));
        mHolder.ib_delete.setOnClickListener(null);
        mHolder.iv_pic.setOnClickListener(null);

        if(position==list.size()){
            mHolder.ib_delete.setVisibility(View.INVISIBLE);
            mHolder.iv_pic.setImageResource(R.drawable.rc_ic_setting_friends_add);
            mHolder.iv_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnChooseListener != null) {
                        mOnChooseListener.onChoose();
                    }
                }
            });

            return  convertView;
        }
        if(list.size()!=0){
            ImageLoader.getInstance().displayImage("file://"+list.get(position), mHolder.iv_pic);
            mHolder.ib_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });

            mHolder.iv_pic.setColorFilter(Color.parseColor("#77000000"));
        }


        return convertView;
    }

    public class ViewHolder{
        private ImageView iv_pic;
        private ImageButton ib_delete;
    }

    public interface OnChooseListener{
        public void onChoose();
    }

    public OnChooseListener mOnChooseListener;

    public void setOnChooseListener(OnChooseListener mOnChooseListener) {
        this.mOnChooseListener = mOnChooseListener;
    }
}
