package com.powersky.testapplication.viewholder;

import java.util.ArrayList;
import java.util.List;

public class TreeItem {

    //储存的数据
    private String mString;
    //层级，方便缩进
    private int level;
    //是否展开
    private boolean open;
    //子项
    private List<TreeItem> mSons;

    public TreeItem(String string) {
        mString = string;
        //每个item默认层级为1，被添加到父类中时根据父类更改层级
        this.level = 1;
        this.open = false;
    }

    public String getString() {
        return mString;
    }

    //是否有子项，若有则返回子项集合，若没有则为空
    public List<TreeItem> getSons() {
        if(mSons == null || mSons.size() == 0)
            return null;
        return mSons;
    }

    public TreeItem addSons(TreeItem sons) {
        //在初次添加时候初始化
        if(mSons == null)
            mSons = new ArrayList<>();
        //根据自己层级为子项设定层级
        sons.setSonLevel(sons,this.level);
        //把子项加入到自己的列表
        mSons.add(sons);
        return this;
    }

    public void setString(String string) {
        mString = string;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    private void setSonLevel(TreeItem treeItem,int level){
        //子项的层级比父类大1
        treeItem.setLevel(level+1);
        //若有子项，则递归调用，设置其层级
        if (treeItem.getSons() != null) {
            for (TreeItem item : treeItem.getSons()) {
                setSonLevel(item, item.getLevel());
            }
        }
    }
}
