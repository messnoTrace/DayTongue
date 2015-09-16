package notrace.daytongue.entitys.response;

/**
 * Created by notrace on 2015/9/16.
 */
public class LoginResult {

    private String UserHead;
    private String NickName;
    private String UserName;
    private String Sex;
    private String Ucode;



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
    public String getUserHead() {
        return UserHead;
    }

    public void setUserHead(String userHead) {
        UserHead = userHead;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getUcode() {
        return Ucode;
    }

    public void setUcode(String ucode) {
        Ucode = ucode;
    }
}
