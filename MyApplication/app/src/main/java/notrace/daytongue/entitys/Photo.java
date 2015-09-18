package notrace.daytongue.entitys;

import java.io.Serializable;

/**
 * Created by notrace on 2015/9/16.
 */
public class Photo implements Serializable{
    private String PCode;
    private String TCode;
    private String ACode;
    private  String ImgCode;
    private  String ImgName;
    private String ShortUrl;
    private String ImgDes;
    private String Mark;
    private String Emark;
    private boolean IsDel;
    private String UpdateDate;
    private String SortOrder;
    private String uid;
    private String imgid;
    private String photoname;
    private String SrcPath;
    private String MidumPath;
    private String UploadError;
    private String SmallPath;
    private String UCode;
    private String UserHead;
    private String IsAttention;
    private String NickName;
    private boolean IsSale;
    private boolean IsBuyed;

    public String getTCode() {
        return TCode;
    }

    public void setTCode(String TCode) {
        this.TCode = TCode;
    }

    public String getPCode() {
        return PCode;
    }

    public void setPCode(String PCode) {
        this.PCode = PCode;
    }

    public String getACode() {
        return ACode;
    }

    public void setACode(String ACode) {
        this.ACode = ACode;
    }

    public String getImgCode() {
        return ImgCode;
    }

    public void setImgCode(String imgCode) {
        ImgCode = imgCode;
    }

    public String getImgName() {
        return ImgName;
    }

    public void setImgName(String imgName) {
        ImgName = imgName;
    }

    public String getShortUrl() {
        return ShortUrl;
    }

    public void setShortUrl(String shortUrl) {
        ShortUrl = shortUrl;
    }

    public String getImgDes() {
        return ImgDes;
    }

    public void setImgDes(String imgDes) {
        ImgDes = imgDes;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String mark) {
        Mark = mark;
    }

    public String getEmark() {
        return Emark;
    }

    public void setEmark(String emark) {
        Emark = emark;
    }

    public boolean isDel() {
        return IsDel;
    }

    public void setIsDel(boolean isDel) {
        IsDel = isDel;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    public String getSortOrder() {
        return SortOrder;
    }

    public void setSortOrder(String sortOrder) {
        SortOrder = sortOrder;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getPhotoname() {
        return photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

    public String getSrcPath() {
        return SrcPath;
    }

    public void setSrcPath(String srcPath) {
        SrcPath = srcPath;
    }

    public String getMidumPath() {
        return MidumPath;
    }

    public void setMidumPath(String midumPath) {
        MidumPath = midumPath;
    }

    public String getUploadError() {
        return UploadError;
    }

    public void setUploadError(String uploadError) {
        UploadError = uploadError;
    }

    public String getSmallPath() {
        return SmallPath;
    }

    public void setSmallPath(String smallPath) {
        SmallPath = smallPath;
    }

    public String getUCode() {
        return UCode;
    }

    public void setUCode(String UCode) {
        this.UCode = UCode;
    }

    public String getUserHead() {
        return UserHead;
    }

    public void setUserHead(String userHead) {
        UserHead = userHead;
    }

    public String getIsAttention() {
        return IsAttention;
    }

    public void setIsAttention(String isAttention) {
        IsAttention = isAttention;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public boolean isSale() {
        return IsSale;
    }

    public void setIsSale(boolean isSale) {
        IsSale = isSale;
    }

    public boolean isBuyed() {
        return IsBuyed;
    }

    public void setIsBuyed(boolean isBuyed) {
        IsBuyed = isBuyed;
    }
}
