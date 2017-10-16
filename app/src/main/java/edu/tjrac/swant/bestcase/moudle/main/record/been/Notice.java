package edu.tjrac.swant.bestcase.moudle.main.record.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
@Entity
public class Notice implements Record {
    @Id
    Long id;
    public int hour, min;
    public Long create_time;
    public boolean on;
    public boolean repeat;
    public int repeat_mod;//0 weekly 1 month

    public boolean sun;
    public boolean mon;
    public boolean tue;
    public boolean wed;
    public boolean thu;
    public boolean fri;
    public boolean sat;

    public String month;

    public String vlorm;

    public boolean vibrative;

    public String tips;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String tag;

    public int target = -1;// 0 Contacts 1 Location

    public String target_info;

    public Notice() {
create_time= System.currentTimeMillis();
    }

    @Generated(hash = 1397967039)
    public Notice(Long id, int hour, int min, Long create_time, boolean on,
            boolean repeat, int repeat_mod, boolean sun, boolean mon, boolean tue,
            boolean wed, boolean thu, boolean fri, boolean sat, String month,
            String vlorm, boolean vibrative, String tips, String tag, int target,
            String target_info) {
        this.id = id;
        this.hour = hour;
        this.min = min;
        this.create_time = create_time;
        this.on = on;
        this.repeat = repeat;
        this.repeat_mod = repeat_mod;
        this.sun = sun;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.month = month;
        this.vlorm = vlorm;
        this.vibrative = vibrative;
        this.tips = tips;
        this.tag = tag;
        this.target = target;
        this.target_info = target_info;
    }

    public String getTarget_info() {
        return this.target_info;
    }

    public void setTarget_info(String target_info) {
        this.target_info = target_info;
    }

    public int getTarget() {
        return this.target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getTips() {
        return this.tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public boolean getVibrative() {
        return this.vibrative;
    }

    public void setVibrative(boolean vibrative) {
        this.vibrative = vibrative;
    }

    public String getVlorm() {
        return this.vlorm;
    }

    public void setVlorm(String vlorm) {
        this.vlorm = vlorm;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public boolean getSat() {
        return this.sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean getFri() {
        return this.fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean getThu() {
        return this.thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public boolean getWed() {
        return this.wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean getTue() {
        return this.tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean getMon() {
        return this.mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean getSun() {
        return this.sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public int getRepeat_mod() {
        return this.repeat_mod;
    }

    public void setRepeat_mod(int repeat_mod) {
        this.repeat_mod = repeat_mod;
    }

    public boolean getRepeat() {
        return this.repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
    
    @Override
    public Long getCreateTime() {
        return create_time;
    }

    @Override
    public int getItemType() {
        return 1;
    }

    public boolean getOn() {
        return this.on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public Long getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
