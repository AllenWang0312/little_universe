package edu.tjrac.swant.bestcase.moudle.main.project;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.been.interfaze.OnPositiveButtonClickCallback;
import edu.tjrac.swant.bestcase.moudle.main.project.been.Project;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

@SuppressLint("ValidFragment")
public class AddProjectDialog extends DialogFragment {

    Context mContext;
    Dialog mDialog;
    OnPositiveButtonClickCallback mOnPositiveButtonClickCallback;

    @SuppressLint("ValidFragment")
    public AddProjectDialog(Context context, OnPositiveButtonClickCallback mCallback) {
        mOnPositiveButtonClickCallback = mCallback;
        mContext = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.add_workgroup, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        EditText name =v.findViewById(R.id.et_name);




        builder.setView(v);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Project project=new Project(name.getText().toString(),0);
                project.create();

                mOnPositiveButtonClickCallback.call();
            }
        });
        builder.setNegativeButton("取消", null);

        return builder.create();
    }
}
