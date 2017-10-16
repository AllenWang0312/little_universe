package edu.tjrac.swant.richtexteditor.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class ImageCheckView extends View {

    int cent_x,cent_y;
    
    State state; ExpandOrientation orientation;

    ArrayList<Integer> img_res;
    int check_index;

    void addItem(int res_id){
        if(img_res==null){
            img_res=new ArrayList<>();
        }
        img_res.add(res_id);
    }


    public ImageCheckView(Context context) {
        super(context);
    }

    public ImageCheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    enum State{
        closed,opening,open,closing
    }
    enum ExpandOrientation{
        left,up,right,down
    }
}
