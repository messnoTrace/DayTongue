package notrace.daytongue.entitys.response;

/**
 * Created by notrace on 2015/9/18.
 */
public class GetUserInfoResult {
    private String Status;
    private String UCode;
    private String UserName;
    private String NickName;
    private String Sex;
    private String UserHead;
    private String Telephone;
    private String Email;
    private boolean IsDel;
    private boolean IsLock;
    private boolean IsAdmin;
    private String ThirdPartyKey;
    private  String RegFrom;
    private String IP;
    private String LastUpdateDate;
    private String LastLoginDate;
    private String Createdate;
    private  String CreateCode;
    private boolean IsPublic;

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

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getUserHead() {
        return UserHead;
    }

    public void setUserHead(String userHead) {
        UserHead = userHead;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        IsAdmin = isAdmin;
    }

    public String getThirdPartyKey() {
        return ThirdPartyKey;
    }

    public void setThirdPartyKey(String thirdPartyKey) {
        ThirdPartyKey = thirdPartyKey;
    }

    public String getRegFrom() {
        return RegFrom;
    }

    public void setRegFrom(String regFrom) {
        RegFrom = regFrom;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
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

    public String getCreatedate() {
        return Createdate;
    }

    public void setCreatedate(String createdate) {
        Createdate = createdate;
    }

    public String getCreateCode() {
        return CreateCode;
    }

    public void setCreateCode(String createCode) {
        CreateCode = createCode;
    }

    public boolean isPublic() {
        return IsPublic;
    }

    public void setIsPublic(boolean isPublic) {
        IsPublic = isPublic;
    }

    public String getLostUpdateDate() {
        return LostUpdateDate;
    }

    public void setLostUpdateDate(String lostUpdateDate) {
        LostUpdateDate = lostUpdateDate;
    }

    private String LostUpdateDate;


}
