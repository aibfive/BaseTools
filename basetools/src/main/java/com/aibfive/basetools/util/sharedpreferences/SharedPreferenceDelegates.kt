package com.aibfive.basetools.util.sharedpreferences

import com.aibfive.basetools.util.LogUtil
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Date : 2020/12/1/001
 * Time : 17:10
 * author : Li
 */
object SharedPreferenceDelegates {

    fun int(default : Int = 0) : ReadWriteProperty<SharedPreferencesUtils, Int> {
        return object : ReadWriteProperty<SharedPreferencesUtils, Int> {
            override fun setValue(thisRef: SharedPreferencesUtils, property: KProperty<*>, value: Int) {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return
                }
                thisRef.preferences!!.edit().putInt(property.name, value).apply()
            }

            override fun getValue(thisRef: SharedPreferencesUtils, property: KProperty<*>): Int {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return default
                }
                return thisRef.preferences!!.getInt(property.name, default)
            }
        }
    }

    fun string(default : String? = null) : ReadWriteProperty<SharedPreferencesUtils, String?> {
        return object : ReadWriteProperty<SharedPreferencesUtils, String?> {
            override fun setValue(thisRef: SharedPreferencesUtils, property: KProperty<*>, value: String?) {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return
                }
                thisRef.preferences!!.edit().putString(property.name, value).apply()
            }

            override fun getValue(thisRef: SharedPreferencesUtils, property: KProperty<*>): String? {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return default
                }
                return thisRef.preferences!!.getString(property.name, default)
            }
        }
    }

    fun boolean(default : Boolean = false) : ReadWriteProperty<SharedPreferencesUtils, Boolean> {
        return object : ReadWriteProperty<SharedPreferencesUtils, Boolean> {
            override fun setValue(thisRef: SharedPreferencesUtils, property: KProperty<*>, value: Boolean) {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return
                }
                thisRef.preferences!!.edit().putBoolean(property.name, value).apply()
            }

            override fun getValue(thisRef: SharedPreferencesUtils, property: KProperty<*>): Boolean {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return default
                }
                return thisRef.preferences!!.getBoolean(property.name, default)
            }
        }
    }

    fun float(default : Float = 0.0F) : ReadWriteProperty<SharedPreferencesUtils, Float> {
        return object : ReadWriteProperty<SharedPreferencesUtils, Float> {
            override fun setValue(thisRef: SharedPreferencesUtils, property: KProperty<*>, value: Float) {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return
                }
                thisRef.preferences!!.edit().putFloat(property.name, value).apply()
            }

            override fun getValue(thisRef: SharedPreferencesUtils, property: KProperty<*>): Float {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return default
                }
                return thisRef.preferences!!.getFloat(property.name, default)
            }
        }
    }

    fun long(default : Long = 1L) : ReadWriteProperty<SharedPreferencesUtils, Long> {
        return object : ReadWriteProperty<SharedPreferencesUtils, Long> {
            override fun setValue(thisRef: SharedPreferencesUtils, property: KProperty<*>, value: Long) {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return
                }
                thisRef.preferences!!.edit().putLong(property.name, value).apply()
            }

            override fun getValue(thisRef: SharedPreferencesUtils, property: KProperty<*>): Long {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return default
                }
                return thisRef.preferences!!.getLong(property.name, default)
            }
        }
    }

    fun stringSet(default : Set<String>? = null) : ReadWriteProperty<SharedPreferencesUtils, Set<String>?> {
        return object : ReadWriteProperty<SharedPreferencesUtils, Set<String>?> {
            override fun setValue(thisRef: SharedPreferencesUtils, property: KProperty<*>, value: Set<String>?) {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return
                }
                thisRef.preferences!!.edit().putStringSet(property.name, value).apply()
            }

            override fun getValue(thisRef: SharedPreferencesUtils, property: KProperty<*>): Set<String>? {
                if(thisRef.preferences == null){
                    LogUtil.v(SharedPreferenceDelegates::class.simpleName, "SharedPreferences value is null")
                    return default
                }
                return thisRef.preferences!!.getStringSet(property.name, default)
            }
        }
    }

}