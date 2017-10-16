package edu.tjrac.swant.bestcase.moudle.service;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class ClipboardTextWatcherService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {

                Toast.makeText(ClipboardTextWatcherService.this,
                        "clipboard text changed"+(clipboard.getText().toString()),Toast.LENGTH_LONG).show();


            }
        });


        return super.onStartCommand(intent, flags, startId);
    }
}
