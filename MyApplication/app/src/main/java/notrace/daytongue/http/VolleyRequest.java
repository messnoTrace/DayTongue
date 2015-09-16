package notrace.daytongue.http;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.cache.BitmapImageCache;
import com.android.volley.cache.SimpleImageLoader;
import com.android.volley.request.GsonRequest;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.android.volley.ui.NetworkImageView;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;


public class VolleyRequest {

	private static VolleyRequest instance = null;
	private RequestQueue volleyRequestQueue;
	private SimpleImageLoader mImageLoader;

	public RequestQueue getQue(){
		return  volleyRequestQueue;
	}

	private VolleyRequest() {

	}

	public static VolleyRequest getInstance() {
		if (null == instance) {
			synchronized (VolleyRequest.class) {
				if (null == instance) {
					instance = new VolleyRequest();
				}
			}
		}
		return instance;
	}

	public void init(Context con) {
		volleyRequestQueue = Volley.newRequestQueue(con);
		mImageLoader = new SimpleImageLoader(volleyRequestQueue, BitmapImageCache.getInstance(null));
	}

	/**
	 * ��������һ��StringRequest����(GET)
	 * 
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public StringRequest newStringRequest(String url,
			Listener<String> listener, ErrorListener errorListener) {
		StringRequest request = new StringRequest(url, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * ��������һ��StringRequest����
	 * 
	 * @param method
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public StringRequest newStringRequest(int method, String url,
			Listener<String> listener, ErrorListener errorListener) {
		StringRequest request = new StringRequest(method, url, listener,
				errorListener);
		add(request);
		return request;
	}

	/**
	 * ��������һ��JsonObjectRequest����(GET)
	 * 
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public JsonObjectRequest newJsonObjectRequest(String url,
			JSONObject jsonRequest, Listener<JSONObject> listener,
			ErrorListener errorListener) {
		JsonObjectRequest request = new JsonObjectRequest(url, jsonRequest,
				listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * ��������һ��JsonObjectRequest����
	 * 
	 * @param method
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public JsonObjectRequest newJsonObjectRequest(int method, String url,
			JSONObject jsonRequest, Listener<JSONObject> listener,
			ErrorListener errorListener) {
		JsonObjectRequest request = new JsonObjectRequest(method, url,
				jsonRequest, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * ��������һ��JsonArrayRequest����(GET)
	 * 
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public JsonArrayRequest newJsonArrayRequest(String url,
			Listener<JSONArray> listener, ErrorListener errorListener) {
		JsonArrayRequest request = new JsonArrayRequest(url, listener,
				errorListener);
		add(request);
		return request;
	}


	/**
	 * ͨ��ImageLoader��ȡͼƬ(�Զ����?��)
	 * 
	 * @param imageView
	 * @param imgViewUrl
	 * @param defaultImageResId
	 * @param errorImageResId
	 */
	public void newImageViewLoaderRequest(ImageView imageView,
			String imgViewUrl, int defaultImageResId, int errorImageResId) {
		ImageListener listener = ImageLoader.getImageListener(imageView,
				defaultImageResId, errorImageResId);
		mImageLoader.get(imgViewUrl, listener);
	}

	/**
	 * networkImageView����
	 * 
	 * @param networkImageView
	 * @param imageUrl
	 * @param defaultImageResId
	 * @param errorImageResId
	 */
	public void newNetworkImageViewsRequest(NetworkImageView networkImageView,
			String imageUrl, int defaultImageResId, int errorImageResId) {
		networkImageView.setDefaultImageResId(defaultImageResId);
		networkImageView.setErrorImageResId(errorImageResId);
		networkImageView.setImageUrl(imageUrl, mImageLoader);
	}

	/**
	 * ��������һ�� GsonRequest(GET)
	 * 
	 * @param url
	 * @param clazz
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> GsonRequest<T> newGsonRequest(String url, Class<T> clazz,
			Listener<T> listener, ErrorListener errorListener) {
		GsonRequest<T> request = new GsonRequest<T>(url, clazz, listener,
				errorListener);
		add(request);
		return request;
	}

	/**
	 * ��������һ�� GsonRequest
	 * 
	 * @param url
	 * @param clazz
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> GsonRequest<T> newGsonRequest(int method, String url,
			Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		GsonRequest<T> request = new GsonRequest<T>(method, url, clazz,
				listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * ��������һ�� GsonRequest(GET)
	 * 
	 * @param url
	 * @param typeToken
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> GsonRequest<T> newGsonRequest(String url,
			TypeToken<T> typeToken, Listener<T> listener,
			ErrorListener errorListener) {
		GsonRequest<T> request = new GsonRequest<T>(url, typeToken, listener,
				errorListener);
		add(request);
		return request;
	}

	/**
	 * ��������һ�� GsonRequest
	 * 
	 * @param url
	 * @param typeToken
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> GsonRequest<T> newGsonRequest(int method, String url,
			TypeToken<T> typeToken, Listener<T> listener,
			ErrorListener errorListener) {
		GsonRequest<T> request = new GsonRequest<T>(method, url, typeToken,
				listener, errorListener);
		add(request);
		return request;
	}

	private <T> Request<T> add(Request<T> request) {
		return volleyRequestQueue.add(request);
	}

	/**
	 * �жϡ�ȡ����������
	 * 
	 * @param tag
	 */
	public void cancelAll(Object tag) {
		volleyRequestQueue.cancelAll(tag);
	}

}
