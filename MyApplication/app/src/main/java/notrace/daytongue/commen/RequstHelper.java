package notrace.daytongue.commen;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

import notrace.daytongue.entitys.response.BaseResult;
import notrace.daytongue.entitys.response.LoginResult;
import notrace.daytongue.entitys.response.root;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.http.VolleyRequest;
import notrace.daytongue.xmlutils.XmlUtils;

/**
 * Created by notrace on 2015/9/16.
 */
public class RequstHelper {

    public static void checkLogin(final String userName, final String pwd, final RequestCallBack<LoginResult> callBack){


        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                LoginResult result=XmlUtils.xmlToBean(s,LoginResult.class);

                if(result!=null&&"1".equals(result.getStatus())){
                    callBack.onSuccess(result);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                    callBack.onFail(volleyError.toString());
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String ,String> params=new HashMap<String,String>();

                params.put("userName",userName);
                params.put("pwd",pwd);
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };

        VolleyRequest.getInstance().getQue().add(request);
    }

    public static void register(final String name,final String pwd, final RequestCallBack<String> callBack)
    {


        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                BaseResult result = XmlUtils.xmlToBean(s, BaseResult.class);

                if(result!=null&&"0".equals(result.getStatus()))
                {
                    //TODO register success result.getStatus()==1?
//                    response=result.getStatus();
                    callBack.onSuccess(result.getStatus());
                }else
                {
                    callBack.onFail(result.getMsg());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                callBack.onFail(volleyError.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String ,String> params=new HashMap<String,String>();
                params.put("userXml","<Users>" +
                        "  <UserName>"+name+"</UserName>" +
                        "  <Passwords>"+pwd+"</Passwords>" +
                        "</Users>");
                params.put("tokenKey", CommonConst.TOKENID);
                return params;
            }
        };

        VolleyRequest.getInstance().getQue().add(request);
    }


    public static  void  updateUser(final String userXml,String tokenKey){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_UPDATEPASSWORD, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.d("TAG",s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
    }



    public static  void getTopic(final String top, final String gCode, final String isPerson, final String uCode, final String datetime){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_GETTOPIC, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            Log.d("Ta",s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.d("tA",volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String>params=new HashMap<>();
                params.put("top",top);
                params.put("gCode",gCode);
                params.put("isPerson",isPerson);
                params.put("uCode",uCode);
                params.put("datetime",datetime);
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };


        VolleyRequest.getInstance().getQue().add(request);

    }


    public void postRequest(String url,final HashMap<String,String>params)
    {

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
    }



    public static void updateUserInfo(String url,HashMap<String,String> params){

    }

    /**
     * update user's password
     * @param pwd
     * @param uCode
     * @param oldPwd
     * @param callBack
     */
    public static void updatePassword(final String pwd, final String uCode, final String oldPwd, final RequestCallBack<BaseResult> callBack){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_UPDATEPASSWORD, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                BaseResult result=XmlUtils.xmlToBean(s,BaseResult.class);
                if(result!=null&&"0".equals(result.getStatus())){
                    //// TODO: 2015/9/17  success
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                callBack.onFail(volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("pwd",pwd);
                params.put("oldPwd",oldPwd);
                params.put("uCode",uCode);
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };
        VolleyRequest.getInstance().getQue().add(request);
    }



    public static void upLoadImage(final String bytestr, final String ucode,final String fileExtension){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_FILEUPLOADIMAGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {


                Log.d("TAG",s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String, String>();
                params.put("uCode",ucode);
                params.put("fileExtension",fileExtension);
                params.put("tokenKey",CommonConst.TOKENID);
                params.put("bytestr",bytestr);
                return params;
            }
        };

        VolleyRequest.getInstance().getQue().add(request);
    }

    public static void  getRCMDBannerInfo(){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_GetRCMDBannerInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("TAG",s);
                root root=XmlUtils.xmlToBean(s,root.class);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.d("TAG",volleyError.toString());
            }

            }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };
        VolleyRequest.getInstance().getQue().add(request);
    }

//
//    private void baserequest(String url,final Map<String,String>params,final RequestCallBack<Class<T>>callBack){
//        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//
//               Class<T> t=XmlUtils.xmlToBean(s,t.getClass());
//                callBack.onSuccess(t);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//                callBack.onFail(volleyError.toString());
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                return params;
//            }
//        };
//        VolleyRequest.getInstance().getQue().add(request);
//    }
//
//
//    protected Class<T> getResultClass(){
//        try{
//            return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        }catch (Exception e)
//        {
//        }
//
//        return  null;
//    }
}
