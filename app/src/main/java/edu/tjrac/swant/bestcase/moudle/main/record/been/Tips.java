package edu.tjrac.swant.bestcase.moudle.main.record.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/9/29 0029.
 */
@Entity
public class Tips implements Record{

    @Id(autoincrement = true)

    Long id;
    public Long user_id = Long.valueOf(0);
public Long create_time;

    public int imp_lev;
    //    String img_url;
    public String content;
    public String tag;

    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getImp_lev() {
        return this.imp_lev;
    }
    public void setImp_lev(int imp_lev) {
        this.imp_lev = imp_lev;
    }
    public Long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    

    public Tips() {
        create_time= System.currentTimeMillis();
    }
    @Generated(hash = 1558633877)
    public Tips(Long id, Long user_id, Long create_time, int imp_lev,
            String content, String tag) {
        this.id = id;
        this.user_id = user_id;
        this.create_time = create_time;
        this.imp_lev = imp_lev;
        this.content = content;
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }


    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public Long getCreateTime() {
        return create_time;
    }
    public Long getCreate_time() {
        return this.create_time;
    }
    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}
