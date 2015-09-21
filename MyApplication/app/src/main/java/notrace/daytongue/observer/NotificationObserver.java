package notrace.daytongue.observer;

public interface NotificationObserver<T> {
	public void onReceiveNotification(String tag, Object data);
//	public void onReceiveNotification2(String tag,T t);

}
