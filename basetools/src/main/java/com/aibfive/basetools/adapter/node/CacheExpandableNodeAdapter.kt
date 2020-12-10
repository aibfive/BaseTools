package com.aibfive.basetools.adapter.node

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Date : 2020/12/9/009
 * Time : 18:06
 * author : Li
 */
class CacheExpandableNodeAdapter<T : MultiItemEntity> : ExpandableNodeAdapter<T> {

    var cacheData : List<T>? = ArrayList<T>()

    constructor(firstLayoutId: Int?, secondLayoutId: Int?) : super(firstLayoutId, secondLayoutId)

    constructor(firstLayoutId: Int?, secondLayoutId: Int?, thirdLayoutId: Int?) : super(firstLayoutId, secondLayoutId, thirdLayoutId)


}