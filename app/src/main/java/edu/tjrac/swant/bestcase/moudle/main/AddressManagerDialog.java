package edu.tjrac.swant.bestcase.moudle.main;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import edu.tjrac.swant.bestcase.R;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class AddressManagerDialog extends DialogFragment {

    ImageView add ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.address_dialog,container,false);
        add=v.findViewById(R.id.iv_add_address);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new AlertDialog.Builder(getActivity()).setTitle("")
            }
        });
        return v;
    }
}
