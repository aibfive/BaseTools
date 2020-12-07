package com.aibfive.basetools.adapter.expandable;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;

/**
 * Date : 2020/5/19/019
 * Time : 17:21
 * author : Li
 */
public abstract class TagAbstractExpandableItem<T> extends AbstractExpandableItem<T> {

    protected Object tag;

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
