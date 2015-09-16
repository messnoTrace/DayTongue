package notrace.daytongue.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 一些常用的正则判断
 * Created by notrace on 2015/6/19.
 */
public class RegExUtil {

    /**
     * 检测手机号是否合法
     * @param moblie
     * @return boolean
     */
    public static boolean isMobile(String moblie) {
        if (null==moblie || moblie.length() == 0){
            return false;
        }
        String regPhoneNum= "^[1][3-8]\\d{9}$";
        Pattern p = Pattern.compile(regPhoneNum);
        Matcher m = p.matcher(moblie);
        return m.find();
    }
    /**
     * 邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (null==email || email.length() == 0){
            return false;
        }
        String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
//	        SWLog.LogD(m.matches()+"---");
        return m.matches();
    }


    /**
     * 姓名是否合法
     * @param name
     * @return
     */
    public static boolean isName(String name){
        if (null==name || name.length() == 0){
            return false;
        }
        String str="/^[\\u4e00-\\u9fa5]+$/";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    /**\
     * 身份证号是否合法
     * @param idcard
     * @return
     */
    public static  boolean isIDCard(String idcard){
        if (null==idcard || idcard.length() == 0){
            return false;
        }
        String str="/\\d{17}[\\d|x|X]|\\d{15}/";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(idcard);
        return m.matches();
    }


    /**
     * 银行卡号是否合法
     * @param bankcardno
     * @return
     */
    public static boolean isBankCard(String bankcardno){
        if (null==bankcardno || bankcardno.length() == 0){
            return false;
        }
        String str="/^\\d{16}|\\d{19}$/";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(bankcardno);
        return m.matches();
    }
}
