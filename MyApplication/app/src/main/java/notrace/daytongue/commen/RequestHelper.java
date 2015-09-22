package notrace.daytongue.commen;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

import notrace.daytongue.entitys.Banner;
import notrace.daytongue.entitys.Comment;
import notrace.daytongue.entitys.Comments;
import notrace.daytongue.entitys.Topics;
import notrace.daytongue.entitys.response.BaseResult;
import notrace.daytongue.entitys.response.GetUserInfoResult;
import notrace.daytongue.entitys.response.LoginResult;
import notrace.daytongue.entitys.response.UserList;
import notrace.daytongue.http.RequestCallBack;
import notrace.daytongue.http.VolleyRequest;
import notrace.daytongue.xmlutils.XmlUtils;

/**
 * Created by notrace on 2015/9/16.
 */
public class RequestHelper {



    /**
     * LOGIN ok
     * @param userName
     * @param pwd
     * @param callBack
     */
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

    //OK

    /**
     * register  ok
     * @param name
     * @param pwd
     * @param callBack
     */
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




    /**
     * get topic
     * @param top
     * @param gCode
     * @param isPerson
     * @param uCode
     * @param datetime
     */
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




    public  static void getUserInfo(final String ucode, final RequestCallBack<GetUserInfoResult>callBack){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_GETUSERINFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

//                GetUserInfoResult result=XmlUtils.xmlToBean(s,GetUserInfoResult.class);
                GetUserInfoResult result=XMLParser.xml2UserInfo(s);
                if(result!=null){

                    callBack.onSuccess(result);
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
                params.put("uCode",ucode);
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
    //OK
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



    //OK

    /**
     * upload image ok
     * @param bytestr
     * @param ucode
     * @param fileExtension
     */
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

    /**
     * index banner data ok
     * @param callBack
     */
    //OK
    public static void  getRCMDBannerInfo(final RequestCallBack<Banner>callBack){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_GetRCMDBannerInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Banner banner=XMLParser.xml2Banner(s);
                callBack.onSuccess(banner);

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
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };
        VolleyRequest.getInstance().getQue().add(request);
    }


//OK

    /**
     * index right listview ok
     * @param page
     * @param callBack
     */
    public  static void getRCMDPhotoInfo(final String page,final RequestCallBack<Banner>callBack){
        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_GetRCMDPhotoInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Banner model=XMLParser.xml2Banner(s);
                callBack.onSuccess(model);

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
                params.put("tokenKey",CommonConst.TOKENID);
                params.put("pageSize","10");
                params.put("page",page);
                return params;
            }
        };
        VolleyRequest.getInstance().getQue().add(request);
    }


    /**
     * index left listview ok
     * @param page
     * @param callBack
     */
    public  static void getRCMDUserInfo(final String page,final RequestCallBack<Banner>callBack){
        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_GetRCMDUserInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Banner model=XMLParser.xml2Banner(s);
                callBack.onSuccess(model);

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
                params.put("tokenKey",CommonConst.TOKENID);
                params.put("pageSize","10");
                params.put("page",page);
                return params;
            }
        };
        VolleyRequest.getInstance().getQue().add(request);
    }



    //OK

    /**
     * get user topic by uCode ok
     * @param uCode
     * @param page
     * @param callBack
     */
    public static void GetUserPicTopic(final String uCode,final  String page,final  RequestCallBack<Topics>callBack){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_GetUserPicTopic, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Topics topics=XMLParser.xml2Topics(s.trim().replace("&nbsp", ""));
                callBack.onSuccess(topics);

                Log.d("===================", topics.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("===================", volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("uCode",uCode);
                params.put("pageIndex","10");
                params.put("page",page);
                params.put("tokenKey",CommonConst.TOKENID);
                params.put("currentUCode","");
                params.put("datetime","");
                return params;
            }
        };

        VolleyRequest.getInstance().getQue().add(request);
    }


    /**
     * add good 0k
     * @param fCode
     * @param type
     * @param uCode
     * @param act
     * @param callBack
     */
    public static void addGood(final String fCode, final String type, final String uCode, final String act, final RequestCallBack<String>callBack){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_ADDGOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                callBack.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callBack.onFail(volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>map=new HashMap<>();
                map.put("fCode",fCode);
                map.put("type",type);
                map.put("uCode",uCode);
                map.put("act",act);
                map.put("tokenKey",CommonConst.TOKENID);
                return map;
            }
        };

        VolleyRequest.getInstance().getQue().add(request);

    }


    /**
     * get commment ok
     * @param code
     * @param callBack
     */

    public static void getComment(final String code, final RequestCallBack<Comments>callBack)
    {
        StringRequest request= new StringRequest(Request.Method.POST, CommonConst.URL_GETCOMMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Comments comments=XMLParser.xml2Comments(s);
                Log.d("=====================",s);
                callBack.onSuccess(comments);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("=====================", volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("code",code);
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };

        VolleyRequest.getInstance().getQue().add(request);


//                Map<String,String>params=new HashMap<>();
//                params.put("code",code);
//                params.put("tokenKey",CommonConst.TOKENID);
//        baserequest(CommonConst.URL_GETCOMMENT,params,callBack);

    }


    /**
     * get recmond user list ok
     * @param ucode
     * @param callBack
     */
    public static void getUserList(final String ucode,final  RequestCallBack<UserList>callBack){
        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_GETUSERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                UserList list=XMLParser.xml2UserList(s);
                if(list!=null)
                {
                    callBack.onSuccess(list);
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
                params.put("uCode",ucode);
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };
        VolleyRequest.getInstance().getQue().add(request);

    }


    /**
     * search all user ok
     * @param key
     * @param pageIndex
     * @param ucode
     * @param callBack
     */
    public static void SerchGetAllUsers(final String key , final String pageIndex, final String ucode, final RequestCallBack<UserList>callBack){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_SerchGetAllUsers, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                UserList list=XMLParser.xml2UserList(s);
                if(list!=null){
                    callBack.onSuccess(list);
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
                Map<String ,String>params=new HashMap<>();
                params.put("key",key);
                params.put("pageIndex",pageIndex);
                params.put("uCode",ucode);
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };

        VolleyRequest.getInstance().getQue().add(request);

    }

    //TODO 500
    public static void addCommentReturn(final Comment comment){
        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_ADDCOMMENTRETURN, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("=====================",s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("=====================",volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String ,String>params=new HashMap<>();
                params.put("comXml", "<Comment><FCode>"
                        +comment.getFCode()+"</FCode><CType>"
                        + comment.getCType()+"</CType><ImgCode>"
                        +comment.getImgCode()+"</ImgCode><UCode>"
                        +comment.getUCode()+"</UCode><Contents>"
                        +comment.getContents()+"</Contents><CreateDate>"
                                +"<img></img>"
                        +comment.getCreateDate()+"</CreateDate><NickName>"
                        +comment.getNickName()+"</NickName><UserHead>"
                        +comment.getUserHead()+"</UserHead><UImgCode>"
                        +comment.getUImgCode()+"</UImgCode></Comment>"
                );
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };

        VolleyRequest.getInstance().getQue().add(request);
    }






    //TODO  no data
    public static void getOccupations(){

        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_GetOccupations, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("==========",s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("==========",volleyError.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String ,String>params=new HashMap<>();
                params.put("tokenKey",CommonConst.TOKENID);
                return params;
            }
        };
        VolleyRequest.getInstance().getQue().add(request);

    }











































    //TODO  check check check

//    private static   <T> void baserequest(String url,final Map<String,String>params,final RequestCallBack<T>callBack){
//        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//
////                Log.d("==========",((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0].toString());
////               T t=XmlUtils.xmlToBean(s,(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
//                T t=XmlUtils.xmlToBean(s,(Class<T>)callBack.getClass());
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
