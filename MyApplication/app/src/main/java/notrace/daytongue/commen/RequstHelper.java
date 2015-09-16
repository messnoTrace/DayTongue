package notrace.daytongue.commen;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

import notrace.daytongue.entitys.response.BaseResult;
import notrace.daytongue.entitys.response.LoginResult;
import notrace.daytongue.http.RequesCallBack;
import notrace.daytongue.http.VolleyRequest;
import notrace.daytongue.xmlutils.XmlUtils;

/**
 * Created by notrace on 2015/9/16.
 */
public class RequstHelper {



    public static void checkLogin(final String userName, final String pwd, final RequesCallBack<LoginResult> callBack){


        StringRequest request=new StringRequest(Request.Method.POST, CommonConst.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                LoginResult result=XmlUtils.xmlToBean(s,LoginResult.class);

                callBack.onSuccess(result);

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

    public static void register(final String name,final String pwd, final RequesCallBack<String> callBack)
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


}
