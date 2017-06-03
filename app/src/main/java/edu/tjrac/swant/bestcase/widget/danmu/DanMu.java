package edu.tjrac.swant.bestcase.widget.danmu;

/**
 * Created by wpc on 2017/4/15.
 */

public class DanMu {

    public DanMu(int parent_width, int parent_height) {
        x = parent_width;
        y = (int)(Math.random() * parent_height);
    }

    private int x, y;

    private int userId;

    private int speed;

    private long createTime;//相对视频开始时间(db存储)  或   date System.currentTimeMillis()

    private String content;

    private int content_color;

    private String typeface;

    public DanMu(int userId, int speed, long createTime, String content, int content_color) {
        this.userId = userId;
        this.speed = speed;
        this.createTime = createTime;
        this.content = content;
        this.content_color = content_color;
    }

    public DanMu(int userId, int speed, long createTime, String content, int content_color, String typeface) {
        this.userId = userId;
        this.speed = speed;
        this.createTime = createTime;
        this.content = content;
        this.content_color = content_color;
        this.typeface = typeface;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContent_color() {
        return content_color;
    }

    public void setContent_color(int content_color) {
        this.content_color = content_color;
    }

    public String getTypeface() {
        return typeface;
    }

    public void setTypeface(String typeface) {
        this.typeface = typeface;
    }
}
