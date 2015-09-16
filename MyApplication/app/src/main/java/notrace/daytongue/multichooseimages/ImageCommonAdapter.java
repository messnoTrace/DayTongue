package notrace.daytongue.multichooseimages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class ImageCommonAdapter<T> extends BaseAdapter


{

	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	private Map<Integer,Boolean> isSelected;
	private List<ViewHolder> list_holders;



	public ImageCommonAdapter(Context context, List<T> mDatas, int itemLayoutId)
	{
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
		init();
	}





	public void init(){
		isSelected=new HashMap<Integer, Boolean>();
		list_holders=new ArrayList<ViewHolder>();
		for(int i=0;i<mDatas.size();i++){
			isSelected.put(i,false);
		}
	}

	@Override
	public int getCount()
	{
		return mDatas.size();
	}


	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder viewHolder = getViewHolder(position, convertView,
				parent);
		list_holders.add(viewHolder);
		convert(viewHolder, getItem(position));
		return viewHolder.getConvertView();

	}

	public abstract void convert(ViewHolder helper, T item);

	private ViewHolder getViewHolder(int position, View convertView,
			ViewGroup parent)
	{
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
				position);
	}


	public Map<Integer,Boolean> getSelectedMap()
	{
		return isSelected;
	}

	public List<ViewHolder> getHolderList(){
		return  list_holders;
	}

}
