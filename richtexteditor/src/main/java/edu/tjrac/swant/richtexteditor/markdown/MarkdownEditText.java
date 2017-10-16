package edu.tjrac.swant.richtexteditor.markdown;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class MarkdownEditText extends EditText implements TextWatcher {


    private int historySize=100;
    private List<StringBuilder> historyList = new LinkedList<StringBuilder>();

    private boolean historyEnable =true;
    private boolean historyWorking = true;
    private int historyCursor = 0;

    private StringBuilder inputBefore;
    private Editable inputLast;


    public MarkdownEditText(Context context) {
        super(context);
    }

    public MarkdownEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarkdownEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence text, int start, int count, int after) {
        if (!historyEnable || historyWorking) {
            return;
        }

        inputBefore = new StringBuilder(text);
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count) {
        // DO NOTHING HERE
    }

    @Override
    public void afterTextChanged(Editable text) {
        if (!historyEnable || historyWorking) {
            return;
        }

        inputLast = text;
        if (text != null && text.toString().equals(inputBefore.toString())) {
            return;
        }

        if (historyList.size() >= historySize) {
            historyList.remove(0);
        }

        historyList.add(inputBefore);
        historyCursor = historyList.size();
    }

    public void undo() {
        if (!undoValid()) {
            return;
        }

        historyWorking = true;

        historyCursor--;
        setText(historyList.get(historyCursor));
        setSelection(getEditableText().length());

        historyWorking = false;

        invalidate();
    }
    public void redo() {
        if (!redoValid()) {
            return;
        }

        historyWorking = true;

        if (historyCursor >= historyList.size() - 1) {
            historyCursor = historyList.size();
            setText(inputLast);
        } else {
            historyCursor++;
            setText(historyList.get(historyCursor));
        }

        setSelection(getEditableText().length());
        historyWorking = false;
        invalidate();
    }
    public boolean undoValid() {
        if (!historyEnable || historySize <= 0 || historyWorking) {
            return false;
        }

        if (historyList.size() <= 0 || historyCursor <= 0) {
            return false;
        }

        return true;
    }
    public boolean redoValid() {
        if (!historyEnable || historySize <= 0 || historyList.size() <= 0 || historyWorking) {
            return false;
        }

        return historyCursor < historyList.size() - 1 || historyCursor >= historyList.size() - 1 && inputLast != null;
    }


}
