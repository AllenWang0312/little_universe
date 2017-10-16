package edu.tjrac.swant.bestcase.manager.notifacation;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.Uri;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class NotifecationService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setAlarmParams(Notification notification) {
        //AudioManager provides access to volume and ringer mode control.
        AudioManager volMgr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        switch (volMgr.getRingerMode()) {//获取系统设置的铃声模式
            case AudioManager.RINGER_MODE_SILENT://静音模式，值为0，这时候不震动，不响铃
                notification.sound = null;
                notification.vibrate = null;
                break;
            case AudioManager.RINGER_MODE_VIBRATE://震动模式，值为1，这时候震动，不响铃
                notification.sound = null;
                notification.defaults |= Notification.DEFAULT_VIBRATE;
                break;
            case AudioManager.RINGER_MODE_NORMAL://常规模式，值为2，分两种情况：1_响铃但不震动，2_响铃+震动
                Uri ringTone = null;
                //获取软件的设置
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//                if(!sp.contains(SystemUtil.KEY_RING_TONE)){//如果没有生成配置文件，那么既有铃声又有震动
                    notification.defaults |= Notification.DEFAULT_VIBRATE;
                    notification.defaults |= Notification.DEFAULT_SOUND;
//                }else{
//                    String ringFile = sp.getString(SystemUtil.KEY_RING_TONE, null);
//                    if(ringFile==null){//无值，为空，不播放铃声
//                        ringTone=null;
//                    }else if(!TextUtils.isEmpty(ringFile)){//有铃声：1，默认2自定义，都返回一个uri
//                        ringTone=Uri.parse(ringFile);
//                    }
//                    notification.sound = ringTone;
//
//                    boolean vibrate = sp.getBoolean(SystemUtil.KEY_NEW_MAIL_VIBRATE,true);
//                    if(vibrate == false){//如果软件设置不震动，那么就不震动了
//                        notification.vibrate = null;
//                    }else{//否则就是需要震动，这时候要看系统是怎么设置的：不震动=0;震动=1；仅在静音模式下震动=2；
//                        if(volMgr.getVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER) == AudioManager.VIBRATE_SETTING_OFF){
//                            //不震动
//                            notification.vibrate = null;
//                        }else if(volMgr.getVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER) == AudioManager.VIBRATE_SETTING_ONLY_SILENT){
//                            //只在静音时震动
//                            notification.vibrate = null;
//                        }else{
//                            //震动
//                            notification.defaults |= Notification.DEFAULT_VIBRATE;
//                        }
//                    }
//                }
                notification.flags |= Notification.FLAG_SHOW_LIGHTS;//都给开灯
                break;
            default:
                break;
        }
    }
}
