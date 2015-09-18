package notrace.daytongue.commen;

import com.thoughtworks.xstream.XStream;

import notrace.daytongue.entitys.Banner;
import notrace.daytongue.entitys.Contents;
import notrace.daytongue.entitys.Photo;
import notrace.daytongue.entitys.Topic;
import notrace.daytongue.entitys.Topics;
import notrace.daytongue.entitys.response.GoodResult;
import notrace.daytongue.entitys.response.RCMDModel;

/**
 * Created by notrace on 2015/9/18.
 */
public class XMLParser {
    public static Banner xml2Banner(String xml){
        XStream xStream=new XStream();

        xStream.alias("root",Banner.class);
        xStream.alias("RCMDModel", RCMDModel.class);

        xStream.aliasField("item",Banner.class,"item");

        Banner banner= (Banner) xStream.fromXML(xml);
        return  banner;
    }

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

    public  static GoodResult xml2GoodResult(String xml){
        XStream xStream=new XStream();

        //TODO erro
        xStream.alias("string",GoodResult.class);

        GoodResult result= (GoodResult) xStream.fromXML(xml);
        return  result;
    }
}
