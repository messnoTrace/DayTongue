package notrace.daytongue.multichooseimages;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.R;

public class MyAdapter extends ImageCommonAdapter<String>
{



	private int count=0;

	public int getSelectedCount(){
		return   count;
	}
	/**
	 * 用户选择的图片，存储为图片的完整路径
	 */
	private  List<String> mSelectedImage = new ArrayList<String>();

	public List<String> getmSelectedImage() {
		return mSelectedImage;
	}

	/**
	 * 文件夹路径
	 */
	private String mDirPath;

	public MyAdapter(Context context, List<String> mDatas, int itemLayoutId,
			String dirPath)
	{
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
	}


	public void setData(List<String>datas,String dirPath)
	{

		this.mDatas=datas;
		this.mDirPath=dirPath;
		init();
		notifyDataSetChanged();

	}

	@Override
	public void convert(final ViewHolder helper, final String item) {
		//设置no_pic
		helper.setImageResource(R.id.id_item_image, R.drawable.pictures_no);
		//设置no_selected
		helper.setImageResource(R.id.id_item_select,
				R.drawable.picture_unselected);
		//设置图片
		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);

		mImageView.setColorFilter(null);
		//设置ImageView的点击事件
		mImageView.setOnClickListener(new OnClickListener()
		{
			//选择，则将图片变暗，反之则反之
			@Override
			public void onClick(View v)
			{

				// 已经选择过该图片
				if (mSelectedImage.contains(mDirPath + "/" + item))
				{
					mSelectedImage.remove(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.picture_unselected);
					mImageView.setColorFilter(null);
					getSelectedMap().put(helper.getPosition(), false);
					count--;
				} else
				// 未选择该图片
				{
					if(count<9){
						count++;
					}
					else
					{
						Toast.makeText(mContext, "最多只能选9张", Toast.LENGTH_SHORT).show();
						return;
					}
					mSelectedImage.add(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.pictures_selected);
					mImageView.setColorFilter(Color.parseColor("#77000000"));
					getSelectedMap().put(helper.getPosition(),true);


				}
				if(imageSelectListener!=null){
					imageSelectListener.ImageSelect(count);
				}


			}
		});

		/**
		 * 已经选择过的图片，显示出选择过的效果
		 */
		if (mSelectedImage.contains(mDirPath + "/" + item))
		{
			mSelect.setImageResource(R.drawable.pictures_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}
	}

	public interface  OnImageSelectListener{
		public  void ImageSelect(int count);
	}
	private OnImageSelectListener imageSelectListener;
	public void setOnImageSelctdListener(OnImageSelectListener mListener){
		this.imageSelectListener =mListener;
	}
	public  void clearList(){
		mSelectedImage=new ArrayList<>();
		count=0;
	}
}
