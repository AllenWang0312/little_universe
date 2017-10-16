package edu.tjrac.swant.richtexteditor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class KeyListenerEditText extends EditText {
    public KeyListenerEditText(Context context) {
        super(context);
    }

    public KeyListenerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyListenerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface BackListener {
        void back(EditText et);
    }



    private BackListener listener;

    public void setBackListener(BackListener listener) {
        this.listener = listener;
    }



    @Override

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (listener != null) {
                listener.back(this);
            }
        }
        return false;
    }
}
