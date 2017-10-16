package edu.tjrac.swant.bestcase.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class StateImageView extends ImageView {

    private HashMap<Integer,Integer> res_ids;

   public void addState(int index,int id){
        if(res_ids==null){
            res_ids=new HashMap<>();
        }
        res_ids.put(index,id);
    }
    int state;
    public void setState(int index){
       setImageResource(res_ids.get(index));
       state=index;
    }
    public int getState(){
        return state;
    }

    public void switchState(){
        if(state==res_ids.size()-1){
            setState(0);
        }else {
            setState(getState()+1);
        }
    }

    public StateImageView(Context context) {
        super(context);
    }

    public StateImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
