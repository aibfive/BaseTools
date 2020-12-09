package com.aibfive.sample.ui.adapter.expandable

import com.aibfive.basetools.adapter.node.ExpandableNodeAdapter
import com.aibfive.basetools.adapter.node.NodeLevel
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

/**
 * Date : 2020/12/9/009
 * Time : 14:19
 * author : Li
 */
class FirstBean() : BaseExpandNode(), MultiItemEntity {

    var list : List<BaseNode>? = null
    var title : String? = null
    override val childNode: MutableList<BaseNode>?
        get() = list as MutableList<BaseNode>?
    override val itemType: Int
        get() = NodeLevel.LEVEL_FIRST

    class SecondBean : BaseExpandNode(), MultiItemEntity {

        var list : List<BaseNode>? = null
        var title : String? = null
        override val childNode: MutableList<BaseNode>?
            get() = list as MutableList<BaseNode>?
        override val itemType: Int
            get() = NodeLevel.LEVEL_SECOND

        class ThirdBean : BaseNode(), MultiItemEntity {

            var title : String? = null
            override val childNode: MutableList<BaseNode>?
                get() = null
            override val itemType: Int
                get() = NodeLevel.LEVEL_THIRD
        }

    }

}