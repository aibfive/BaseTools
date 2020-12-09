package com.aibfive.basetools.adapter.node

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Date : 2020/12/9/009
 * Time : 15:20
 * author : Li
 */
open class ExpandableNodeAdapter<T : MultiItemEntity> : BaseNodeAdapter {

    private var firstLayoutId : Int? = null
    private var secondLayoutId : Int? = null
    private var thirdLayoutId : Int? = null

    private constructor() : super()

    constructor(firstLayoutId : Int?, secondLayoutId : Int?) : this(){
        this.firstLayoutId = firstLayoutId
        this.secondLayoutId = secondLayoutId
        init()
    }

    constructor(firstLayoutId : Int?, secondLayoutId : Int?, thirdLayoutId : Int?) : this(firstLayoutId, secondLayoutId){
        this.thirdLayoutId = thirdLayoutId
        init()
    }

    fun init() {
        if(firstLayoutId != null) {
            addNodeProvider(object : BaseNodeProvider() {
                override val itemViewType: Int
                    get() = NodeLevel.LEVEL_FIRST
                override val layoutId: Int
                    get() = firstLayoutId!!
                override fun convert(helper: BaseViewHolder, item: BaseNode) {
                    convertFirst(helper, item)
                    helper.itemView.setOnClickListener {
                        if (item is BaseExpandNode) {
                            expandOrCollapse(getItemPosition(item), false, true)
                        } else {
                            if (getOnItemClickListener() != null) {
                                getOnItemClickListener()?.onItemClick(getAdapter()!!, helper.itemView, getItemPosition(item))
                            }
                        }
                    }
                }
            })
        }
        if(secondLayoutId != null) {
            addNodeProvider(object : BaseNodeProvider() {
                override val itemViewType: Int
                    get() = NodeLevel.LEVEL_SECOND
                override val layoutId: Int
                    get() = secondLayoutId!!
                override fun convert(helper: BaseViewHolder, item: BaseNode) {
                    convertSecond(helper, item)
                    helper.itemView.setOnClickListener {
                        if (item is BaseExpandNode) {
                            expandOrCollapse(getItemPosition(item), false, true)
                        } else {
                            if (getOnItemClickListener() != null) {
                                getOnItemClickListener()?.onItemClick(getAdapter()!!, helper.itemView, getItemPosition(item))
                            }
                        }
                    }
                }
            })
        }
        if(thirdLayoutId != null) {
            addNodeProvider(object : BaseNodeProvider() {
                override val itemViewType: Int
                    get() = NodeLevel.LEVEL_THIRD
                override val layoutId: Int
                    get() = thirdLayoutId!!
                override fun convert(helper: BaseViewHolder, item: BaseNode) {
                    convertThird(helper, item)
                }
            })
        }
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        try {
            return (getItem(position) as MultiItemEntity).itemType
        } catch (e: Exception) {
            return -1
        }
    }

    open fun convertFirst(helper: BaseViewHolder, item: BaseNode){

    }

    open fun convertSecond(helper: BaseViewHolder, item: BaseNode){

    }

    open fun convertThird(helper: BaseViewHolder, item: BaseNode){

    }



}