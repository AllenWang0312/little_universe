package edu.tjrac.swant.bestcase.been;

import android.net.Uri;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by wpc on 2017/8/17.
 */
@Entity
public class User {
    @Id
    public Long id;
    public String name;
    @Transient
    public Uri portrait_uri;

    public int age;

    public String portrait, sign,city,
            qq, weichat, weibo,
            tel, address;
//    private String address;

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeibo() {
        return this.weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getWeichat() {
        return this.weichat;
    }

    public void setWeichat(String weichat) {
        this.weichat = weichat;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1405954604)
    public User(Long id, String name, int age, String portrait, String sign,
            String city, String qq, String weichat, String weibo, String tel,
            String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.portrait = portrait;
        this.sign = sign;
        this.city = city;
        this.qq = qq;
        this.weichat = weichat;
        this.weibo = weibo;
        this.tel = tel;
        this.address = address;
    }

    @Generated(hash = 586692638)
    public User() {
    }
    public User(String name) {
        this.name=name;
    }
    public User(String name,String tel) {
        this.name=name;
        this.tel=tel;
    }
}
