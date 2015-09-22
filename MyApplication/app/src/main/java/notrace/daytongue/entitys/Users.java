package notrace.daytongue.entitys;

/**
 * Created by notrace on 2015/9/22.
 */
public class Users {
    private String Status;
    private String UCode;
    private String NickName;
    private String ImgCode;
    private String UserHead;
    private String TId;
    private boolean IsDel;
    private boolean IsLock;
    private Boolean IsAdmin;
    private String LastUpdateDate;
    private String LastLoginDate;
    private String WLevel;
    private String Createdate;
    private String Coin;
    private String HeadHeight;
    private String HeadWidth;
    private String AttResult;


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUCode() {
        return UCode;
    }

    public void setUCode(String UCode) {
        this.UCode = UCode;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getImgCode() {
        return ImgCode;
    }

    public void setImgCode(String imgCode) {
        ImgCode = imgCode;
    }

    public String getUserHead() {
        return UserHead;
    }

    public void setUserHead(String userHead) {
        UserHead = userHead;
    }

    public String getTId() {
        return TId;
    }

    public void setTId(String TId) {
        this.TId = TId;
    }

    public boolean isDel() {
        return IsDel;
    }

    public void setIsDel(boolean isDel) {
        IsDel = isDel;
    }

    public boolean isLock() {
        return IsLock;
    }

    public void setIsLock(boolean isLock) {
        IsLock = isLock;
    }

    public Boolean getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        IsAdmin = isAdmin;
    }

    public String getLastUpdateDate() {
        return LastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        LastUpdateDate = lastUpdateDate;
    }

    public String getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }

    public String getWLevel() {
        return WLevel;
    }

    public void setWLevel(String WLevel) {
        this.WLevel = WLevel;
    }

    public String getCreatedate() {
        return Createdate;
    }

    public void setCreatedate(String createdate) {
        Createdate = createdate;
    }

    public String getCoin() {
        return Coin;
    }

    public void setCoin(String coin) {
        Coin = coin;
    }

    public String getHeadHeight() {
        return HeadHeight;
    }

    public void setHeadHeight(String headHeight) {
        HeadHeight = headHeight;
    }

    public String getHeadWidth() {
        return HeadWidth;
    }

    public void setHeadWidth(String headWidth) {
        HeadWidth = headWidth;
    }

    public String getAttResult() {
        return AttResult;
    }

    public void setAttResult(String attResult) {
        AttResult = attResult;
    }
}
