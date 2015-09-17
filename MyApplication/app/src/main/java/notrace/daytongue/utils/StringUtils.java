package notrace.daytongue.utils;

import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class StringUtils {
    public static final String MONEY_DECIMAL_FORMAT = "#,###";

    /**
     * is null or its length is 0
     * <p/>
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0, return true, else return false.
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * overload isEmpty for charSequence
     *
     * @param charSequence
     * @return
     */
    public static boolean isEmpty(CharSequence charSequence) {
        return (charSequence == null || isEmpty(charSequence.toString()));
    }

    /**
     * overload isEmpty for TextView;
     *
     * @param textView
     * @return
     */
    public static boolean isEmpty(TextView textView) {
        return (textView == null || isEmpty(textView.getText()));
    }

    /**
     * get the charSequence as string
     *
     * @param charSequence
     * @return
     */
    public static String getString(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence.toString();
    }

    /**
     * overload for TextView
     *
     * @param textView
     * @return
     */
    public static String getString(TextView textView) {
        return textView == null ? null : getString(textView.getText());
    }

    /**
     * 根据格式，将制定数据格式化
     *
     * @param data
     * @param pattern
     * @return
     */
    public static String getDecimalFormat(double data, String pattern) {
        String res = String.valueOf(data);
        DecimalFormat df = new DecimalFormat();
        try {
            df.applyPattern(pattern);
            res = df.format(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }

    public static String getDecimalFormat(String data, String pattern) {
        if (isEmpty(data)||"null".equals(data)) {
            return data;
        }
        return  getDecimalFormat(Double.valueOf(data),pattern);
    }

//    public static void main(String[] args) {
//        double s = 33334.5;
//        System.out.println("=============StringUtils.getDecimalFormat(s,StringUtils.MONEY_DECIMAL_FORMAT)=============" + StringUtils.getDecimalFormat(s, StringUtils.MONEY_DECIMAL_FORMAT));
//        System.out.println("=============StringUtils.getDecimalFormat(s,StringUtils.MONEY_DECIMAL_FORMAT)=============" + StringUtils.getDecimalFormat(String.valueOf(s), StringUtils.MONEY_DECIMAL_FORMAT));
//    }

    /**
     * 隐藏手机号中间部分
     *
     * @param str
     * @return
     */
    public static String ChangeMobileNum(String str) {
        String mobileNum = null;
        if (str != null && str.length() == 11) {
            String num = str.substring(0, 3);
            String num1 = str.substring(7, 11);
            mobileNum = num + "****" + num1;
        }
        return mobileNum;
    }

    /**
     * 隐藏身份证号中间部分
     *
     * @param str
     * @return
     */
    public static String ChangeUserNum(String str) {
        String userNum = null;
        if (str != null && (15 == str.length() || str.length() == 18)) {
            if (str.length() == 15) {
                String num = str.substring(0, 3);
                String num1 = str.substring(11, 15);
                userNum = num + "********" + num1;
            }
            else if (str.length() == 18) {
                String num = str.substring(0, 3);
                String num1 = str.substring(14, 18);
                userNum = num + "***********" + num1;
            }

        }
        return userNum;
    }

    //年月日 时间转换
    public static SimpleDateFormat dayFormat_yyyy_MM_dd;
    public static String changeDate(String date) {

        if (isEmpty(date)) {
            return null;
        }
        if (dateFormat_yyyyMMddHHmmss == null) {
            dateFormat_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
        }
        if (dayFormat_yyyy_MM_dd == null) {
            dayFormat_yyyy_MM_dd = new SimpleDateFormat("yyyy年MM月dd日");
        }

        try {
            return dayFormat_yyyy_MM_dd.format(dateFormat_yyyyMMddHHmmss.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SimpleDateFormat dateFormat_yyyyMMddHHmmss;
    public static SimpleDateFormat dayFormat_yyyy_MM_dd_HH_mm_ss;
    public static String changeDate1(String date) {

        if (isEmpty(date)) {
            return null;
        }
        if (dateFormat_yyyyMMddHHmmss == null) {
            dateFormat_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
        }
        if (dayFormat_yyyy_MM_dd_HH_mm_ss == null) {
            dayFormat_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        }

        try {
            return dayFormat_yyyy_MM_dd_HH_mm_ss.format(dateFormat_yyyyMMddHHmmss.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
