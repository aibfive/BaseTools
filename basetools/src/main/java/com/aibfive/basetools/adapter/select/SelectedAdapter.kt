package com.aibfive.basetools.adapter.select

import android.view.View
import com.aibfive.basetools.adapter.BaseRVAdapter
import com.aibfive.basetools.adapter.BaseRVHolder

/**
 * Date : 2020/10/27/027
 * Time : 14:24
 * author : Li
 */
open class SelectedAdapter<T : SelectItemEntity> : BaseRVAdapter<T> {

    companion object {
        const val MODE_SINGLE = 1;//单选模式
        const val MODE_MULTI = 2;//多选模式
        const val PAYLOAD = "select"//
    }
    lateinit var listener : OnSelectedListener
    var modeEnabled : Boolean = true//模式启用
    var mode : Int = MODE_SINGLE//当前模式--单线
    var singleModeLimit : Boolean = true//单选模式--限制是否可以取消选中
    val selectHashMap = HashMap<Any, Boolean>()
    var showCacheStatus = true//是否显示缓存的选中状态

    constructor(layoutResId : Int) : super(layoutResId)

    override fun convertPayloads(helper: BaseRVHolder, item: T, payloads: MutableList<Any>) {
        super.convertPayloads(helper, item, payloads)
    }

    override fun onBindVH(holder: BaseRVHolder, item: T, position: Int) {
        registerSelectOperateView(holder.itemView, item, position)
    }

    /**
     * 注册选择操作视图
     */
    fun registerSelectOperateView(view : View?, item : T, position: Int){
        if(view == null){
            return
        }
        view.isSelected = item.select
        view.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                item.select = !item.select
                notifyItemChanged(position, PAYLOAD)
                when(mode){
                    MODE_SINGLE -> {//单选模式
                        cacheSingleSelectStatus(item)
                    }
                    MODE_MULTI -> {//多选模式
                        cacheMultiSelectStatus(item)
                    }
                }
                if(listener != null){
                    listener.onSelected(isSelectedAll(), getSelectedCount())
                }
                if(onItemClickListener != null){
                    setOnItemClick(v, position)
                }
            }
        })
    }

    override fun setData(index: Int, data: T) {
        if(showCacheStatus) {
            showCacheSelectStatus(data)
        }
        super.setData(index, data)
        if (showCacheStatus && listener != null) {
            listener.onSelected(isSelectedAll(), getSelectedCount())
        }
    }

    override fun replaceData(data: MutableCollection<out T>) {
        if(showCacheStatus && data != null){
            showCacheSelectStatus(data.toList())
        }
        super.replaceData(data)
        if (showCacheStatus && listener != null) {
            listener.onSelected(isSelectedAll(), getSelectedCount())
        }
    }

    override fun setNewData(data: List<T>?) {
        if(showCacheStatus){
            showCacheSelectStatus(data)
        }
        super.setNewData(data)
        if (showCacheStatus && listener != null) {
            listener.onSelected(isSelectedAll(), getSelectedCount())
        }
    }

    /**
     * 显示缓存的选中状态
     */
    private fun showCacheSelectStatus(data : T?){
        if(selectHashMap.isEmpty() || data == null) {
            return
        }
        val set: Set<Any> = selectHashMap.keys
        val iterator = set.iterator()
        while (iterator.hasNext()) {
            val obj = iterator.next()
            if (data.tag != null && data.tag!!.equals(obj)) {
                data.select = true
                break
            }
        }
    }

    /**
     * 显示缓存的选中状态
     */
    private fun showCacheSelectStatus(data: List<T>?){
        if (selectHashMap.isEmpty() || data == null || data.size == 0) {
            return
        }
        val set: Set<Any> = selectHashMap.keys
        val iterator = set.iterator()
        while (iterator.hasNext()) {
            val obj = iterator.next()
            for (item in data) {
                if (item.tag != null && item.tag!!.equals(obj)) {
                    item.select = true
                    break
                }
            }
        }
    }

    /**
     * 清楚缓存
     */
    fun clearCache(){
        if(!selectHashMap.isEmpty()){
            selectHashMap.clear()
        }
    }


    /**
     * 设置单选模式选择状态
     */
    fun cacheSingleSelectStatus(item: T){
        if(item.tag == null){
            return
        }
        if(singleModeLimit){//单选模式--限制必须选中
            if(!selectHashMap.containsKey(item.tag!!)) {
                selectHashMap.clear()//清除缓存
                selectHashMap.put(item.tag!!, true)//若选中，缓存该key，value。
            }
        }else{//单选模式--可以取消选中
            if(item.select){
                if(!selectHashMap.containsKey(item.tag!!)) {
                    selectHashMap.clear()//清除缓存
                    selectHashMap.put(item.tag!!, true)//若选中，缓存该key，value。
                }
            }else{
                selectHashMap.remove(item.tag!!)//若取消选中，移除该key，value。
            }
        }
    }

    /**
     * 设置多选模式选择状态
     */
    fun cacheMultiSelectStatus(item: T){
        if(item.tag == null){
            return
        }
        if(item.select){
            selectHashMap.put(item.tag!!, true)//若选中，缓存该key，value。
        }else{
            selectHashMap.remove(item.tag!!)//若取消选中，移除该key，value。
        }
    }

    /**
     * 模式设置-->
     * 模式弃用
     * 当前模式
     */
    fun setMode(modeEnabled : Boolean, mode : Int){
        this.modeEnabled = modeEnabled
        this.mode = mode
    }

    /**
     * 是否选中全部
     */
    fun isSelectedAll() : Boolean{
        when(mode){
            MODE_SINGLE -> {//单选模式--选中一个表示选中全部
                return selectHashMap.size == 1
            }
            MODE_MULTI -> {//多选模式
                return selectHashMap.size == itemCount
            }
            else -> return false
        }
    }

    /**
     * 获取选中的数量
     */
    fun getSelectedCount() : Int {
        return selectHashMap.size
    }

    /**
     * 获取选中项--用于单选模式
     */
    fun getSingleSelectedData() : T? {
        for(item in mData){
            if(item.select){
                return item
            }
        }
        return null
    }

    /**
     * 获取选中项--用于多选模式
     */
    fun getMultiSelectedData() : List<T> {
        val list = ArrayList<T>()
        for(item in mData){
            if(item.select){
                list.add(item)
            }
        }
        return list
    }



    /**
     * item选择监听器
     */
    interface OnSelectedListener{
        /**
         * isAll：是否选中全部（对于单选模式，选中一个便代表选中全部）
         * count：选中数量
         */
        fun onSelected(isAll : Boolean, count : Int)
    }

    fun setOnSelectedListener(listener : OnSelectedListener){
        this.listener = listener
    }

}