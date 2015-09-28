package notrace.daytongue.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import notrace.daytongue.R;

/**
 * Created by notrace on 2015/9/25.
 */
public class TwoStatusTextView extends TextView{

    private boolean STATUS_NORMAL=true;

    public boolean isSTATUS_NORMAL() {
        return STATUS_NORMAL;
    }

    private int drawable_nomal;
    private int drawable_clicked;

    private String text_nomal;
    private String text_clicked;
    private int drawable_position=1;//default is left



    public TwoStatusTextView(Context context) {
        super(context);
    }

    public TwoStatusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.TwoStatusTextView);
        drawable_nomal=ta.getResourceId(R.styleable.TwoStatusTextView_nomalImage,R.drawable.ic_launcher);
        drawable_clicked=ta.getResourceId(R.styleable.TwoStatusTextView_clickedImage, R.drawable.ic_launcher);
        drawable_position=ta.getInteger(R.styleable.TwoStatusTextView_drawableposition, 1);

        text_nomal=ta.getString(R.styleable.TwoStatusTextView_nomalText);
        text_clicked=ta.getString(R.styleable.TwoStatusTextView_clickedText);
        ta.recycle();

        setView(STATUS_NORMAL);
    }
    public TwoStatusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void setView(boolean isNomal){
        Drawable drawable;
         String text;
        if(isNomal){
            drawable=getResources().getDrawable(drawable_nomal);
            text=text_nomal;
        }else
        {
            drawable=getResources().getDrawable(drawable_clicked);
            text=text_clicked;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        switch (drawable_position)
        {
            case 1:
                //left
                this.setCompoundDrawables(drawable,null,null,null);
                break;
            case 2:
                //top
                this.setCompoundDrawables(null,drawable,null,null);
                break;
            case  3:

                //right
                this.setCompoundDrawables(null,null,drawable,null);
                break;
            case 4:
                //bottom
                this.setCompoundDrawables(null,null,null,drawable);
                break;
        }

        setText(text);

    }

    public void changeStatus(){
        setView(STATUS_NORMAL);
        STATUS_NORMAL=!STATUS_NORMAL;
    }

    public void setStatus(boolean status)
    {
        this.STATUS_NORMAL=status;
        setView(status);

    }

}
