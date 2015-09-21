package notrace.daytongue.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by notrace on 2015/9/21.
 */
public class PhoneUtil {


    public static void showInputBord(Context mContext,View view){
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(mContext.INPUT_METHOD_SERVICE);
         imm.showSoftInput(view, 0);
    }

    public static void hideInputBord(Context mContext,View view){
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(mContext.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
