package notrace.daytongue.entitys.response;

/**
 * Created by notrace on 2015/9/28.
 */
public class Good {
    private boolean IsAttention;
    private String UCode;
    private String Introduction;
    private String NickName;
    private String UserHead;

    public boolean isAttention() {
        return IsAttention;
    }

    public void setIsAttention(boolean isAttention) {
        IsAttention = isAttention;
    }

    public String getUCode() {
        return UCode;
    }

    public void setUCode(String UCode) {
        this.UCode = UCode;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getUserHead() {
        return UserHead;
    }

    public void setUserHead(String userHead) {
        UserHead = userHead;
    }


    public Good() {
    }

    public Good(boolean isAttention, String UCode, String introduction, String nickName, String userHead) {

        IsAttention = isAttention;
        this.UCode = UCode;
        Introduction = introduction;
        NickName = nickName;
        UserHead = userHead;
    }
}
