package edu.tjrac.swant.bestcase.moudle;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import edu.tjrac.swant.bestcase.been.User;
import edu.tjrac.swant.bestcase.util.swant.FileUtils;

/**
 * Created by wpc on 2017/6/5.
 */

public class App extends Application {

    public static User logged_user = new User();
    public static String extand_file_path;
    public static String cache_path, file_path;


    @Override
    public void onCreate() {
        super.onCreate();
        cache_path = getCacheDir().getPath();
        file_path = getFilesDir().getPath();

        FileUtils.fileSystemCheck();
        Logger.addLogAdapter(new AndroidLogAdapter());

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        } else {
            LeakCanary.install(this);
        }


        Logger.addLogAdapter(new AndroidLogAdapter());

//        Stetho.initializeWithDefaults(this);
//        new OkHttpClient.Builder() .
//                addNetworkInterceptor(new StethoInterceptor()) .build();
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());


//        PluginHelper.getInstance().applicationOnCreate(getBaseContext());
    }

    public Context getContext() {
        return getApplicationContext();
    }
}
