package notrace.daytongue.entitys.response;

/**
 * Created by notrace on 2015/9/16.
 */
public class BaseResult {

    private String Status;
    private String Msg;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
