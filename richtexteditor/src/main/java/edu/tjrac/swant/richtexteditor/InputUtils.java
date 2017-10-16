package edu.tjrac.swant.richtexteditor;

import android.text.Editable;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/10/14 0014.
 */

public class InputUtils {

    public static void insertString (EditText editText,String content){
        int index = editText.getSelectionStart();
        Editable editable = editText.getText();
        editable.insert(index, content);
    }
}
