package edu.tjrac.swant.bestcase.common.Tempalte;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import edu.tjrac.swant.bestcase.R;

/**
 * Created by wpc on 2017/4/21.
 */

public class TempalteDialogFragment extends DialogFragment {

    Context mContext;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext=getActivity();
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setTitle("");
        //布局请重新绑定其他
        View v=LayoutInflater.from(mContext).inflate(R.layout.layout_tempalte,null);

        builder.setView(v);

        builder.setPositiveButton("确定",null);
        builder.setNegativeButton("取消",null);

        return builder.create();
    }
}
