package edu.tjrac.swant.bestcase.moudle;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by wpc on 2017/6/5.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }else {
            LeakCanary.install(this);
        }
    }
}
