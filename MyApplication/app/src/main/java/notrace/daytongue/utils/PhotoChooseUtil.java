package notrace.daytongue.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

import notrace.daytongue.MyApplication;
import notrace.daytongue.R;
import notrace.daytongue.choosepicture.ChoosePicturePopWindow;
import notrace.daytongue.choosepicture.ImageCropActivity;
import notrace.daytongue.commen.PathConsts;
import notrace.daytongue.commen.RequestHelper;

/**
 * Created by notrace on 2015/9/17.
 */
public class PhotoChooseUtil implements View.OnClickListener{

    private File vFile;
    private String dir_temp;
    private String path;
    private Activity activityContext;
    private Fragment fragmentContext;

    public static int requestCode=-1;

    public static final int PHOTOHRAPH = 1;// 拍照
    public static final int PHOTOZOOM = 2; // 缩放
    public static final int PHOTORESOULT = 3;// 结果
    public static final int CROPIMAGES = 4;

    ChoosePicturePopWindow mPopWindow;


    public  PhotoChooseUtil(Activity activityContext){
        this.activityContext=activityContext;
        init();
    }

    public PhotoChooseUtil(Fragment fragmentContext){
        this.fragmentContext=fragmentContext;
        init();
    }
    private void init(){
        dir_temp= FileDownloadUtil.getDefaultLocalDir(PathConsts.DIR_TEMP);
    }
    /**
     * show choose popwindow examble ,head icon click then call this method
     * @param context
     * @param parent
     * @param gravity
     * @param x
     * @param y
     */
    public  void showPop(Context  context,View parent,int gravity,int x,int y){
        mPopWindow=new ChoosePicturePopWindow(context,this);
        mPopWindow.showAtLocation(parent,gravity,x,y);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_take_photo:
                requestCode=PHOTOHRAPH;
                //take photo
                mPopWindow.dismiss();

                vFile = new File(dir_temp + "user_icon_temp.jpg");
                Uri uri = Uri.fromFile(vFile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                if(activityContext!=null)
                {
                    activityContext.startActivityForResult(intent, PHOTOHRAPH);
                }
                if(fragmentContext!=null){
                    activityContext.startActivityForResult(intent, PHOTOHRAPH);
                }

                break;
            case R.id.btn_pick_photo:

                requestCode=PHOTORESOULT;
                mPopWindow.dismiss();
				selectPicFromLocal();// 调用系统相册
//                Intent i=new Intent();
//                if(activityContext!=null){
//                    i.setClass(activityContext,ImageListActivity.class);
//                    startActivity(i);
//                }
//                if(fragmentContext!=null){
//
//                }

                break;
        }
    }




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

        if(activityContext!=null){
            activityContext.startActivityForResult(intent, PHOTORESOULT);
        }
        if(fragmentContext!=null){
            fragmentContext.startActivityForResult(intent, PHOTORESOULT);
        }

    }



    /**
     * call in onActivityResult
     * @param resultCode
     * @param data
     */
    public void getResult( int resultCode, Intent data,ImageView imageView){


        if(requestCode==-1){
            return;
        }

        if(resultCode!=Activity.RESULT_OK){}
        if (requestCode == PHOTOHRAPH) {// 拍照后进行裁剪图片
            if (vFile != null && vFile.exists()) {
                startCrop(Uri.fromFile(vFile).getPath());
            }
        } else if (requestCode == CROPIMAGES) {// 获取裁剪后的图片
            if (data != null) {
                path = data.getStringExtra("path");


                if(imageView!=null){
                    ImageLoader.getInstance().displayImage("file://" + path, imageView);
                    final File file=new File(path);


                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {

                                String str=FileUtils.base64encode(file);
                                RequestHelper.upLoadImage(str, MyApplication.currentUser.getUcode(), "JPEG");
                            }catch (Exception e)
                            {

                            }

                        }
                    }).start();
                }

            }
        } else if (requestCode == PHOTORESOULT) {// 打开系统相册进行裁剪图片
            if (data == null) {// 处理返回，取消键被点击报空指针异常
                return;
            }

            Uri startCrop = data.getData();
            if (startCrop != null) {
                if(activityContext!=null){
                    startCrop(getPath(activityContext, startCrop));
                }
                if(fragmentContext!=null){
                    startCrop(getPath(fragmentContext.getActivity(), startCrop));
                }

            }
        }
    }


    /**
     * 裁剪图片
     */
    public void startCrop(String path) {
        requestCode=CROPIMAGES;
        Intent intent = new Intent();
        intent.putExtra("path", path);
        intent.putExtra("flag", false);
        if(activityContext!=null){
            intent.setClass(activityContext, ImageCropActivity.class);
            activityContext.startActivityForResult(intent, CROPIMAGES);
        }

        if(fragmentContext!=null){
            intent.setClass(fragmentContext.getActivity(), ImageCropActivity.class);
            fragmentContext.startActivityForResult(intent, CROPIMAGES);
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
