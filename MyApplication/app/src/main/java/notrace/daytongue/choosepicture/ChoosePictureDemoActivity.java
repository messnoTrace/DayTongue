package notrace.daytongue.choosepicture;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import notrace.daytongue.BaseActivity;
import notrace.daytongue.R;
import notrace.daytongue.activitys.SendSettingActivity;
import notrace.daytongue.adapters.Adapter_choosepic;
import notrace.daytongue.commen.PathConsts;
import notrace.daytongue.multichooseimages.ImageListActivity;
import notrace.daytongue.utils.FileDownloadUtil;
import notrace.daytongue.views.CircleImageView;
import notrace.daytongue.views.UnScrollableGridView;


public class  ChoosePictureDemoActivity extends BaseActivity {
	
	private CircleImageView civ;
	private InputMethodManager imm;
	private File vFile;
	private String dir_temp;
	private ChoosePicturePopWindow menuWindow;
	private TextView tv_cancel,tv_next;

	private EditText et_des;
	private UnScrollableGridView gv_pics;

	private List<String>list_choosed;
	private Adapter_choosepic adapter_choosed;

	
	
	private String path;
    private String picturePath;
    private String top_bg;
	public static final int PHOTOHRAPH = 1;// 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果
	public static final int CROPIMAGES = 4;


	public static final  int RESULT_CHOOSEPIC=1001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choosepic);
		
		findViews();
		bindListener();
		initData();

	}



	@Override
	public void bindListener() {
		
		civ.setOnClickListener(clickListener);
		tv_cancel.setOnClickListener(this);
		tv_next.setOnClickListener(this);
	}



	@Override
	public void findViews() {
		civ=(CircleImageView) findViewById(R.id.civ);
		tv_cancel= (TextView) findViewById(R.id.tv_choosepic_cancel);
		tv_next= (TextView) findViewById(R.id.tv_choosepic_next);
		et_des= (EditText) findViewById(R.id.et_choosepic_des);
		gv_pics= (UnScrollableGridView) findViewById(R.id.gv_choosepic_pics);
		
	}

	@Override
	public void initData() {
		dir_temp= FileDownloadUtil.getDefaultLocalDir(PathConsts.DIR_TEMP);


		list_choosed=new ArrayList<>();
		adapter_choosed=new Adapter_choosepic(ChoosePictureDemoActivity.this,list_choosed);
		gv_pics.setAdapter(adapter_choosed);

		adapter_choosed.setOnChooseListener(new Adapter_choosepic.OnChooseListener() {
			@Override
			public void onChoose() {

				menuWindow = new ChoosePicturePopWindow(ChoosePictureDemoActivity.this, clickListener);
				menuWindow.showAtLocation(ChoosePictureDemoActivity.this.findViewById(R.id.main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

			}
		});
	}
	
	
	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.civ:// 更换头像

				break;
			case R.id.btn_take_photo:// 拍照
				menuWindow.dismiss();
				vFile = new File(dir_temp + "user_icon_temp.jpg");

				Uri uri = Uri.fromFile(vFile);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivityForResult(intent, PHOTOHRAPH);
				break;
			case R.id.btn_pick_photo:// 系统相册
				menuWindow.dismiss();
//				selectPicFromLocal();// 调用系统相册
				Intent i=new Intent();
				i.putExtra("count",list_choosed.size());
				i.setClass(ChoosePictureDemoActivity.this, ImageListActivity.class);
//				startActivity(i);

				startActivityForResult(i,RESULT_CHOOSEPIC);

				break;


			}
		}
	};
	
	
	
	/**
	 * 从图库获取图片
	 */
	public void selectPicFromLocal() {
		Intent intent;
		if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");

		} else {
//			intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

			intent =  new  Intent(Intent. ACTION_OPEN_DOCUMENT );
			intent.setType("image/*");
		}
		this.startActivityForResult(intent, PHOTORESOULT);
	}

	/**
	 * 裁剪图片
	 */
	public void startCrop(String path) {
		Intent intent = new Intent();
		intent.putExtra("path", path);
		intent.putExtra("flag", false);
		intent.setClass(this, ImageCropActivity.class);
		startActivityForResult(intent, CROPIMAGES);
	}
	
	

	/**
	 * 获取回传数据进行相应的操作
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		utils.pickResult(requestCode,RESULT_OK,data);
		if (requestCode == PHOTOHRAPH) {// 拍照后进行裁剪图片
			if (vFile != null && vFile.exists()) {
				startCrop(Uri.fromFile(vFile).getPath());
			}
		} else if (requestCode == CROPIMAGES) {// 获取裁剪后的图片
			if (data != null) {
				path = data.getStringExtra("path");
				new ImageUploadTask().execute(path);
				ImageLoader.getInstance().displayImage("file://" + path, civ);
				list_choosed.add(path);
				adapter_choosed.setData(list_choosed);
			}
		} else if (requestCode == PHOTORESOULT) {// 打开系统相册进行裁剪图片
			if (data == null) {// 处理返回，取消键被点击报空指针异常
				return;
			}



			Uri startCrop = data.getData();
			if (startCrop != null) {
				startCrop(getPath(ChoosePictureDemoActivity.this, startCrop));
//				findPicByUri(startCrop);
			}
		}


		if(requestCode==RESULT_CHOOSEPIC){

			if(data!=null){
				ArrayList<String>list=data.getStringArrayListExtra("piclist");
				list_choosed.addAll(list);
				adapter_choosed.setData(list_choosed);
			}
		}
	}
	
	/**
	 * 根据图库图片uri获取图片
	 */
	private void findPicByUri(Uri selectedImage) {


		Cursor cursor = null;
		if (selectedImage != null) {
//			cursor = getContentResolver().query(selectedImage, null, null, null, null);
			cursor=this.managedQuery(selectedImage,new String[]{MediaStore.Images.Media.DATA},null,
					null, null);
		}
		if (cursor != null) {// 判断图片是否存在

			// 按我个人理解 这个是获得用户选择的图片的索引值
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();

//			int columnIndex = cursor.getColumnIndex("_data");
//			picturePath = cursor.getString(cursor.getColumnIndex("id"));
			path=cursor.getString(column_index);
			cursor.close();
			cursor = null;
			if (picturePath == null || picturePath.equals("null")) {
				Toast toast = Toast.makeText(this, "找不到图片", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
		} else {// 判断图片是否存在
			File file = new File(selectedImage.getPath());
			if (!file.exists()) {
				Toast toast = Toast.makeText(this, "找不到图片", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
		}
		startCrop(picturePath);// 进行图片的裁剪
	}

	@Override
	public void onClick(View v) {

		switch (v.getId())
		{
			case R.id.tv_choosepic_cancel:
				finish();
				break;
			case R.id.tv_choosepic_next:

				if(list_choosed.size()==0){
					Toast.makeText(ChoosePictureDemoActivity.this,"请先选择图片",Toast.LENGTH_SHORT).show();
					return;
				}
				startActivity(new Intent(ChoosePictureDemoActivity.this, SendSettingActivity.class));
				break;
		}
	}


	/**
	 * 检查加载背景图片
	 */
	class ImageUploadTask extends AsyncTask<String, Void, String> {
		private static final String TEST_API_KEY = "yuIOo0F9DDf8ZbkZa1syRG/zdes="; // 测试使用的表单api验证密钥
		private static final String BUCKET = "notrace"; // 存储空间
		private final long EXPIRATION = System.currentTimeMillis() / 1000 + 1000 * 5 * 10; // 过期时间，必须大于当前时间
		String SAVE_KEY = File.separator + "notrace" + File.separator + System.currentTimeMillis() + ".jpg";

		@Override
		protected String doInBackground(String... arg0) {
			String string = null;
//			try {
//				String policy = UpYunUtils.makePolicy(SAVE_KEY, EXPIRATION, BUCKET);
//				String signature = UpYunUtils.signature(policy + "&" + TEST_API_KEY);
//				string = Uploader.upload(policy, signature, BUCKET, arg0[0]);
//			} catch (UpYunException e) {
//				e.printStackTrace();
//			}
			return string;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				top_bg = "http://haomee.b0.upaiyun.com" + SAVE_KEY;
//				user.setUser_head_pic(top_bg);
//				ImageLoaderCharles.getInstance(PersonSettingActivity.this).addTask(top_bg, user_head_pic);
				ImageLoader.getInstance().displayImage(top_bg, civ);
			} else {
				top_bg = null;
//				Toast.makeText(activity_context, "当前网络不可用,无法进行此操作!!", Toast.LENGTH_LONG).show();
			}
		}

	}




	@TargetApi(Build.VERSION_CODES.KITKAT)
	    public static String getPath(final Context context, final Uri uri) {
	    final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
	    // DocumentProvider
	    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
	            // ExternalStorageProvider
	            if (isExternalStorageDocument(uri)) {
	             final String docId = DocumentsContract.getDocumentId(uri);
	             final String[] split = docId.split(":");
	             final String type = split[0];
	             if ("primary".equalsIgnoreCase(type)) {
	                     return Environment.getExternalStorageDirectory() + "/"
	                             + split[1];
	                 }
	             // TODO handle non-primary volumes
	         }
	     // DownloadsProvider
	     else if (isDownloadsDocument(uri)) {
	             final String id = DocumentsContract.getDocumentId(uri);
	             final Uri contentUri = ContentUris.withAppendedId(
		Uri.parse("content://downloads/public_downloads"),
		Long.valueOf(id));
	             return getDataColumn(context, contentUri, null, null);
	         }
	     // MediaProvider
	     else if (isMediaDocument(uri)) {
	             final String docId = DocumentsContract.getDocumentId(uri);
	             final String[] split = docId.split(":");
	             final String type = split[0];
	             Uri contentUri = null;
	             if ("image".equals(type)) {
	                     contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	                 } else if ("video".equals(type)) {
	                     contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
	                 } else if ("audio".equals(type)) {
	                     contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	                 }
	             final String selection = MediaStore.MediaColumns._ID + "=?";
	             final String[] selectionArgs = new String[] { split[1] };
	             return getDataColumn(context, contentUri, selection,
				                        selectionArgs);
				            }
			      }
		     // MediaStore (and general)
		     else if ("content".equalsIgnoreCase(uri.getScheme())) {
		             // Return the remote address
		             if (isGooglePhotosUri(uri))
			                return uri.getLastPathSegment();
		             return getDataColumn(context, uri, null, null);
		         }
		     // File
		     else if ("file".equalsIgnoreCase(uri.getScheme())) {
		             return uri.getPath();
		         }

		     return null;
		 }









	/**
	      * Get the value of the data column for this Uri . This is useful for
	      * MediaStore Uris , and other file - based ContentProviders.
	      *
	      * @param context
	      *            The context.
	      * @param uri
	      *            The Uri to query.
	      * @param selection
	      *            (Optional) Filter used in the query.
	      * @param selectionArgs
	      *            (Optional) Selection arguments used in the query.
	      * @return The value of the _data column, which is typically a file path.
	      */
	     public static String getDataColumn(Context context, Uri uri,
		           String selection, String[] selectionArgs) {
	         Cursor cursor = null;
	         final String column = MediaStore.MediaColumns.DATA;
	         final String[] projection = { column };
	      try {
	             cursor = context.getContentResolver().query(uri, projection,
						 selection, selectionArgs, null);
	        if (cursor != null && cursor.moveToFirst()) {
	                 final int index = cursor.getColumnIndexOrThrow(column);
	                 return cursor.getString(index);
	             }
	    } finally {
	        if (cursor != null)
	                 cursor.close();
	       }
	    return null;
	}

		    /**
	     * @param uri
	     *            The Uri to check.
	     * @return Whether the Uri authority is ExternalStorageProvider.
	     */
	     public static boolean isExternalStorageDocument(Uri uri) {
	     return "com.android.externalstorage.documents".equals(uri
		               .getAuthority());
	 }

	     /**
	      * @param uri
	      *            The Uri to check.
	      * @return Whether the Uri authority is DownloadsProvider.
	      */
	     public static boolean isDownloadsDocument(Uri uri) {
	     return "com.android.providers.downloads.documents".equals(uri
		                 .getAuthority());
	 }

	     /**
	      * @param uri
	      *            The Uri to check.
	      * @return Whether the Uri authority is MediaProvider.
	      */
		   public static boolean isMediaDocument(Uri uri) {
	         return "com.android.providers.media.documents".equals(uri
		               .getAuthority());
	     }

			    /**
	     * @param uri
	     *            The Uri to check.
	     * @return Whether the Uri authority is Google Photos.
	     */
		    public static boolean isGooglePhotosUri(Uri uri) {
		        return "com.google.android.apps.photos.content".equals(uri
		               .getAuthority());
		    }



}
