package com.aibfive.basetools.adapter.expandable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public interface SelectMultiItemEntity extends MultiItemEntity {

    boolean isSelectAble = true;
    void setSelect(boolean isSelect);
    boolean isSelect();
    void setSelectAble(boolean isSelectAble);
    boolean isSelectAble();

}
