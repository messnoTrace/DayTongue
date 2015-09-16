package notrace.daytongue.http;

/**
 * Created by notrace on 2015/9/16.
 */
public interface RequesCallBack<T> {
    public void onSuccess(T t);
    public void onFail(String msg);
}
