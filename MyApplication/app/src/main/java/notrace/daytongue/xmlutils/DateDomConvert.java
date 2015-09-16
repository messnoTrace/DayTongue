package notrace.daytongue.xmlutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateDomConvert implements DomConvert
{

    @Override
    public Date convert(Object object)
    {
        String date = null;
        if (object instanceof String)
        {
            date = (String)object;
        }
        if (date == null)
        {
            return null;
        }
        if (date.contains("T") || date.contains("."))
        {
            date = date.replace("T", " ");
            int index = date.indexOf(".");
            date = date.substring(0, index);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            return sdf.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args)
    {
        System.out.println(new DateDomConvert().convert("2014-10-20 15:15:15.024"));
    }

}
