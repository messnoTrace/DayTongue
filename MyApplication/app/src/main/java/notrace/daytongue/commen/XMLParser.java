package notrace.daytongue.commen;

import com.thoughtworks.xstream.XStream;

import notrace.daytongue.entitys.Banner;
import notrace.daytongue.entitys.Comment;
import notrace.daytongue.entitys.Comments;
import notrace.daytongue.entitys.Contents;
import notrace.daytongue.entitys.Photo;
import notrace.daytongue.entitys.Topic;
import notrace.daytongue.entitys.Topics;
import notrace.daytongue.entitys.Users;
import notrace.daytongue.entitys.response.GetUserInfoResult;
import notrace.daytongue.entitys.response.GoodResult;
import notrace.daytongue.entitys.response.RCMDModel;
import notrace.daytongue.entitys.response.UserList;

/**
 * Created by notrace on 2015/9/18.
 */
public class XMLParser {

    /**
     * get banner info
     * @param xml
     * @return
     */
    public static Banner xml2Banner(String xml){
        XStream xStream=new XStream();

        xStream.alias("root",Banner.class);
        xStream.alias("RCMDModel", RCMDModel.class);

        xStream.aliasField("item",Banner.class,"item");

        Banner banner= (Banner) xStream.fromXML(xml);
        return  banner;
    }

    /**
     * get topics
     * @param xml
     * @return
     */
    public static Topics xml2Topics(String xml){
        XStream xStream=new XStream();
        xStream.alias("root",Topics.class);
        xStream.alias("Topic", Topic.class);
        xStream.alias("Contents", Contents.class);
        xStream.alias("Photo", Photo.class);


        xStream.aliasField("item", Topics.class, "item");
        xStream.aliasField("Contents", Topic.class, "contents");

        xStream.aliasField("item", Contents.class, "item");
        Topics topics = (Topics) xStream.fromXML(xml);
        return  topics;
    }

    /**
     * get goodresult
     * @param xml
     * @return
     */
    public  static GoodResult xml2GoodResult(String xml){
        XStream xStream=new XStream();

        //TODO erro
        xStream.alias("string", GoodResult.class);

        GoodResult result= (GoodResult) xStream.fromXML(xml);
        return  result;
    }

    /**
     *
     * get comment  list
     * @param xml
     * @return
     */
    public static Comments xml2Comments(String xml){

        XStream xStream=new XStream();
        xStream.alias("root",Comments.class);
        xStream.alias("Comment", Comment.class);
        xStream.aliasField("Comment", Comments.class, "item");
        Comments comments= (Comments) xStream.fromXML(xml);
        return  comments;

    }

    /**
     * get userinfo
     * @param xml
     * @return
     */
    public static GetUserInfoResult xml2UserInfo(String xml){
        XStream xStream=new XStream();
        xStream.alias("GetUserInfoResult", GetUserInfoResult.class);
        GetUserInfoResult result= (GetUserInfoResult) xStream.fromXML(xml);
        return result;
    }


    /**
     * get recmond friend and search friend list
     * @param xml
     * @return
     */
    public static UserList xml2UserList(String xml){
        XStream xStream=new XStream();
        xStream.alias("root",UserList.class);
        xStream.alias("Users", Users.class);
        xStream.aliasField("item",UserList.class,"item");
        UserList list= (UserList) xStream.fromXML(xml);
        return  list;
    }

    /**
     * get status for good collection
     * @param xml
     * @return
     */
    public static boolean getStatusCode(String xml){

        String[]s1=xml.split(">");
        String str=s1[s1.length-1];
        String status=str.substring(0,1);
        if(Integer.valueOf(status)>0){
            return  true;
        }
        return  false;
    }
}
