package edu.tjrac.swant.bestcase.moudle.main.project;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.tjrac.swant.bestcase.moudle.main.project.been.Project;
import edu.tjrac.swant.bestcase.util.swant.FileUtils;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class ProjectViewModel extends ViewModel {

    public static final String root_dir = FileUtils.SD_local_project;

    public MutableLiveData<List<Project>> work_groups;

  public LiveData<List<Project>> getProjects(){
        if(work_groups==null){
            work_groups= new MutableLiveData<>();
        }
      List<Project> liveData =new ArrayList<>() ;

        File root= new File(root_dir);
        String []childs =root.list();
        for(String child:childs){
            liveData.add(new Project(new File(root,child)));
        }
        work_groups.setValue(liveData);

        return  work_groups;
    }

}
