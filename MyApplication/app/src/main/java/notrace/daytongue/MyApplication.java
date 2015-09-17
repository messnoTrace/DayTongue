package notrace.daytongue;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

import notrace.daytongue.commen.PathConsts;
import notrace.daytongue.entitys.response.LoginResult;
import notrace.daytongue.http.VolleyRequest;

/**
 * Created by notrace on 2015/7/20.
 */
public class MyApplication extends Application {


    public static LoginResult currentUser;
	
	private Context applicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=getApplicationContext();
        initImageLoader();
        VolleyRequest.getInstance().init(this);
    }





    /** get cache menu **/
    private String getDirectory() {
        String dir = getSDPath() + PathConsts.ROOT_PATH;
        return dir;
    }

    /** get sdcard menu**/
    private String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // �ж�sd���Ƿ����
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory(); // ��ȡ��Ŀ¼
        }
        if (sdDir != null) {
            return sdDir.toString();
        } else {
            return "";
        }
    }



    private void initImageLoader() {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
    	
    	
        String cacheDir = getDirectory();
        File dirFile = new File(cacheDir);
        if (!dirFile.exists())
            dirFile.mkdirs();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_launcher) // resource or drawable
                .showImageOnFail(R.mipmap.ic_launcher) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                        //       .preProcessor(...)
//        .postProcessor(...)
//        .extraForDownloader(...)
                .considerExifParams(true) // default
                .imageScaleType(ImageScaleType.EXACTLY) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
//        .decodingOptions(...)
                .displayer(new SimpleBitmapDisplayer()) // default
//        .handler(new Handler()) // default
                .build();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(applicationContext)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 320, null)
                .defaultDisplayImageOptions(options)
//        .taskExecutor(...)
                        //       .taskExecutorForCachedImages(...)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(8 * 1024 * 1024))
                .memoryCacheSize(16 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiscCache(dirFile)) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(500)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(applicationContext)) // default
                .imageDecoder(new BaseImageDecoder(false)) // default
                .build();
        ImageLoader.getInstance().init(config);
    }

}
