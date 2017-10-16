package edu.tjrac.swant.bestcase.moudle.main.record.been;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/15 0015.
 */

public class Note implements Record {

    String filename;
    String path;

    ArrayList<Tag> tags;
    Date date ;

    public  Note(String path,String filename){
        this.filename=filename;
        this.path=path;
        tags=gettags(path,filename);
        date = new Date(new File(path,filename).lastModified());
    }

    private ArrayList<Tag> gettags(String path, String filename) {
        return null;
    }

    @Override
    public Long getCreateTime() {
//
        return System.currentTimeMillis();
    }

    @Override
    public int getItemType() {
        return 2;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
