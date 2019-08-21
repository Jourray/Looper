package com.example.shop.bean;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/1914:46
 * desc   :
 * package: Shop:
 */
public class NumBean {
    int tag;
    boolean ischeck;

    @Override
    public String toString() {
        return "NumBean{" +
                "tag=" + tag +
                ", ischeck=" + ischeck +
                '}';
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public NumBean(int tag, boolean ischeck) {
        this.tag = tag;
        this.ischeck = ischeck;
    }
}
