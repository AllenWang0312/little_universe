package edu.tjrac.swant.bestcase.moudle.main.project.been;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import edu.tjrac.swant.bestcase.util.swant.FileUtils;
import edu.tjrac.swant.bestcase.util.swant.SerialUtil;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class Project implements Serializable, MultiItemEntity {

    int type ;

    String file_path;
    String name;
    String target;

    Long start_time;
    int plain_days;

    public Project(String name,int type){
        this.type=type;
        file_path= FileUtils.SD_local_project+"/"+name;
    }
    public Project(File file) {

        file_path=file.getAbsolutePath();
        name=file.getName();
    }

   public void create(){
        FileUtils.createOrExistsDir(file_path);
        File project_config = new File(file_path,name+".config");
        try {
            project_config.createNewFile();
            SerialUtil.saveObject2File(this,project_config);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemType() {
        return type;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public int getPlain_days() {
        return plain_days;
    }

    public void setPlain_days(int plain_days) {
        this.plain_days = plain_days;
    }
}
