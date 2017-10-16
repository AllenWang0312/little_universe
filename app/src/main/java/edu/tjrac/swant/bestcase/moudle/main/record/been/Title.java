package edu.tjrac.swant.bestcase.moudle.main.record.been;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class Title implements Record{
   public String title;
   public String summary;
   public boolean hasMore;

    public Title(String title, boolean hasMore) {
        this.title = title;
        this.hasMore = hasMore;
    }

    @Override
    public Long getCreateTime() {
        return null;
    }

    @Override
    public int getItemType() {
        return -1;
    }
}
