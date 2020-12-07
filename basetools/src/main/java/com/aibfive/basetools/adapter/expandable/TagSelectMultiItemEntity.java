package com.aibfive.basetools.adapter.expandable;

/**
 * Date : 2020/9/15/015
 * Time : 9:54
 * author : Li
 */
public interface TagSelectMultiItemEntity extends SelectMultiItemEntity{

    Object tag = null;

    Object getTag();

    void setTag(Object tag);

}
