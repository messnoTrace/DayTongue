package notrace.daytongue.entitys.response;

/**
 * Created by notrace on 2015/9/17.
 */
public class RCMDModel {
    private String ID;
    private String ImgUrl;
    private String Url;
    private String Ucode;
    private String PicWidth;
    private String PicHeight;
    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getUcode() {
        return Ucode;
    }

    public void setUcode(String ucode) {
        Ucode = ucode;
    }

    public String getPicWidth() {
        return PicWidth;
    }

    public void setPicWidth(String picWidth) {
        PicWidth = picWidth;
    }

    public String getPicHeight() {
        return PicHeight;
    }

    public void setPicHeight(String picHeight) {
        PicHeight = picHeight;
    }
}
