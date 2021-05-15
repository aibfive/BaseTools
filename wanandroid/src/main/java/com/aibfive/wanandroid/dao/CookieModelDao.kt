package com.aibfive.wanandroid.dao

import androidx.room.*
import com.aibfive.wanandroid.base.CookieModel

/**
 * @author: 小李
 * @date: 2021/5/14 22:05
 */
@Dao
interface CookieModelDao {

    /**
     * 获取Cookie
     * @return CookieModel
     */
    @Query("SELECT * FROM cookie")
    fun getCookie() : CookieModel

    /**
     * 储存Cookie
     * @param cookie CookieModel
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCookie(cookie : CookieModel)

}