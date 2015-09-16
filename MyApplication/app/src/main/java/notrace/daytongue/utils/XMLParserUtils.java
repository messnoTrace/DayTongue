package notrace.daytongue.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by notrace on 2015/9/16.
 */
public class XMLParserUtils {
    public static List<Objects> parse(InputStream is,Class<?> clazz,List<String>fields,List<String> elements,String itemElement)
    {
        List<Objects> list=new ArrayList<Objects>();
        try{

            XmlPullParser xmlPullParser= Xml.newPullParser();
            xmlPullParser.setInput(is,"UTF-8");
            int event=xmlPullParser.getEventType();

        }catch (Exception e){

        }
        return  null;
    }
}
