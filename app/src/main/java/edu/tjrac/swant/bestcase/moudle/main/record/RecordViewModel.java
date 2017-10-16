package edu.tjrac.swant.bestcase.moudle.main.record;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.tjrac.swant.bestcase.moudle.main.record.been.Note;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Notice;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Record;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Tips;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Title;
import edu.tjrac.swant.bestcase.manager.db.DBManager;
import edu.tjrac.swant.bestcase.util.swant.FileUtils;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class RecordViewModel extends ViewModel {

    public MutableLiveData<List<Record>> group_records;

    public LiveData<List<Record>> getRecords(Context context, int return_type,String tag) {
        if (group_records == null) {
            group_records = new MutableLiveData<List<Record>>();
//            mix_records = new MutableLiveData<List>();
            //todo init records
        }
        return loadRecords(context, return_type,tag);
    }

    @SuppressLint("NewApi")
    private LiveData<List<Record>> loadRecords(Context context, int return_type,String tag) {
        List<Record> rec = new ArrayList<Record>();
        List<Tips> tips = DBManager.getInstance(context).queryTipsList(tag);
        List<Notice> notices = DBManager.getInstance(context).queryNoticeList(tag);
        rec.addAll(tips);
        rec.addAll(notices);
        List<Note> notes=getNotice( FileUtils.SD_local_record,tag);

//        group_records.setValue(rec);

        if (return_type == 0) {
            rec.sort(new Comparator<Record>() {
                @Override
                public int compare(Record r1, Record r2) {
                    return r1.getCreateTime() < r2.getCreateTime() ? -1 : 1;
                }
            });

//            mix_records.setValue(rec);
//            return mix_records;
        }else if(return_type==1){
//            List<Section> groups =new ArrayList<>();
//            for (int i = 0; i <tips.size() ; i++) {
//                Section section=new Section(i==0,"Tips");
//                section.t=tips.get(i);
//                groups.add(section);
//            }
//            for (int i = 0; i <notices.size() ; i++) {
//                Section section=new Section(i==0,"Notice");
//                section.t=notices.get(i);
//                groups.add(section);
//            }
//            group_records.setValue(groups);
            List<Record> groups =new ArrayList<>();
            if(tips.size()>0){
                groups.add(new Title("Tips",false));
                groups.addAll(tips);
            }
            if(notices.size()>0){
                groups.add(new Title("Notice",false));
                groups.addAll(notices);
            }
            if(notes.size()>0){
                groups.add(new Title("Note",false));
                groups.addAll(notes);
            }


            group_records.setValue(groups);
            return group_records;
        }
       return null;
    }

    private List<Note> getNotice(String record_path,String tag) {
        File file =new File(record_path,tag);
        List<Note> notes=new ArrayList<>();
        if(file.isDirectory()){
          String [] files=  file.list();
          for(String name : files){
              if(name.endsWith(".md")){
                  notes.add(new Note(file.getAbsolutePath(),name));
              }
          }
        }
        return notes;
    }

    public void deleteItem(int i,int type) {
//        if(type==0){
//            group_records.getValue().remove(mix_records.getValue().get(i));
//            mix_records.getValue().remove(i);
//        }else if(type==1){
//            mix_records.getValue().remove(group_records.getValue().get(i));
          group_records.getValue().remove(i);
//        }
//        records.notify();
    }


}
