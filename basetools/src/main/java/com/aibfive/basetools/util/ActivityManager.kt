package com.aibfive.basetools.util

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList

/**
 * 所有Activity的管理器
 * 建议在Application调用registerActivityLifecycleCallbacks
 * 进行Activity的生命周期绑定，并统一管理。
 * Date : 2020/12/10/010
 * Time : 17:30
 * author : Li
 */
object ActivityManager {

    private val mActStack = Stack<WeakReference<Activity>>()

    fun size() : Int{
        return mActStack.size
    }

    /**
     * 添加activity
     */
    fun add(activity : Activity){
        mActStack.add(WeakReference(activity))
    }

    /**
     * 移除activity
     */
    fun remove(activity : Activity){
        for(actStack in mActStack){
            if(equals(actStack, activity)){
                mActStack.remove(actStack)
                break
            }
        }
    }

    fun equals(weakReference: WeakReference<out Activity>, activity: Activity) : Boolean{
        return weakReference.get() != null && weakReference.get()!!.equals(activity)
    }

    fun equals(weakReference: WeakReference<out Activity>, cls : Class<out Activity>) : Boolean{
        return weakReference.get() != null && weakReference.get()!!.javaClass.equals(cls)
    }

    /**
     * 栈内是否存在该Activity
     */
    fun contains(cls : Class<out Activity>) : Boolean{
        for(actStack in mActStack){
            if(equals(actStack, cls)){
                return true
            }
        }
        return false
    }

    /**
     * 通过Class获取Activity实例
     */
    fun find(cls : Class<out Activity>) : Activity?{
        for(actStack in mActStack){
            if(equals(actStack, cls)){
                return actStack.get()
            }
        }
        return null
    }

    /**
     * 获取当前Activity实例
     */
    fun getCurrent() : Activity?{
        if(mActStack.isEmpty()){
            return null
        }
        return mActStack.lastElement().get()
    }

    /**
     * 移除所有activity
     * 移除过程无需mActStack去移除所存的activity实例，
     * 因为由于绑定了Activity的生命周期，当finish执行，
     * 在onDestory的remove也就执行了。
     */
    fun finishAll(){
        for(actStack in mActStack){
            val activity = actStack?.get()
            if(activity != null && !activity.isFinishing){
                activity.finish()
            }
        }
    }

    /**
     * 移除activity
     * 移除过程无需mActStack去移除所存的activity实例，
     * 因为由于绑定了Activity的生命周期，当finish执行，
     * 在onDestory的remove也就执行了。
     */
    fun finish(activity: Activity){
        if(activity != null && !activity.isFinishing){
            activity.finish()
        }
    }

    /**
     * 移除该类activity
     * 若栈中存在多个，一并移除。
     * 移除过程无需mActStack去移除所存的activity实例，
     * 因为由于绑定了Activity的生命周期，当finish执行，
     * 在onDestory的remove也就执行了。
     */
    fun finish(cls: Class<out Activity>){
        for(actStack in mActStack){
            if(equals(actStack, cls)){
                val activity = actStack.get()!!
                if(activity != null && !activity.isFinishing){
                    activity.finish()
                }
            }
        }
    }

}